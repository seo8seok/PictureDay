package com.example.user.picture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.user.picture.forecast.Title;

import java.util.List;

/**
 * Created by user on 2017-03-23.
 */

public class ExpAdater extends BaseExpandableListAdapter {

    private List<Title> GroupData;

    public ExpAdater(List<Title> groupData) {
        GroupData = groupData;
    }

    @Override
    public int getGroupCount() {
        return GroupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return GroupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return 1;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_exp, parent, false);
            viewHolder.groupText = (TextView) convertView.findViewById(R.id.item_group);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.groupText.setText(GroupData.get(groupPosition).getDtTxt());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_child, parent, false);

            viewHolder.weatherText = (TextView) convertView.findViewById(R.id.weater_item);
            viewHolder.tempText = (TextView) convertView.findViewById(R.id.temp_item);

            viewHolder.speedText = (TextView) convertView.findViewById(R.id.speed_item);

            viewHolder.atmoText = (TextView) convertView.findViewById(R.id.atmo_item);
            viewHolder.humText = (TextView) convertView.findViewById(R.id.hum_item);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.weatherText.setText(GroupData.get(groupPosition).getWeather().get(childPosition).getMain());

        viewHolder.tempText.setText(GroupData.get(groupPosition).getMain().getTemp());

        viewHolder.speedText.setText(GroupData.get(groupPosition).getWind().getSpeed());

        viewHolder.atmoText.setText(GroupData.get(groupPosition).getMain().getPressure());
        viewHolder.humText.setText(GroupData.get(groupPosition).getMain().getHumidity());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ViewHolder {
        private TextView groupText;

        private TextView weatherText;
        private TextView tempText;
        private TextView speedText;
        private TextView atmoText;
        private TextView humText;
    }
}