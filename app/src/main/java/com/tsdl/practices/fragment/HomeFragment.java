package com.tsdl.practices.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tsdl.practices.R;
import com.tsdl.practices.adapter.BillAdapter;
import com.tsdl.practices.databinding.FragmentHomeBinding;
import com.tsdl.practices.model.Bill;

import java.util.Calendar;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void initView() {

        mBinding.layoutIcon.setOnClickListener(this);

        int mount = getMonth();
        mBinding.tvIncome.setText(mount + getString(R.string.income_suffix));
        mBinding.tvOutcome.setText(mount + getString(R.string.outcome_suffix));
        mBinding.tvIncomeAmount.setText(String.format("%.2f", 10000f));
        mBinding.tvOutcomeAmount.setText(String.format("%.2f", 111.3f));

        Bill[] bills = new Bill[]{
                new Bill("今天", -73.3f),
                new Bill(0, 35.3f, "凉糕"),
                new Bill(1, 38f, "凉皮鸡架"),
                new Bill("昨天", -27.7f),
                new Bill(0, 27.7f, "鸭货"),
                new Bill("2日", 9989.7f),
                new Bill(3, 10000f, "7月份工资"),
                new Bill(5, 10.3f, "其他物品")
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        BillAdapter billAdapter = new BillAdapter(getContext(), bills);
        mBinding.rvBill.setLayoutManager(layoutManager);
        mBinding.rvBill.setAdapter(billAdapter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initView();
        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.layout_icon) {
            Toast.makeText(getContext(), "onClick", Toast.LENGTH_SHORT).show();
        }
    }

    public int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }
}