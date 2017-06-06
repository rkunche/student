package com.tracker.student.student99.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tracker.student.student99.R;
import com.tracker.student.student99.models.Student;

import io.realm.Realm;


public class StudentCreateActivity extends Activity {
    Realm realm;
    EditText name;
    EditText ageEdtText;
    EditText fee;
    EditText number;
    EditText section;
    EditText rollNoEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_creator);
        realm = Realm.getDefaultInstance();
        Button button = (Button)findViewById(R.id.create_id);
        name = (EditText) findViewById(R.id.name_id);
        ageEdtText = (EditText) findViewById(R.id.age_id);
        fee = (EditText) findViewById(R.id.fee_id);
        number = (EditText) findViewById(R.id.num_id);
        section = (EditText) findViewById(R.id.sec_id);
        rollNoEdit = (EditText) findViewById(R.id.roll_num_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nameS = name.getText().toString();
               String ageS = ageEdtText.getText().toString();
                String feeS = fee.getText().toString();
                String numberS = number.getText().toString();
                String sectionS = section.getText().toString();
                String rollNumberS = rollNoEdit.getText().toString();

                long id = Long.parseLong(rollNumberS);
                int age = Integer.parseInt(ageS);
                long numberE = Long.parseLong(numberS);
                Student student = new Student();
                student.setName(nameS);
                student.setSection(sectionS);
                student.setFee(Float.parseFloat(feeS));
                student.setId(id);
                student.setAge(age);
                student.setNumber(numberE);
                Log.i("saved naumber","number "+numberE);
                createNewStudent(student);

                name.setText("");
                ageEdtText.setText("");
                fee.setText("");
                number.setText("");
                section.setText("");
                rollNoEdit.setText("");
            }
        });
    }

    private void createNewStudent(Student student)
    {
        realm.beginTransaction();
        Student transaction = realm.createObject(Student.class);
        transaction.setName(student.getName());
        transaction.setSection(student.getSection());
        transaction.setFee(student.getFee());
        transaction.setId(student.getId());
        transaction.setNumber(student.getNumber());
        realm.commitTransaction();
    }
}
