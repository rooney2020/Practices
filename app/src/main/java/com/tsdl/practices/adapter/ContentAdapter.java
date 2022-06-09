package com.tsdl.practices.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tsdl.practices.R;
import com.tsdl.practices.databinding.ItemContentListBinding;
import com.tsdl.practices.model.DemoDetail;
import com.tsdl.practices.util.Constants;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private final Context mContext;
    private DemoDetail[] data;

    public ContentAdapter(Context context, DemoDetail[] data) {
        this.mContext = context;
        this.data = data;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(DemoDetail[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContentListBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_content_list, parent, false);
        return new ContentViewHolder(binding.getRoot());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        if (position >= data.length) {
            return;
        }
        ItemContentListBinding binding = DataBindingUtil.getBinding(holder.itemView);
        DemoDetail demoDetail = data[position];
        if (binding != null) {
            if (demoDetail.getActivityClass() == null) {
                binding.tvItemName.setText(demoDetail.getTitleId());
            } else {
                binding.tvItemName.setText(Constants.STRING_BLANK + demoDetail.getTitleId());
            }
        }

        holder.itemView.setOnClickListener(view -> {
            DemoDetail demo = data[holder.getAdapterPosition()];
            if (demo.getActivityClass() != null) {
                mContext.startActivity(new Intent(mContext, demo.getActivityClass()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length;
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {

        ContentViewHolder(View view) {
            super(view);
        }
    }
}
