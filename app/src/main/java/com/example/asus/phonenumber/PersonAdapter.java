package com.example.asus.phonenumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    private int resourceId;

    public PersonAdapter(Context context, int textViewResourceId, List<Person> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        Person person = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.personName = (TextView) view.findViewById(R.id.person_name);
            viewHolder.personNumber = (TextView) view.findViewById(R.id.person_number);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.personName.setText(person.getName());
        viewHolder.personNumber.setText(person.getNumber());
        return view;
    }

    private class ViewHolder {
        TextView personName;
        TextView personNumber;
    }
}
