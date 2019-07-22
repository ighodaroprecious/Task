package com.example.mynotepad;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class}, version = 2)
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase instance;

    public abstract TaskDao reminderDao();

    public static  synchronized TaskDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDatabase.class, "reminder_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return  instance;
    }

    private static RoomDatabase.Callback roomCallback = new  RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static  class  PopulateDbAsyncTask extends AsyncTask< Void, Void, Void>{
        private TaskDao taskDao;

        private  PopulateDbAsyncTask(TaskDatabase db){

            taskDao = db.reminderDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.insert(new Task("I want to go to work","21 Aug 2019","11:00 AM"));
            taskDao.insert(new Task("I want to go to sleep","21 Aug 2019","11:00 AM"));
            taskDao.insert(new Task("I want to go to church","21 Aug 2019","11:00 AM"));
            return null;
        }
    }
}
