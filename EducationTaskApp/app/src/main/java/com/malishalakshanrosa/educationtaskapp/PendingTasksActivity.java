package com.malishalakshanrosa.educationtaskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PendingTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_tasks);
    }

    public void toCreateTask(View v){
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPendingTasks();
    }

    public void loadPendingTasks(){

        EducationTaskHelper dbHelper = new EducationTaskHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT _id, name, deadline FROM tasks WHERE complete = '0' ";
        Cursor cursor = db.rawQuery(sql, null);


//        //4. Create an empty list object
//        List<String> list = new ArrayList<String>();
//
//        //5. Move cursor and get data to the created list
//        cursor.moveToFirst();
//        while (cursor.isAfterLast() == false){
//            String name = cursor.getString(1);
//            String date = cursor.getString(2);
//            list.add(name+ " ("+date+")");
//            cursor.moveToNext();
//        }

        //6. Get listView object
        ListView pendingTaskListView = findViewById(R.id.LV_pending_task_list);

        //7. Create adapter and set values to the list
//        int layout = android.R.layout.simple_list_item_1;
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, layout, list);

        int layout = R.layout.pending_one_task;
        String []columns = {"name", "deadline", "_id"};
        int[] labels = {R.id.pending_task_name, R.id.pending_task_deadling, R.id.pending_task_id};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, layout, cursor, columns,labels);


        //8. Set adapter to the ListView
        pendingTaskListView.setAdapter(adapter);
    }

    public void complete(View v){


            LinearLayout ll = (LinearLayout) v.getParent();
            TextView tv_TaskId = ll.findViewById(R.id.pending_task_id);
            String taskId = tv_TaskId.getText().toString();

            //1. Create openHelper with Context passed
            EducationTaskHelper dbHelper = new EducationTaskHelper(this);

            //2. Get writtable database object
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(new Date());

            //3. Create insert SQL and execute
            String sql = "UPDATE tasks SET completedDate='"+today+"', complete=1 WHERE _id = '"+taskId+"'";
            db.execSQL(sql);

            loadPendingTasks();

            Toast.makeText(this, "Task completed", Toast.LENGTH_SHORT).show();

    }

}