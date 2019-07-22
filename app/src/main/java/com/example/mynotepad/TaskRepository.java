package com.example.mynotepad;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>> allReminder;


    public TaskRepository(@NonNull Application application) {

        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        taskDao = taskDatabase.reminderDao();
        allReminder = taskDao.getAllRemainder();
    }

    public  void insert (Task task){
        new TaskRepository.InsertReminderAsyncTask(taskDao).execute(task);

    }

    public  void update (Task task){
        new TaskRepository.UpdateReminderAsyncTask(taskDao).execute(task);
    }

    public  void delete (Task task){
        new TaskRepository.DeleteReminderAsyncTask(taskDao).execute(task);
    }

    public  void deleteAllReminders (){
        new TaskRepository.DeleteAllReminderAsyncTask(taskDao).execute();
    }

    public  LiveData<List<Task>> getAllReminder(){
        return  allReminder;
    }

    private  static class InsertReminderAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao taskDao;

        private  InsertReminderAsyncTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return  null;
        }
    }

    private  static class UpdateReminderAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDao taskDao;

        private  UpdateReminderAsyncTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return  null;
        }
    }

    private  static class DeleteReminderAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDao taskDao;

        private  DeleteReminderAsyncTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return  null;
        }
    }

    private  static class DeleteAllReminderAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDao taskDao;

        private  DeleteAllReminderAsyncTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.deleteAllReminders();
            return  null;
        }
    }
}
