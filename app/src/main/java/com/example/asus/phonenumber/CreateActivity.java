package com.example.asus.phonenumber;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    private EditText nameEdit;
    private EditText numberEdit;
    private UserDatabase dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_person);
        TextView save = (TextView) findViewById(R.id.create_save);
        dbUser = new UserDatabase(this, "Person.db", null, 2);
        nameEdit = (EditText) findViewById(R.id.create_name);
        numberEdit = (EditText) findViewById(R.id.create_number);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String number = numberEdit.getText().toString();
                if(name.isEmpty() || number.isEmpty()) {
                    Toast.makeText(CreateActivity.this, "错误: 姓名或者电话号码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteDatabase db = dbUser.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("name", name);
                    values.put("number", number);
                    db.insert("Person", null, values);
                    values.clear();
                    Toast.makeText(CreateActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
