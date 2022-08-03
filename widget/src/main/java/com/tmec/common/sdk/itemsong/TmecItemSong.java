package com.tmec.common.sdk.itemsong;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.tmec.common.sdk.R;
import com.tmec.common.sdk.base.SkinManager;
import com.tmec.common.sdk.base.SkinMode;
import com.tmec.common.sdk.base.TmecImageView;
import com.tmec.common.sdk.textview.TmecTextExplainTitle;
import com.tmec.common.sdk.textview.TmecTextSmallTitle;

public class TmecItemSong extends RelativeLayout implements SkinManager.OnSkinModeChangeListener {

    private TextView mTextNumber;
    private TmecTextSmallTitle mTextSongName;
    private TmecTextExplainTitle mTextSinger;
    private TextView mTextScore;
    private TmecImageView mBottomLine;

    private TmecImageView mIconRight;
    private TmecImageView mIconLeft;
    private RelativeLayout mRootLayout;

    public TmecItemSong(Context context) {
        this(context, null);
    }

    public TmecItemSong(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecItemSong(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecItemSong(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
        initAttrs(attrs);
    }

    private void initView() {
        mRootLayout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.tmec_item_song, this, true);
        mTextSongName = mRootLayout.findViewById(R.id.tv_song_name);
        mTextScore = mRootLayout.findViewById(R.id.tv_score);
        mTextSinger = mRootLayout.findViewById(R.id.tv_singer);
        mTextNumber = mRootLayout.findViewById(R.id.tv_song_number);
        mIconRight = mRootLayout.findViewById(R.id.iv_song_icon_right);
        mIconLeft = mRootLayout.findViewById(R.id.iv_song_icon_left);
        mBottomLine = mRootLayout.findViewById(R.id.item_song_bottom_line);
    }

    private void initAttrs(AttributeSet attrs) {
        SkinManager.Singleton.getInstance().register(this);
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecItemSong);
            mTextNumber.setText(array.getString(R.styleable.TmecItemSong_tmec_item_song_number));
            mTextScore.setText(array.getString(R.styleable.TmecItemSong_tmec_item_song_score));
            mTextSinger.setText(array.getString(R.styleable.TmecItemSong_tmec_item_song_singer));
            mTextSongName.setText(array.getString(R.styleable.TmecItemSong_tmec_item_song_songname));
            mIconLeft.setImageBg(array.getResourceId(R.styleable.TmecItemSong_tmec_icon_left_drawable, R.drawable.icon_setting_normal));
            mIconRight.setImageBg(array.getResourceId(R.styleable.TmecItemSong_tmec_icon_right_drawable, R.drawable.icon_setting_delete));
            mIconLeft.setImageNightBg(array.getResourceId(R.styleable.TmecItemSong_tmec_icon_left_night_drawable, R.drawable.icon_setting_normal_night));
            mIconRight.setImageNightBg(array.getResourceId(R.styleable.TmecItemSong_tmec_icon_right_night_drawable, R.drawable.icon_setting_delete_night));
            mIconLeft.setVisibility(array.getBoolean(R.styleable.TmecItemSong_tmec_item_song_show_left_icon, true) ? View.VISIBLE : View.GONE);
            mBottomLine.setVisibility(array.getBoolean(R.styleable.TmecItemSong_tmec_item_song_show_bottom_line, false) ? View.VISIBLE : View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
        updateLayout();
    }

    private void updateLayout() {
        if (SkinManager.Singleton.getInstance().isNight()) {
            mTextNumber.setBackgroundResource(R.drawable.bg_icon_number_night);
            mTextScore.setBackgroundResource(R.drawable.bg_tab_normal_night);
            mRootLayout.setBackgroundResource(R.drawable.slt_item_song_bg_night);
        } else {
            mTextNumber.setBackgroundResource(R.drawable.bg_icon_number);
            mTextScore.setBackgroundResource(R.drawable.bg_tab_normal);
            mRootLayout.setBackgroundResource(R.drawable.slt_item_song_bg);
        }
    }


    /**
     * set number.
     *
     * @param number number
     */
    public void setNumber(String number) {
        if (number != null) {
            mTextNumber.setText(number);
        }
    }

    /**
     * set singerName.
     *
     * @param singerName singerName
     */
    public void setSinger(String singerName) {
        if (singerName != null) {
            mTextSinger.setText(singerName);
        }
    }

    /**
     * set score.
     *
     * @param score score
     */
    public void setScore(String score) {
        if (score != null) {
            mTextScore.setText(score);
        }
    }

    /**
     * set songName.
     *
     * @param songName songName
     */
    public void setSongName(String songName) {
        if (songName != null) {
            mTextSongName.setText(songName);
        }
    }

    /**
     * set left icon drawable when the theme is light mode.
     *
     * @param drawableId drawableId
     */
    public void setLeftIcon(@DrawableRes int drawableId) {
        if (drawableId != 0) {
            mIconLeft.setImageBg(drawableId);
        }
    }

    /**
     * set left icon drawable when the theme is night mode.
     *
     * @param drawableId drawableId
     */
    public void setNightLeftIcon(@DrawableRes int drawableId) {
        if (drawableId != 0) {
            mIconLeft.setImageNightBg(drawableId);
        }
    }

    /**
     * set right icon drawable when the theme is light mode.
     *
     * @param drawableId drawableId
     */
    public void setRightIcon(@DrawableRes int drawableId) {
        if (drawableId != 0) {
            mIconRight.setImageBg(drawableId);
        }
    }

    /**
     * set right icon drawable when the theme is night mode.
     *
     * @param drawableId drawableId
     */
    public void setNightRightIcon(@DrawableRes int drawableId) {
        if (drawableId != 0) {
            mIconRight.setImageNightBg(drawableId);
        }
    }

    /**
     * set left icon clickListener.
     *
     * @param listener listener
     */
    public void setOnLeftIconClick(View.OnClickListener listener) {
        if (listener != null) {
            mIconLeft.setOnClickListener(listener);
        }
    }

    /**
     * set right icon clickListener.
     *
     * @param listener listener
     */
    public void setOnRightIconClick(View.OnClickListener listener) {
        if (listener != null) {
            mIconRight.setOnClickListener(listener);
        }
    }

    /**
     * set show left icon or not.
     *
     * @param isShow isShow
     */
    public void setShowLeftIcon(boolean isShow) {
        mIconLeft.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    /**
     * set show bottom line or not.
     *
     * @param isShow isShow
     */
    public void setShowBottomLine(boolean isShow) {
        mBottomLine.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }


    @Override
    public void onSkinModeChange(SkinMode skinMode) {
        updateLayout();
    }
}
