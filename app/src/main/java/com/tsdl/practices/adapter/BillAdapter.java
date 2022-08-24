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

import com.tsdl.common.sdk.base.SingleToast;
import com.tsdl.practices.R;
import com.tsdl.practices.databinding.ItemBillBinding;
import com.tsdl.practices.manager.DataManager;
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
            if (bill.isDate()) {
                binding.tvDate.setVisibility(View.VISIBLE);
                binding.tvDateAmount.setVisibility(View.VISIBLE);
                binding.ivIconDate.setVisibility(View.VISIBLE);
                binding.ivIconBg.setVisibility(View.GONE);
                binding.ivIcon.setVisibility(View.GONE);
                binding.tvDate.setText(bill.getDate());
                binding.tvDateAmount.setText(String.valueOf(bill.getTotalAmount()));
            } else {
                binding.ivIcon.setImageResource(DataManager.getBillTypeById(bill.getType()).getIconId());
                if (!DataManager.getBillTypeById(bill.getType()).isIncome()) {
                    binding.layoutRight.setVisibility(View.VISIBLE);
                    binding.tvType.setText(DataManager.getBillTypeById(bill.getType()).getName());
                    binding.tvAmount.setText(String.format("%.2f", bill.getAmount()));
                    binding.tvDetail.setText(bill.getDetail());
                } else {
                    binding.layoutLeft.setVisibility(View.VISIBLE);
                    binding.tvTypeLeft.setText(DataManager.getBillTypeById(bill.getType()).getName());
                    binding.tvAmountLeft.setText(String.format("%.2f", bill.getAmount()));
                    binding.tvDetailLeft.setText(bill.getDetail());
                }
            }
        }

        holder.itemView.setOnClickListener(view -> {
            Bill demo = data[holder.getAdapterPosition()];
            SingleToast.makeText(mContext, DataManager.getBillTypeById(demo.getType()).getName() + " "
                    + demo.getAmount(), Toast.LENGTH_SHORT).show();
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
