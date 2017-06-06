package com.tracker.student.student99;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tracker.student.student99.activities.PayMentDetailsActivity;
import com.tracker.student.student99.activities.StudentCreateActivity;
import com.tracker.student.student99.adapters.StudentAdapter;
import com.tracker.student.student99.models.Student;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
 Realm realm;
    EditText searchByNameEdTv;
    EditText searchByNumberTv;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.search_btn);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        searchByNameEdTv = (EditText)findViewById(R.id.search_by_name);
        searchByNumberTv = (EditText)findViewById(R.id.search_by_number);
        listView = (ListView)findViewById(R.id.list_view);
        realm = Realm.getDefaultInstance();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(MainActivity.this, StudentCreateActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, PayMentDetailsActivity.class);
//                startActivity(intent);
                getUser();
            }
        });


    }
    private void getUser()
    {
        String number = searchByNumberTv.getText().toString();
        List<Student> students = null;
        Log.i("number is"," "+number);
         if(number != null && number.length() != 0)
         {
             Long num = Long.parseLong(number);
             RealmResults<Student> users = realm.where(Student.class).equalTo("number", num).findAll();
             students = users;
             Log.i("nmber is ","hey "+students.size());
         }
         if(students == null)
         {
             String name = searchByNameEdTv.getText().toString();
             if(name != null && name.length() != 0) {
                 RealmResults<Student> users = realm.where(Student.class).equalTo("name", name).findAll();
                 students = users;
             }
         }
//        students = new ArrayList<>();
//        for(int i=0;i<5;i++)
//        {
//            Student student = new Student();
//            student.setName("hii "+i);
//            student.setFee(12323f+i);
//            students.add(student);
//        }
         if(students != null && students.size() != 0)
         {
             StudentAdapter studentAdapter = new StudentAdapter(students,this);
             listView.setAdapter(studentAdapter);
         }
    }
}
