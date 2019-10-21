package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    Context context;
    ArrayList<TaskModel> taskModels;


    public TaskAdapter(Context context, ArrayList<TaskModel> taskModels) {
        this.context = context;
        this.taskModels = taskModels;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTask, descTask, dateTask, keytask;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            titleTask = itemView.findViewById(R.id.titleTask);
            descTask = itemView.findViewById(R.id.descTask);
            dateTask = itemView.findViewById(R.id.dateTask);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.titleTask.setText(taskModels.get(position).getTitleTask());
        holder.descTask.setText(taskModels.get(position).getDescTask());
        holder.dateTask.setText(taskModels.get(position).getDateTask());

        final String getTitleTask = taskModels.get(position).getTitleTask(); // burada direk taskModels.getposition olabilirdi
        final String getDesckTask = taskModels.get(position).getDescTask();
        final String getDateTask = taskModels.get(position).getDateTask();
        final String getKeytask = taskModels.get(position).getKeytask();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditTask.class);
                intent.putExtra("titletask", getTitleTask);  // burada direk taskModels.getposition olabilirdi
                intent.putExtra("desctask", getDesckTask);  // burada direk taskModels.getposition olabilirdi
                intent.putExtra("datetask", getDateTask);  // burada direk taskModels.getposition olabilirdi
                intent.putExtra("keytask", getKeytask);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return taskModels.size();
    }


}
