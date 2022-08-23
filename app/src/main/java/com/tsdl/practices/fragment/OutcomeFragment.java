package com.tsdl.practices.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tmec.common.sdk.base.SingleToast;
import com.tsdl.practices.R;
import com.tsdl.practices.databinding.FragmentOutcomeBinding;

public class OutcomeFragment extends Fragment implements View.OnClickListener {

    private FragmentOutcomeBinding mBinding;

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