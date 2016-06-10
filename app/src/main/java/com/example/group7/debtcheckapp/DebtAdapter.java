package com.example.group7.debtcheckapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.group7.debtcheckapp.Mock.Account;
import com.example.group7.debtcheckapp.Mock.Debt;

import java.util.ArrayList;

public class DebtAdapter extends BaseAdapter {

    private ArrayList<Debt> debtList;
    private LayoutInflater layoutInflater;

    public DebtAdapter(Context context, ArrayList<Debt> debts) {
        this.debtList = debts;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount(){
        if(debtList == null){
            return 0;
        }
        return debtList.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return debtList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_debt, null);
        }
        // Lookup view for data population
        TextView tvDebt = (TextView) convertView.findViewById(R.id.Debt);
        // Populate the data into the template view using the data object
        tvDebt.setText(String.valueOf(debtList.get(position).getAmount()));
        // Return the completed view to render on screen
        TextView tvCreditor = (TextView) convertView.findViewById(R.id.Creditor);
        // Populate the data into the template view using the data object
        tvCreditor.setText(String.valueOf(debtList.get(position).getCreditor()));

        TextView tvReason = (TextView) convertView.findViewById(R.id.DebtReason);
        // Populate the data into the template view using the data object
        tvReason.setText(String.valueOf(debtList.get(position).getReason()));

        return convertView;
    }
}

