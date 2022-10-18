package com.malishalakshanrosa.educationtaskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class CompletedTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadCompletedTasks();
    }

    public void loadCompletedTasks(){

        EducationTaskHelper dbHelper = new EducationTaskHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT _id, name, deadline, completedDate FROM tasks WHERE complete = '1' ";
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
        ListView completedTaskListView = findViewById(R.id.LV_completed_task_list);

        //7. Create adapter and set values to the list
//        int layout = android.R.layout.simple_list_item_1;
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, layout, list);

        int layout = R.layout.completed_one_task;
        String []columns = {"name", "deadline", "_id", "completedDate"};
        int[] labels = {R.id.completed_task_name, R.id.task_deadline_date, R.id.completed_task_id, R.id.task_competed_date};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, layout, cursor, columns,labels);


        //8. Set adapter to the ListView
        completedTaskListView.setAdapter(adapter);
    }

    public void toCreateTask(View v){
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }
}