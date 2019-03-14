package ru.vktarget.vktarget.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vktarget.vktarget.R;
import ru.vktarget.vktarget.api.BalanceApi;
import ru.vktarget.vktarget.models.Data;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://vktarget.ru/api/method/";

    private TextView balanceText;
    private ImageView refreshBtn;
    private Button tasksBtn;
    private Button exitBtn;
    private ProgressBar balanceProgress;
    private View content;

    private String key = null;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private BalanceApi balanceApi = retrofit.create(BalanceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initView();
        refreshBtnClickable();
        onClick();
    }

    private void initView() {
        key = getIntent().getStringExtra("key");
        Double balance = getIntent().getDoubleExtra("balance", 0);

        balanceText = findViewById(R.id.balance_text);
        refreshBtn = findViewById(R.id.refresh_button);
        tasksBtn = findViewById(R.id.tasks_button);
        exitBtn = findViewById(R.id.exit_button);
        balanceProgress = findViewById(R.id.balance_progress);
        content = findViewById(R.id.content);

        balanceProgress.setVisibility(View.GONE);
        balanceText.setText(String.valueOf(balance));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void refreshBtnClickable() {
        refreshBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

    private void onClick() {
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balanceProgress.setVisibility(View.VISIBLE);
                content.setVisibility(View.GONE);
                refreshBalance(key);
            }
        });

        tasksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TasksActivity.class);
                intent.putExtra("key", key);
                startActivity(intent);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    /**
     * Background thread
     */
    private void refreshBalance(final String key) {

        final Call<Data> dataCall = balanceApi.getBalance(key);
        dataCall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                if (response.body() != null) {
                    balanceText.setText(String.valueOf(response.body().getResponse().getBalance()));
                    balanceProgress.setVisibility(View.GONE);
                    content.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Баланс обновлен", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
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
