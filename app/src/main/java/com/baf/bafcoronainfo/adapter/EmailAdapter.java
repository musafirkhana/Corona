package com.baf.bafcoronainfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.model.Item;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EmailAdapter extends ArrayAdapter<Item> {
    Context context;

    ArrayList<Item> email_list = new ArrayList<>();
    public EmailAdapter(Context context, ArrayList<Item> objects) {
        super(context, R.layout.row_email, objects);
        email_list = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }


    static class ViewHolder {

        private TextView row_unitname;
        private TextView row_txt_email;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View v = convertView;

        if (v == null) {
            final LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_email, null);
            // convertView = _inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();

            holder.row_unitname = (TextView) v.findViewById(R.id.row_unitname);
            holder.row_txt_email = (TextView) v.findViewById(R.id.row_txt_email);


            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.row_unitname.setText(email_list.get(position).getUnit_name());
        holder.row_txt_email.setText(email_list.get(position).getEmail());


        return v;
    }



}