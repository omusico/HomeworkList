package com.example.asus.phonenumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class HomeworkAdapter extends ArrayAdapter<Homework> {

    private int resourceId;

    public HomeworkAdapter(Context context, int textViewResourceId, List<Homework> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        Homework homework = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.date = (TextView) view.findViewById(R.id.item_data);
            viewHolder.homework = (TextView) view.findViewById(R.id.item_homework);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.date.setText(homework.getName());
        viewHolder.homework.setText(homework.getNumber());
        return view;
    }

    private class ViewHolder {
        TextView date;
        TextView homework;
    }
}
