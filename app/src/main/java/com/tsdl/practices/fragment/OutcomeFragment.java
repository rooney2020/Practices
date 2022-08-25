package com.tsdl.practices.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tsdl.common.entity.Bill;
import com.tsdl.common.entity.BillType;
import com.tsdl.common.sdk.base.SingleToast;
import com.tsdl.common.sdk.grid.BaseGridViewAdapter;
import com.tsdl.common.util.CommonUtils;
import com.tsdl.practices.R;
import com.tsdl.practices.databinding.FragmentOutcomeBinding;
import com.tsdl.practices.manager.DataManager;

import java.util.List;

public class OutcomeFragment extends Fragment implements View.OnClickListener {

    private FragmentOutcomeBinding mBinding;
    private List<BillType> mOutComeBillTypes;
    private int mSelectedPosition;

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

        mOutComeBillTypes = DataManager.getsInstance(getContext()).getBillTypeListByType(false);
        BaseGridViewAdapter baseGridViewAdapter = new BaseGridViewAdapter(getContext(), mOutComeBillTypes, mSelectedPosition);
        mBinding.tvTypeName.setText(((BillType) baseGridViewAdapter.getItem(mSelectedPosition)).getName());
        mBinding.billTypeGrid.setAdapter(baseGridViewAdapter);
        mBinding.billTypeGrid.setOnItemClickListener((parent, view, position, id) -> {
            mSelectedPosition = position;
            BillType selectedData = (BillType) baseGridViewAdapter.getItem(position);
            if (selectedData != null) {
                mBinding.tvTypeName.setText(selectedData.getName());
            }
        });
        mBinding.btnOk.setOnClickListener(v -> {
            if (mBinding.etAmount.getText().toString().isEmpty()) {
                SingleToast.makeText(getContext(), "请输入金额").show();
                return;
            }
            int type = ((BillType) baseGridViewAdapter.getItem(mSelectedPosition)).getId();
            float amount = -Float.parseFloat(mBinding.etAmount.getText().toString());
            String detail = mBinding.etDetail.getText().toString();
            String time = CommonUtils.getTime();
            Bill bill = new Bill(type, amount, detail, time);
            DataManager.getsInstance(getContext()).insertBill(bill);
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_outcome, container, false);
        initView();
        return mBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        SingleToast.makeText(getContext(), "fragment onclick", SingleToast.LENGTH_SHORT).show();
    }
}