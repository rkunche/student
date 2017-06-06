package com.tracker.student.student99.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.tracker.student.student99.R;
import com.tracker.student.student99.activities.FeeAdditionActivity;
import com.tracker.student.student99.activities.PayMentDetailsActivity;
import com.tracker.student.student99.appconstants.KeyConstants;
import com.tracker.student.student99.models.Student;

import java.util.List;


public class StudentAdapter extends BaseAdapter {
     List<Student> studentList;
    LayoutInflater inflater;
    Context context;

    public StudentAdapter(List<Student> studentList, Context context)
    {
        this.studentList = studentList;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.search_student_layout,null);
            viewHolder.nameTv = (TextView) view.findViewById(R.id.name_id);
            viewHolder.numberTv = (TextView) view.findViewById(R.id.number_id);
            viewHolder.addPaymentBtn = (Button) view.findViewById(R.id.my_payments_id);
            viewHolder.updateFee = (Button) view.findViewById(R.id.update_fee_id);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder)view.getTag();
        if(studentList.get(i).getName() !=null) {
            viewHolder.nameTv.setText(studentList.get(i).getName());
            viewHolder.numberTv.setText(studentList.get(i).getNumber()+"");
        }
        viewHolder.addPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Student student = studentList.get(i);
                Intent intent = new Intent(context,PayMentDetailsActivity.class);
                Log.i("info","info "+student.getId());
                intent.putExtra(KeyConstants.STD_ID,student.getId());
                intent.putExtra(KeyConstants.STD_NAME,student.getName());
                intent.putExtra(KeyConstants.STD_SEC,student.getSection());
                context.startActivity(intent);
            }
        });

        viewHolder.updateFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = studentList.get(i);
                Log.i("info","info "+student.getId());
                Intent intent = new Intent(context,FeeAdditionActivity.class);
                intent.putExtra(KeyConstants.STD_ID,student.getId());
                intent.putExtra(KeyConstants.STD_NAME,student.getName());
                intent.putExtra(KeyConstants.STD_SEC,student.getSection());
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }
    private static class ViewHolder
    {
        TextView nameTv;
        TextView numberTv;
        Button addPaymentBtn;
        Button updateFee;
    }
}
