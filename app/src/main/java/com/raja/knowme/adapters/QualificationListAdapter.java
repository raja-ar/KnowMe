package com.raja.knowme.adapters;
/*
 * Copyright 2015 Azmeer Raja
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.raja.knowme.R;
import com.raja.knowme.variables.QualificationDetailsObject;

import java.util.ArrayList;

public class QualificationListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<QualificationDetailsObject> mData = new  ArrayList<QualificationDetailsObject>(0);

    public QualificationListAdapter(Context _context) {
        mInflater = LayoutInflater.from(_context);
    }

    public void setListData(ArrayList<QualificationDetailsObject> data) {
        this.mData = data;
    }

    public int getCount() {
        return mData.size();
    }

    public Object getItem(int position) {
        return mData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        /** Defining the custom list layout and attaching the display data to it */
        if (convertView == null) {
            convertView	= mInflater.inflate(R.layout.layout_listview_item, null);
            holder = new ViewHolder();
            holder.mHeaderText	= (TextView) convertView.findViewById(R.id.listview_header_text);
            holder.mDescriptionText	= (TextView) convertView.findViewById(R.id.listview_content_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mHeaderText.setText(mData.get(position).getQualificationName());
        holder.mDescriptionText.setText(mData.get(position).getQualificationDescription());
        return convertView;
    }

    static class ViewHolder {
        TextView mHeaderText, mDescriptionText;
    }

}
