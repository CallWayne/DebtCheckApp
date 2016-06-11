package com.example.group7.debtcheckapp;

/**
 * Adapter für eine ListView Debt
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
public class DebtAdapter extends BaseAdapter {

    private ArrayList<Debt> debtList;
    private LayoutInflater layoutInflater;

    /**
     * Konstruktor
     * @param context Context
     * @param debts ArrayList
     */
    public DebtAdapter(Context context, ArrayList<Debt> debts) {
        this.debtList = debts;
        this.layoutInflater = LayoutInflater.from(context);

    }

    /**
     * GET-Methode für die größe der ArrayListe
     * @return int
     */
    @Override
    public int getCount(){
        if(debtList == null){
            return 0;
        }
        return debtList.size();
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
        return debtList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.item_debt, null);
        }
        //TextView erstellen
        TextView tvDebt = (TextView) convertView.findViewById(R.id.Debt);
        //TextView füllen
        tvDebt.setText(String.valueOf(debtList.get(position).getAmount()));
        //TextView erstellen
        TextView tvCreditor = (TextView) convertView.findViewById(R.id.Creditor);
        //TextView füllen
        tvCreditor.setText(String.valueOf(debtList.get(position).getCreditor()));
        //TextView erstellen
        TextView tvReason = (TextView) convertView.findViewById(R.id.DebtReason);
        //TextView füllen
        tvReason.setText(String.valueOf(debtList.get(position).getReason()));
        //return convertView
        return convertView;
    }
}

