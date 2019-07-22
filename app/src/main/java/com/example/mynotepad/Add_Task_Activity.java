package com.example.mynotepad;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import java.util.Calendar;

/**
 * Created by Ighodaro Precious on 11/24/2018.
 */

public class Add_Task_Activity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.example.mynotepad.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.mynotepad.EXTRA_DESCRIPTION";
    public static final String EXTRA_DATE = "com.example.mynotepad.EXTRA_DATE";
    public static final String EXTRA_TIME = "com.example.mynotepad.EXTRA_TIME";

    EditText reminderHeading;
    EditText reminderDescription;
    Intent intent = new Intent();
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        save = findViewById(R.id.save_btn);
        reminderHeading = findViewById(R.id.edit_txt1);
        reminderDescription = findViewById(R.id.edit_txt2);


    }


    public void save(View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Comfirm save..!!!");

        alertDialog.setMessage("Are you sure, You want to save?");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                saveReminder();
                finish();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;

            }
        });

        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();


    }

    private void saveReminder() {
        String title = reminderHeading.getText().toString();
        String desc = reminderDescription.getText().toString();

        if(title.trim().isEmpty()||desc.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra(EXTRA_TITLE,title);
        intent.putExtra(EXTRA_DESCRIPTION,desc);

        setResult(RESULT_OK, intent);

    }

    @TargetApi(Build.VERSION_CODES.N)
    public void date(View view) {
        Calendar mCurrentDate = Calendar.getInstance();
        int mYear = mCurrentDate.get(Calendar.YEAR);
        int mMonth = mCurrentDate.get(Calendar.MONTH);
        int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker;

        mDatePicker = new DatePickerDialog(Add_Task_Activity.this, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker datePicker, int selected_year, int selected_month, int selected_day) {
                String date = selected_day+"-"+(selected_month+1)+"-"+selected_year;
             Toast.makeText(Add_Task_Activity.this, selected_day+"-"+(selected_month+1)+"-"+selected_year, Toast.LENGTH_SHORT).show();
                intent.putExtra(EXTRA_DATE, date);
            }

        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();


    }


    public void time(View view) {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);


        TimePickerDialog mTimePicker = new TimePickerDialog(Add_Task_Activity.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String time = selectedHour+":"+selectedMinute;
                intent.putExtra(EXTRA_TIME, time);
                Toast.makeText(Add_Task_Activity.this, selectedHour+":"+selectedMinute, Toast.LENGTH_SHORT).show();
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();





    }
}





