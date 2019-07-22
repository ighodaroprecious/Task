package com.example.mynotepad;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Ighodaro Precious on 12/13/2018.
 */

@Entity(tableName = "task_table")
public class Task {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo(name = "task_heading")
    private String taskHeading;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;


    public Task(String taskHeading, String date, String time) {
        this.taskHeading = taskHeading;
        this.date = date;
        this.time = time;
    }


    public String getTaskHeading() {
        return taskHeading;
    }

    public void setTaskHeading(String taskHeading) {
        this.taskHeading = taskHeading;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
