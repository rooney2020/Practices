package com.tsdl.practices.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;

import com.tsdl.practices.R;
import com.tsdl.practices.databinding.ActivityCalculatorBinding;
import com.tsdl.practices.util.Constants;

public class CalculatorActivity extends BaseActivity implements View.OnClickListener {

    private ActivityCalculatorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator);
        initView();
    }

    private void initView() {
        binding.btnMore.setOnClickListener(this);
        binding.btnInput1.setOnClickListener(this);
        binding.btnInput2.setOnClickListener(this);
        binding.btnInput3.setOnClickListener(this);
        binding.btnInput4.setOnClickListener(this);
        binding.btnInput5.setOnClickListener(this);
        binding.btnInput6.setOnClickListener(this);
        binding.btnInput7.setOnClickListener(this);
        binding.btnInput8.setOnClickListener(this);
        binding.btnInput9.setOnClickListener(this);
        binding.btnInput0.setOnClickListener(this);
        binding.btnInput00.setOnClickListener(this);
        binding.btnInputPoint.setOnClickListener(this);
        binding.btnInputPlus.setOnClickListener(this);
        binding.btnInputMinus.setOnClickListener(this);
        binding.btnInputTimes.setOnClickListener(this);
        binding.btnInputDivide.setOnClickListener(this);
        binding.btnInputDelete.setOnClickListener(this);
        binding.btnInputClear.setOnClickListener(this);
        binding.btnInputMod.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        if (view instanceof Button) {
            String displayString = binding.tvDisplay.getText().toString();
            String keyValue = ((Button) view).getText().toString();
            toast(keyValue);
            if (Constants.KEY_VALUE_CLEAR.equals(keyValue)) {
                binding.tvDisplay.setText(Constants.STRING_EMPTY);
                return;
            }
            if (Constants.KEY_VALUE_DELETE.equals(keyValue)) {
                if (displayString.length() > 0) {
                    binding.tvDisplay.setText(displayString.substring(0, displayString.length() - 1));
                }
                return;
            }
            if (Constants.KEY_VALUE_MORE.equals(keyValue)) {
                return;
            }
            if (Constants.KEY_VALUE_EQUAL.equals(keyValue)) {
                return;
            }
            binding.tvDisplay.setText(displayString + keyValue);
        }
    }

    public double calculate(String express) {
        express = "(5+3*(1+2))^2+0.5+1/2+(-10*3)";
        int leftBracketsNum = 0;

        return 0;
    }
}