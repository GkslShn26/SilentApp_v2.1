package com.example.gksls.silentapp_v2;

/**
 * Created by gksls on 20.12.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gksls on 11.12.2017.
 */
public class EventAdaptor extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Event> events;

    public EventAdaptor(Activity activity, List<Event> events) {
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.events = events;
    }

    @Override
    public int getCount() {
        return this.events.size();
    }

    @Override
    public Event getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;

        rowView = inflater.inflate(R.layout.event_layout,null);
        CheckBox pzt,sal,car,per,cum,cmt,paz;

        TextView tvNameSurname = (TextView) rowView.findViewById(R.id.tvNameSurname);
        TextView tvAge = (TextView) rowView.findViewById(R.id.tvAge);
        TextView tvSalary = (TextView) rowView.findViewById(R.id.tvSalary);
        //TextView tvGender = (TextView) rowView.findViewById(R.id.tvGender);
        //TextView tvEmail = (TextView) rowView.findViewById(R.id.tvEmail);
        //TextView tvGpa = (TextView) rowView.findViewById(R.id.tvGpa);

        pzt= (CheckBox) rowView.findViewById(R.id.checkBox7);
        sal= (CheckBox) rowView.findViewById(R.id.checkBox6);
        car= (CheckBox) rowView.findViewById(R.id.checkBox5);
        per= (CheckBox) rowView.findViewById(R.id.checkBox4);
        cum= (CheckBox) rowView.findViewById(R.id.checkBox3);
        cmt= (CheckBox) rowView.findViewById(R.id.checkBox2);
        paz= (CheckBox) rowView.findViewById(R.id.checkBox);




        Event event = events.get(position);

        if(event.getPzt_true()==1){
            pzt.setChecked(true);
        }

        if(event.getSal_true()==1){
            sal.setChecked(true);
        }

        if(event.getCar_true()==1){
            car.setChecked(true);
        }

        if(event.getPer_true()==1){
            per.setChecked(true);
        }

        if(event.getCum_true()==1){
            cum.setChecked(true);
        }

        if(event.getCmt_true()==1){
            cmt.setChecked(true);
        }

        if(event.getPaz_true()==1){
            paz.setChecked(true);
        }

        tvNameSurname.setText(event.getEventName());
        tvAge.setText(event.getVibrate_start_hours().toString()+":"+event.getVibrate_start_minute().toString());
        tvSalary.setText(event.getVibrate_off_hours().toString()+":"+event.getVibrate_off_minute().toString());
        //tvGender.setText(event.getGender());
        //tvEmail.setText(event.getEmail());
        //tvGpa.setText(event.getGpa());



        return rowView;
    }
}