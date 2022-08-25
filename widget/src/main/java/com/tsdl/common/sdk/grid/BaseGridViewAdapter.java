package com.tsdl.common.sdk.grid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsdl.common.entity.BillType;
import com.tsdl.common.sdk.R;

import java.util.ArrayList;
import java.util.List;

public class BaseGridViewAdapter extends BaseAdapter {

    public static final int UNDEFINED = -1;

    private final Context mContext;
    private List<BillType> dataList = new ArrayList<>();
    private int mSelectedPosition;

    public BaseGridViewAdapter(Context context, List<BillType> dataList, int selectedPosition) {
        mContext = context;
        this.dataList = dataList;
        mSelectedPosition = selectedPosition;
    }

    public void setData(List<BillType> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList == null || position >= dataList.size() ? null : dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_grid_item, null);
        }

        ImageView mAppIconBgImageView = (ImageView) convertView.findViewById(R.id.iv_image_bg);
        ImageView mAppIconImageView = (ImageView) convertView.findViewById(R.id.iv_image);
        TextView mAppNameTextView = (TextView) convertView.findViewById(R.id.tv_title);

        mAppIconImageView.setImageResource(dataList.get(position).getIconId());
        mAppNameTextView.setText(dataList.get(position).getName());

        if (mSelectedPosition != UNDEFINED && position == mSelectedPosition) {
            mAppIconBgImageView.setBackgroundResource(R.drawable.bg_icon);
        }
        return convertView;
    }


}
