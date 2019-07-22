package com.example.mynotepad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ighodaro Precious on 12/7/2018.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TodoViewHolder> {
    //
    private List<Task> tasks = new ArrayList<>();
    private OnItemClickListener listener;


    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discription, parent, false);
        return new TodoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {

        holder.reminderHeading.setText(tasks.get(position).getTaskHeading());
        holder.date.setText(tasks.get(position).getDate());
        holder.time.setText(tasks.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setRemainder(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public Task getReminderAt(int position) {
        return tasks.get(position);
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView reminderHeading;
        private TextView time;
        private TextView date;
        private ImageView edit;


        public TodoViewHolder(View itemView) {
            super(itemView);

            reminderHeading = itemView.findViewById(R.id.reminderHeading);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);
            edit = itemView.findViewById(R.id.edit);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItenClick(tasks.get(position));
                    }

                }
            });
        }



    }



    public interface OnItemClickListener {
        void onItenClick(Task task);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
