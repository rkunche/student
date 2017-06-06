package com.tracker.student.student99.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tracker.student.student99.R;
import com.tracker.student.student99.models.FeeDetails;

import java.util.List;


public class FeeStrutureAdapter extends BaseAdapter {
    Context context;
    List<FeeDetails> feeDetailses;
    LayoutInflater inflater;
    public FeeStrutureAdapter(Context context,List<FeeDetails> feeDetailses)
    {
        this.context = context;
        this.feeDetailses = feeDetailses;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return feeDetailses.size();
    }

    @Override
    public Object getItem(int i) {
        return feeDetailses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.fee_struc_layout,null);
            viewHolder.details = (TextView) view.findViewById(R.id.details_id);
            viewHolder.amount = (TextView) view.findViewById(R.id.amout_id);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder)view.getTag();
        viewHolder.details.setText(feeDetailses.get(i).getFeeDetails());
        viewHolder.amount.setText(feeDetailses.get(i).getAmount()+"");
        return view;
    }
    private static class ViewHolder
    {
        TextView details;
        TextView amount;
    }
}
