package com.malishalakshanrosa.educationtaskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateTaskActivity extends AppCompatActivity {

    EditText et_task_name, et_deadline_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        et_task_name = findViewById(R.id.ET_task_name);
        et_deadline_date = findViewById(R.id.ET_deadline_date);

    }

    public void clear(){
        et_task_name.setText("");
        et_deadline_date.setText("");
    }

    public void saveTask(View v){

        String taskName = et_task_name.getText().toString();
        String deadlineDate = et_deadline_date.getText().toString();

        EducationTaskHelper dbHelper = new EducationTaskHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "INSERT INTO tasks (name, deadline, completedDate, complete) VALUES('"+taskName+"', '"+deadlineDate+"', '"+null+"', '0')";
        db.execSQL(sql);

        //4. Give a message
        Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();

        //5. Call clear function to clear EditText fields
        clear();

    }
}