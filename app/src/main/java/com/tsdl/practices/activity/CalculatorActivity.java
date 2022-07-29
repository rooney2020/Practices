package com.tsdl.practices.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;

import com.tsdl.practices.R;
import com.tsdl.practices.base.BaseActivity;
import com.tsdl.practices.databinding.ActivityCalculatorBinding;
import com.tsdl.practices.exception.CalculateException;
import com.tsdl.practices.model.Calculator;
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
        binding.btnInputPercent.setOnClickListener(this);
        binding.btnInputEqual.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        if (view instanceof Button) {
            String displayString = binding.tvDisplay.getText().toString();
            String keyValue = ((Button) view).getText().toString();
            String lastKey = Constants.STRING_EMPTY;
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
                binding.tvResult.setText(calculate(displayString));
                return;
            }
            if (displayString.length() > 0) {
                lastKey = String.valueOf(displayString.charAt(displayString.length() - 1));
            }
            if (Constants.CHARACTERS.contains(keyValue)) {
                if (displayString.length() > 0 && keyValue.equals(lastKey)) {
                    return;
                }
                if (displayString.length() > 0 && Constants.KEY_VALUE_MINUS.equals(keyValue)
                        && (Constants.KEY_VALUE_TIMES.equals(lastKey) || Constants.KEY_VALUE_DIVIDE.equals(lastKey))) {
                    binding.tvDisplay.setText(displayString + keyValue);
                    return;
                }
                if (displayString.length() > 0 && Constants.CHARACTERS.contains(lastKey)
                        && !Constants.KEY_VALUE_POINT.equals(lastKey) && !Constants.KEY_VALUE_PERCENT.equals(lastKey)) {
                    binding.tvDisplay.setText(displayString.substring(0, displayString.length() - 1) + keyValue);
                    return;
                }
                if (displayString.length() == 0 && !Constants.KEY_VALUE_MINUS.equals(keyValue)
                        && !Constants.KEY_VALUE_POINT.equals(keyValue)) {
                    return;
                }
            }
            binding.tvDisplay.setText(displayString + keyValue);
        }
    }

    public String calculate(String express) {
        try {
            Calculator calculator = new Calculator(express);
            return calculator.calculate();
        } catch (CalculateException calculateException) {
            calculateException.printStackTrace();
            binding.tvResult.setText(Constants.DISPLAY_ERROR);
        }
        return Constants.DISPLAY_ERROR;
    }
}