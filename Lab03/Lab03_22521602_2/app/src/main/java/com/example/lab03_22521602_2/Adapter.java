package com.example.lab03_22521602_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private List<Contact> contactList;

    public Adapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            // Inflate the custom layout for each item
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
            holder = new ViewHolder();
            holder.tvUser = convertView.findViewById(R.id.tv_user);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the current contact and set the name
        Contact contact = contactList.get(position);
        holder.tvUser.setText(contact.getName());

        return convertView;
    }

    private static class ViewHolder {
        TextView tvUser;
    }
}
