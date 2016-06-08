package com.example.group7.debtcheckapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.group7.debtcheckapp.Mock.Debt;

import java.util.ArrayList;

public class ClaimAdapter extends BaseAdapter {
    private ArrayList<Debt> claimList;
    private LayoutInflater layoutInflater;

    public  ClaimAdapter(Context context, ArrayList<Debt> debts) {
        this.claimList = debts;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount(){
        if(claimList == null){
            return 0;
        }
        return claimList.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return claimList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_claim, null);
        }
        // Lookup view for data population
        TextView tvClaim = (TextView) convertView.findViewById(R.id.Claim);
        // Populate the data into the template view using the data object
        tvClaim.setText(String.valueOf(claimList.get(position).getAmount()));
        // Return the completed view to render on screen
        TextView tvDebtor = (TextView) convertView.findViewById(R.id.Debtor);
        // Populate the data into the template view using the data object
        tvDebtor.setText(String.valueOf(claimList.get(position).getDebtor()));

        TextView tvReason = (TextView) convertView.findViewById(R.id.ClaimReason);
        // Populate the data into the template view using the data object
        tvReason.setText(String.valueOf(claimList.get(position).getReason()));
        return convertView;
    }
}
