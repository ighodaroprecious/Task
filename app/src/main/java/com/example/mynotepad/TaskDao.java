package com.example.mynotepad;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {


    @Query("SELECT * FROM task_table  ")
    LiveData<List<Task>> getAllRemainder();

    @Insert
    void insert (Task task);

    @Update
    void update (Task task);

    @Delete
    void delete (Task task);

    @Query("DELETE FROM task_table")
    void deleteAllReminders();
}
