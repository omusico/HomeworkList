package com.example.asus.phonenumber;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Homework> homeworkList = new ArrayList<Homework>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getPerson();
        HomeworkAdapter adapter = new HomeworkAdapter(MainActivity.this,R.layout.homework_item, homeworkList);
        ListView listView = (ListView) findViewById(R.id.student_list);
        listView.setAdapter(adapter);
        TextView textView = (TextView) findViewById(R.id.main_create);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });
    }

    //向List里添加内容.
    public void getPerson() {
        UserDatabase userDatabase = new UserDatabase(MainActivity.this, "Homework.db", null, 2);
        SQLiteDatabase db = userDatabase.getWritableDatabase();
        Cursor cursor = db.query("Homework",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                String time = cursor.getString(cursor.getColumnIndex("date"));
                String work = cursor.getString(cursor.getColumnIndex("homework"));
                Homework homework = new Homework("截至日期: " + time, "内容: " + work);
                homeworkList.add(homework);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
