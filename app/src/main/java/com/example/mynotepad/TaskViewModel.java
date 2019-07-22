package com.example.mynotepad;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel{
    private TaskRepository repository;
    private LiveData<List<Task>> allReminder;


    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allReminder = repository.getAllReminder();
    }

       public void  insert ( Task task){
        repository.insert(task);
    }

    public void  update ( Task task){
        repository.update(task);
    }

    public void  delete ( Task task){
        repository.delete(task);
    }

    public void  deleteAllReminders ( ){
        repository.deleteAllReminders();
    }

    public LiveData<List<Task>> getAllReminder(){
        return allReminder;
    }
}
