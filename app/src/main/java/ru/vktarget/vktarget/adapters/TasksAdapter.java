package ru.vktarget.vktarget.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Map;

import ru.vktarget.vktarget.R;
import ru.vktarget.vktarget.models.User;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.MyViewHolder> {

    private static final String TAG = "AppLog";
    private Map<String, User> data;

    public TasksAdapter(Map<String, User> data) {
        this.data = data;
    }

    @Override
    public TasksAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_tasks_list, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + data.get(String.valueOf(position + 1)));
        if (data.get(String.valueOf(position + 1)) != null) {
            holder.nameText.setText(data.get(String.valueOf(position + 1)).getTaskName());
            holder.idText.setText(data.get(String.valueOf(position + 1)).getId());
            holder.typeText.setText(data.get(String.valueOf(position + 1)).getType());
            holder.urlText.setText(data.get(String.valueOf(position + 1)).getUrl());
            holder.quantityText.setText(String.valueOf(data.get(String.valueOf(position + 1)).getQuantity()));
            holder.peopleText.setText(String.valueOf(data.get(String.valueOf(position + 1)).getPeople()));
            holder.taskProgress.setMax(data.get(String.valueOf(position + 1)).getQuantity());
            holder.taskProgress.setProgress(data.get(String.valueOf(position + 1)).getPeople());
        }
    }

    @Override
    public int getItemCount() {
        return data.size() - 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, idText, typeText, urlText, quantityText, peopleText;
        ProgressBar taskProgress;

        MyViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.name_text);
            idText = itemView.findViewById(R.id.id_text);
            typeText = itemView.findViewById(R.id.type_text);
            urlText = itemView.findViewById(R.id.url_text);
            quantityText = itemView.findViewById(R.id.quantity_text);
            peopleText = itemView.findViewById(R.id.people_text);
            taskProgress = itemView.findViewById(R.id.task_progress);
        }
    }
}

