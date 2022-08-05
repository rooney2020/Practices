package com.tsdl.practices.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tsdl.practices.R;
import com.tsdl.practices.databinding.ItemBillBinding;
import com.tsdl.practices.model.Bill;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder> {

    private final Context mContext;
    private Bill[] data;

    public BillAdapter(Context context, Bill[] data) {
        this.mContext = context;
        this.data = data;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(Bill[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillAdapter.BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBillBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_bill, parent, false);
        return new BillAdapter.BillViewHolder(binding.getRoot());
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull BillAdapter.BillViewHolder holder, int position) {
        if (position >= data.length) {
            return;
        }
        ItemBillBinding binding = DataBindingUtil.getBinding(holder.itemView);
        Bill bill = data[position];
        if (binding != null) {
            binding.ivIcon.setImageResource(bill.getTypeIcon());
            if (bill.isDate()) {
                binding.tvDate.setVisibility(View.VISIBLE);
                binding.ivIcon.setVisibility(View.INVISIBLE);
                binding.tvDate.setText(bill.getDate() + " " + bill.getTotalAmount());
            } else {
                if (bill.isOutcome()) {
                    binding.layoutRight.setVisibility(View.VISIBLE);
                    binding.tvType.setText(bill.getTypeText());
                    binding.tvAmount.setText(String.format("%.2f", bill.getAmount()));
                    binding.tvDetail.setText(bill.getDetail());
                } else {
                    binding.layoutLeft.setVisibility(View.VISIBLE);
                    binding.tvTypeLeft.setText(bill.getTypeText());
                    binding.tvAmountLeft.setText(String.format("%.2f", bill.getAmount()));
                    binding.tvDetailLeft.setText(bill.getDetail());
                }
            }
        }

        holder.itemView.setOnClickListener(view -> {
            Bill demo = data[holder.getAdapterPosition()];
            Toast.makeText(mContext, demo.getTypeText() + " " + demo.getAmount(), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length;
    }

    static class BillViewHolder extends RecyclerView.ViewHolder {

        BillViewHolder(View view) {
            super(view);
        }
    }
}
