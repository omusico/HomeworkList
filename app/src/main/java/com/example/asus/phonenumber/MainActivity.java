package com.example.asus.phonenumber;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Person> personList = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getPerson();
        PersonAdapter adapter = new PersonAdapter(MainActivity.this,R.layout.person_item,personList);
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
    public void getPerson() {
        UserDatabase userDatabase = new UserDatabase(MainActivity.this, "Person.db", null, 2);
        SQLiteDatabase db = userDatabase.getWritableDatabase();
        Cursor cursor = db.query("Person",null,null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                Person person = new Person(name, number);
                personList.add(person);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
