package ru.vktarget.vktarget.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vktarget.vktarget.R;
import ru.vktarget.vktarget.adapters.TasksAdapter;
import ru.vktarget.vktarget.api.TasksApi;
import ru.vktarget.vktarget.models.TasksResponse;
import ru.vktarget.vktarget.models.User;

public class TasksActivity extends AppCompatActivity {

    private static final String TAG = "AppLog";
    private RecyclerView mTasksRecyclerView;
    private RecyclerView.Adapter mTasksAdapter;
    private ProgressBar tasksProgress;
    private ImageView refreshButton;
    private Map<String, User> mData;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://vktarget.ru/api/method/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    TasksApi tasksApi = retrofit.create(TasksApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tasksProgress = findViewById(R.id.tasks_progress);
        mTasksRecyclerView = findViewById(R.id.tasks_list);
        refreshButton = findViewById(R.id.update_image);

        mTasksRecyclerView.setVisibility(View.GONE);
        refreshButton.setVisibility(View.GONE);

        final String key = getIntent().getStringExtra("key");
        getTasks(key);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTasksRecyclerView.setVisibility(View.GONE);
                refreshButton.setVisibility(View.GONE);
                tasksProgress.setVisibility(View.VISIBLE);
                refreshTasks(key);
            }
        });
    }

    /**
     * Background thread
     */
    private void getTasks(final String key) {
        final Call<TasksResponse> dataCall = tasksApi.getBalance(key);
        dataCall.enqueue(new Callback<TasksResponse>() {
            @Override
            public void onResponse(@NonNull Call<TasksResponse> call, @NonNull Response<TasksResponse> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().size());
                    initList(response.body().getResponse());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TasksResponse> call, @NonNull Throwable t) {
                Log.e("Error", "Refresh error", t);
            }
        });
    }

    private void initList(Map<String, User> data) {
        mTasksRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mTasksLayoutManager = new LinearLayoutManager(this);
        mTasksRecyclerView.setLayoutManager(mTasksLayoutManager);

        mData = data;
        mTasksAdapter = new TasksAdapter(mData);
        mTasksRecyclerView.setAdapter(mTasksAdapter);
        contentVisible();
    }

    private void updateList(Map<String, User> data) {
        mData = data;
        mTasksAdapter.notifyDataSetChanged();
        Toast.makeText(TasksActivity.this, "Задания обновлены", Toast.LENGTH_SHORT).show();
        contentVisible();
    }

    private void contentVisible() {
        tasksProgress.setVisibility(View.GONE);
        mTasksRecyclerView.setVisibility(View.VISIBLE);
        refreshButton.setVisibility(View.VISIBLE);
    }

    /**
     * Background thread
     */
    private void refreshTasks(final String key) {
        final Call<TasksResponse> dataCall = tasksApi.getBalance(key);
        dataCall.enqueue(new Callback<TasksResponse>() {
            @Override
            public void onResponse(@NonNull Call<TasksResponse> call, @NonNull Response<TasksResponse> response) {
                if (response.body() != null) {
                    updateList(response.body().getResponse());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TasksResponse> call, @NonNull Throwable t) {
                Log.e("Error", "Refresh error", t);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
