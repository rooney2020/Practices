package com.tsdl.practices.custom.itemsong;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecCheckBox;
import com.tsdl.practices.custom.base.TmecImageView;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;
import com.tsdl.practices.custom.textview.TmecTextExplainTitle;
import com.tsdl.practices.custom.textview.TmecTextSmallTitle;

public class TmecItemSongImageRight extends RelativeLayout implements ISkinChangedListener {

    private RelativeLayout mRootLayout;
    private TextView mScoreTextView;
    private TmecImageView mAddImage;
    private TmecCheckBox mCheckBox;
    private TmecTextSmallTitle mTextSongName;
    private TmecTextExplainTitle mTextSinger;
    private TmecImageView mSingerImage;

    public TmecItemSongImageRight(Context context) {
        this(context, null);
    }

    public TmecItemSongImageRight(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecItemSongImageRight(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecItemSongImageRight(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TmecSkinConfigurationManager.getInstance().register(this);
        initView();
        initAttrs(attrs);
        updateLayout();
    }

    private void initAttrs(AttributeSet attrs) {
        TmecSkinConfigurationManager.getInstance().register(this);
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecItemSongImageRight);
            mCheckBox.setVisibility(array.getBoolean(R.styleable.TmecItemSongImageRight_showCheckBox, false) ? VISIBLE : GONE);
            mAddImage.setVisibility(array.getBoolean(R.styleable.TmecItemSongImageRight_showCheckBox, false) ? GONE : VISIBLE);
            mTextSongName.setText(array.getString(R.styleable.TmecItemSongImageRight_song_name));
            mScoreTextView.setText(array.getString(R.styleable.TmecItemSongImageRight_song_score));
            mTextSinger.setText(array.getString(R.styleable.TmecItemSongImageRight_singer_name));
            mSingerImage.setBackground(array.getDrawable(R.styleable.TmecItemSongImageRight_right_image));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
        updateLayout();
    }

    private void initView() {
        mRootLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.tmec_item_song_image_right, this, true);
        mScoreTextView = mRootLayout.findViewById(R.id.tv_item_song_right_score);
        mAddImage = mRootLayout.findViewById(R.id.iv_add_song_left);
        mCheckBox = mRootLayout.findViewById(R.id.cb_item_song_left);
        mTextSinger = mRootLayout.findViewById(R.id.tv_item_song_right_singer);
        mTextSongName = mRootLayout.findViewById(R.id.tv_item_song_right_songname);
        mSingerImage = mRootLayout.findViewById(R.id.iv_item_song_right_singer_img);
    }

    private void updateLayout() {
        if (TmecSkinConfigurationManager.getInstance().isNight()) {
            mRootLayout.setBackgroundResource(R.drawable.bg_item_song_night);
            mScoreTextView.setBackgroundResource(R.drawable.bg_tab_night);
        } else {
            mRootLayout.setBackgroundResource(R.drawable.bg_item_song);
            mScoreTextView.setBackgroundResource(R.drawable.bg_tab);
        }
    }

    /**
     * set CheckedChangeListener on CheckBox.
     *
     * @param listener listener
     */
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        if (listener != null) {
            mCheckBox.setOnCheckedChangeListener(listener);
        }
    }

    /**
     * set click listener on icon.
     *
     * @param listener listener
     */
    public void setOnIconClickListener(OnClickListener listener) {
        if (listener != null) {
            mAddImage.setOnClickListener(listener);
        }
    }

    /**
     * @return mCheckBox.isChecked()
     */
    public boolean getChecked() {
        return mCheckBox.isChecked();
    }

    /**
     * set CheckBox checked or unChecked.
     *
     * @param checked checked
     */
    public void setChecked(boolean checked) {
        mCheckBox.setChecked(checked);
    }

    /**
     * set show CheckBox or icon.
     *
     * @param isShow isShow
     */
    public void setShowCheckBox(boolean isShow) {
        mAddImage.setVisibility(isShow ? GONE : VISIBLE);
        mCheckBox.setVisibility(isShow ? VISIBLE : GONE);
    }

    /**
     * set right image.
     *
     * @param drawable drawable
     */
    public void setRightImage(Drawable drawable) {
        mSingerImage.setBackground(drawable);
    }

    /**
     * set singerName.
     *
     * @param singerName singerName
     */
    public void setSinger(CharSequence singerName) {
        mTextSinger.setText(singerName);
    }

    /**
     * set score.
     *
     * @param score score
     */
    public void setScore(CharSequence score) {
        mScoreTextView.setText(score);
    }

    /**
     * set songName.
     *
     * @param songName songName
     */
    public void setSongName(CharSequence songName) {
        mTextSongName.setText(songName);
    }

    @Override
    public void onSkinChanged() {
        updateLayout();
    }
}
