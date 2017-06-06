package com.tracker.student.student99.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tracker.student.student99.R;
import com.tracker.student.student99.adapters.FeeStrutureAdapter;
import com.tracker.student.student99.appconstants.KeyConstants;
import com.tracker.student.student99.models.FeeDetails;
import com.tracker.student.student99.models.Student;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class FeeAdditionActivity extends Activity {
    EditText detailsInfo;
    EditText amountInfo;
    Button addBtn;
    ListView listView;
    List<FeeDetails> feeDetailses;
    TextView sectionDetailsTvId;
    Realm realm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_addition);
        detailsInfo = (EditText)findViewById(R.id.fee_details_id);
        amountInfo = (EditText)findViewById(R.id.amount_id);
        addBtn = (Button)findViewById(R.id.add_money_id);
        listView = (ListView)findViewById(R.id.list_view);
        sectionDetailsTvId = (TextView)findViewById(R.id.section_details_id);
        final Long id = getIntent().getExtras().getLong(KeyConstants.STD_ID);
        final String name = getIntent().getExtras().getString(KeyConstants.STD_NAME);
        final String section = getIntent().getExtras().getString(KeyConstants.STD_SEC);
        realm = Realm.getDefaultInstance();
        sectionDetailsTvId.setText(section+" fee details");
        getFeeStructure(section);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feeDetails = detailsInfo.getText().toString();
                String amountS = amountInfo.getText().toString();
                amountInfo.setText("");
                detailsInfo.setText("");

                if(feeDetails != null && feeDetails.length() != 0 && amountS != null && amountS.length() != 0)
                {
                    Float amount = Float.parseFloat(amountS);
                        realm.beginTransaction();
                        FeeDetails transaction = realm.createObject(FeeDetails.class);
                        transaction.setFeeDetails(feeDetails);
                        transaction.setAmount(amount);
                        transaction.setSection(section);
                        realm.commitTransaction();
                        getFeeStructure(section);

                }
            }
        });
    }
    private void getFeeStructure(String section)
    {

        RealmResults<FeeDetails> users = realm.where(FeeDetails.class).equalTo("section",section).findAll();
        feeDetailses = users;
        Log.i("one","one");
        if(feeDetailses != null && feeDetailses.size() != 0)
        {
            Log.i("one","TWo");
            FeeStrutureAdapter adapter = new FeeStrutureAdapter(this,feeDetailses);
            listView.setAdapter(adapter);
        }
    }
}
