package com.tracker.student.student99.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tracker.student.student99.R;
import com.tracker.student.student99.models.Payments;

import java.util.List;
import java.util.zip.Inflater;


public class PaymentsAdapter extends BaseAdapter {
    Context context;
    List<Payments> paymentsList;
    LayoutInflater inflater;

    public PaymentsAdapter(Context context, List<Payments> paymentsList) {
        this.context = context;
        this.paymentsList = paymentsList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return paymentsList.size();
    }

    @Override
    public Object getItem(int i) {
        return paymentsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.list_layout, null);
            viewHolder.paymentDateTvId = (TextView) view.findViewById(R.id.date_id);
            viewHolder.paymentAmountId = (TextView) view.findViewById(R.id.amount_id);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        viewHolder.paymentAmountId.setText(paymentsList.get(i).getPayment()+"");
        viewHolder.paymentDateTvId.setText(paymentsList.get(i).getPaymentDate());
        return view;
    }

    private static class ViewHolder {
        TextView paymentDateTvId;
        TextView paymentAmountId;
    }
}
