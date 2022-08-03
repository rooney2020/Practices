package com.tsdl.practices;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.popup.TmecDialogSmall;
import com.tsdl.practices.adapter.ContentAdapter;
import com.tsdl.practices.base.BaseActivity;
import com.tsdl.practices.databinding.ActivityMainBinding;
import com.tsdl.practices.model.DemoDetail;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private TmecDialogSmall customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ContentAdapter adapter = new ContentAdapter(this, DEMOS);
        binding.rvContent.setLayoutManager(layoutManager);
        binding.rvContent.setAdapter(adapter);
    }

    private final DemoDetail[] DEMOS = {
            new DemoDetail(R.string.app_name, R.string.blank, null),
            new DemoDetail(R.string.activity_calculator, R.string.blank, this, CalculatorActivity.class),
            new DemoDetail(R.string.activity_kotlin, R.string.blank, this, KotlinActivity.class),
            new DemoDetail(R.string.activity_game, R.string.blank, this, GameActivity.class),
            new DemoDetail(R.string.activity_music, R.string.blank, this, MusicActivity.class),
            new DemoDetail(R.string.activity_sms, R.string.blank, this, SmsActivity.class),
            new DemoDetail(R.string.item_callback, R.string.blank, null),
            new DemoDetail(R.string.btn_skin, R.string.blank, () -> SkinManager.Singleton.getInstance().nextSkinMode()),
            new DemoDetail(R.string.btn_dialog, R.string.blank, this::alertDialog),
    };

    private void alertDialog() {
        if (customDialog != null && customDialog.isShowing()) {
            customDialog.show();
            return;
        }
        customDialog = new TmecDialogSmall(MainActivity.this, TmecDialogSmall.DIALOG_TYPE_SYSTEM);
        customDialog.setTitle(getString(R.string.toast_title));
        customDialog.setMainContent(getString(R.string.toast_content));
        customDialog.setOnNegativeClickListener(view -> customDialog.dismiss());
        customDialog.setOnTapOutClickListener(view -> customDialog.dismiss());
//        customDialog.setOnTapOutClickListener(binding.rvContent, view -> customDialog.dismiss());
//        customDialog.setOnTapOutClickListener(binding.getRoot(), view -> customDialog.dismiss());
        customDialog.show();
    }

}