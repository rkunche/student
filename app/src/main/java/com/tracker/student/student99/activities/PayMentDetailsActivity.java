package com.tracker.student.student99.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.tracker.student.student99.R;
import com.tracker.student.student99.adapters.PaymentsAdapter;
import com.tracker.student.student99.appconstants.KeyConstants;
import com.tracker.student.student99.models.FeeDetails;
import com.tracker.student.student99.models.Payments;
import com.tracker.student.student99.models.Student;

import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class PayMentDetailsActivity extends Activity {
    Realm realm;
    float totalFee;
    float paidAmount;
    TextView totalFeeVew;
    TextView paidId;
    Button payBtnId;
    Student currentStudent;
    EditText moneyEditId;
    TextView dueId;
    Calendar calendar;
    float due;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);
        final Long id = getIntent().getExtras().getLong(KeyConstants.STD_ID);
        final String name = getIntent().getExtras().getString(KeyConstants.STD_NAME);
        final String section = getIntent().getExtras().getString(KeyConstants.STD_SEC);
        ListView listView = (ListView)findViewById(R.id.list_item);
        List<Payments> paymentses = new ArrayList<>();
        realm = Realm.getDefaultInstance();
        getFee();
        getPaidAmount(id,name,section);
        totalFeeVew = (TextView)findViewById(R.id.totalFee);
        paidId = (TextView)findViewById(R.id.my_payments_id);
        payBtnId = (Button)findViewById(R.id.payment_id);
        dueId = (TextView)findViewById(R.id.due_id);
        moneyEditId = (EditText)findViewById(R.id.edit_amout_text_id);
        totalFeeVew.setText(totalFee+"");
        paidId.setText(paidAmount+"");
        due = totalFee - paidAmount;
        dueId.setText(due+"");
        calendar = Calendar.getInstance();
        paymentses = getPayments(id,name,section);
        PaymentsAdapter adapter = new PaymentsAdapter(this,paymentses);
        listView.setAdapter(adapter);
        payBtnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudentFee();

            }
        });
    }
    private void getFee()
    {
        RealmResults<FeeDetails> users = realm.where(FeeDetails.class).findAll();
        List<FeeDetails> feeDetailses = users;
        if(feeDetailses != null && feeDetailses.size() != 0)
        {
            for(FeeDetails feeDetails : feeDetailses)
            {
              totalFee+=feeDetails.getAmount();
            }
        }
    }

    private void getPaidAmount(long id,String name,String section)
    {
        currentStudent = realm.where(Student.class).equalTo("name",name).equalTo("id",id).equalTo("section",section).findFirst();
        paidAmount = currentStudent.getFee();
        Log.i("current student","name "+currentStudent.getName());
        Log.i("current student","Fee "+currentStudent.getFee());
    }
    private void addStudentFee()
    {
        String amount = moneyEditId.getText().toString();
        moneyEditId.setText("");
        if(amount !=null && amount.length() == 0)
        {
            return;
        }
        final float value = Float.parseFloat(amount);
        // Update person in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                currentStudent.setFee(currentStudent.getFee()+value);
            }
        });
        createPayment(currentStudent,value);
    }

    private void createPayment(Student student,float payment)
    {
        realm.beginTransaction();
        Payments transaction = realm.createObject(Payments.class);
        transaction.setName(student.getName());
        transaction.setSection(student.getSection());
        transaction.setPayment(payment);
        transaction.setPaymentDate(getCurrentDate());
        transaction.setStudentId(student.getId());
        realm.commitTransaction();
    }

    private String getCurrentDate()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd-mm-yyyy");
        System.out.println(cal.getTime());
        String formatted = format1.format(cal.getTime());
        System.out.println(formatted);
        return formatted;

    }
    private List<Payments> getPayments(long id,String name,String section)
    {
        RealmResults<Payments> paymentses = realm.where(Payments.class).equalTo("name",name).findAll();
        List<Payments> paymentses1 = paymentses;
        if(paymentses1 == null)
        {
            paymentses1 = new ArrayList<>();
        }
        Log.i("size","size is "+paymentses1.size());
        return paymentses1;
    }
}
