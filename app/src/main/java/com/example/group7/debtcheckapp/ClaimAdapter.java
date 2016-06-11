package com.example.group7.debtcheckapp;

/**
 * Adapter für eine ListView Claim
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.group7.debtcheckapp.Mock.Debt;
import java.util.ArrayList;

/**
 * @author Niklas Schlüter, André Käthner
 * @version 1.0
 */
public class ClaimAdapter extends BaseAdapter {

    private ArrayList<Debt> claimList;
    private LayoutInflater layoutInflater;

    /**
     * Konstruktor
     * @param context Context
     * @param debts ArrayList
     */
    public  ClaimAdapter(Context context, ArrayList<Debt> debts) {
        this.claimList = debts;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * GET-Methode für die größe der ArrayListe
     * @return int
     */
    @Override
    public int getCount(){
        if(claimList == null){
            return 0;
        }
        return claimList.size();
    }

    /**
     * GET-Methode für Id
     * @param position int
     * @return long
     */
    @Override
    public long getItemId(int position){
        return position;
    }

    /**
     * GET-Methode für die Claim
     * @param position int
     * @return Object
     */
    @Override
    public Object getItem(int position){
        return claimList.get(position);
    }

    /**
     * Methode für das Anzeigen der ListView
     * @param position int
     * @param convertView View
     * @param parent ViewGroup
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Checken ob es ein convertView gibt, wenn nicht einen erstellen
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_claim, null);
        }
        //TextView erstellen
        TextView tvClaim = (TextView) convertView.findViewById(R.id.Claim);
        //TextView füllen
        tvClaim.setText(String.valueOf(claimList.get(position).getAmount()));
        //TextView erstellen
        TextView tvDebtor = (TextView) convertView.findViewById(R.id.Debtor);
        //TextView füllen
        tvDebtor.setText(String.valueOf(claimList.get(position).getDebtor()));
        //TextView erstellen
        TextView tvReason = (TextView) convertView.findViewById(R.id.ClaimReason);
        //TextView füllen
        tvReason.setText(String.valueOf(claimList.get(position).getReason()));
        //return convertView
        return convertView;
    }
}
