package com.example.asus.phonenumber;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateActivity extends Activity {

    private EditText nameEdit;
    private EditText numberEdit;
    private UserDatabase dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_homework);
        TextView save = (TextView) findViewById(R.id.create_save);
        //数据库相关
        dbUser = new UserDatabase(this, "Homework.db", null, 2);
        nameEdit = (EditText) findViewById(R.id.create_name);
        numberEdit = (EditText) findViewById(R.id.create_number);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = nameEdit.getText().toString();
                String homework = numberEdit.getText().toString();
                if(date.isEmpty() || homework.isEmpty()) {
                    Toast.makeText(CreateActivity.this, "错误: 请完整填写", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase db = dbUser.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("date", date);
                    values.put("homework", homework);
                    db.insert("Homework", null, values);
                    values.clear();
                    Toast.makeText(CreateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
