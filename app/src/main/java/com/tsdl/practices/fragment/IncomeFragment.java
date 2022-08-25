package com.tsdl.practices.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tsdl.common.entity.BillType;
import com.tsdl.common.sdk.base.SingleToast;
import com.tsdl.common.sdk.grid.BaseGridViewAdapter;
import com.tsdl.practices.R;
import com.tsdl.practices.databinding.FragmentIncomeBinding;
import com.tsdl.practices.manager.DataManager;

import java.util.List;

public class IncomeFragment extends Fragment implements View.OnClickListener {

    private FragmentIncomeBinding mBinding;
    private List<BillType> mInComeBillTypes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        mBinding.etAmount.clearFocus();
        mBinding.etAmount.setOnClickListener((v) -> {
            SingleToast.makeText(getContext(), "etAmount onclick", SingleToast.LENGTH_SHORT).show();
            if (!mBinding.etAmount.isInputMethodTarget()) {
                mBinding.etAmount.clearFocus(); // ...other actions
            }
        });

        mInComeBillTypes = DataManager.getsInstance(getContext()).getBillTypeListByType(true);
        BaseGridViewAdapter baseGridViewAdapter = new BaseGridViewAdapter(getContext(), mInComeBillTypes, 2);
        mBinding.tvTypeName.setText(((BillType) baseGridViewAdapter.getItem(2)).getName());
        mBinding.billTypeGrid.setAdapter(baseGridViewAdapter);
        mBinding.billTypeGrid.setOnItemClickListener((parent, view, position, id) -> {
            BillType selectedData = (BillType) baseGridViewAdapter.getItem(position);
            if (selectedData != null) {
                mBinding.tvTypeName.setText(selectedData.getName());
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_income, container, false);
        initView();
        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        SingleToast.makeText(getContext(), "fragment onclick", SingleToast.LENGTH_SHORT).show();
    }
}