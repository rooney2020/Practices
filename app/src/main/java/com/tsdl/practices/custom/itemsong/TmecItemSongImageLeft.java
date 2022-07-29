package com.tsdl.practices.custom.itemsong;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecImageView;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;
import com.tsdl.practices.custom.textview.TmecTextExplainTitle;
import com.tsdl.practices.custom.textview.TmecTextTipsTitle;

public class TmecItemSongImageLeft extends RelativeLayout implements ISkinChangedListener {

    private static final int RANK_FIRST = 1;
    private static final int RANK_SECOND = 2;
    private static final int RANK_THIRD = 3;

    private TmecImageView mRankImage;
    private TmecImageView mAlbumImage;
    private TmecTextExplainTitle mSongName;
    private TmecTextTipsTitle mSingerName;

    public TmecItemSongImageLeft(Context context) {
        this(context, null);
    }

    public TmecItemSongImageLeft(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TmecItemSongImageLeft(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TmecItemSongImageLeft(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TmecSkinConfigurationManager.getInstance().register(this);
        initView();
        initAttrs(attrs);
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.tmec_item_song_image_left, this, true);
        mSingerName = findViewById(R.id.singer_name);
        mSongName = findViewById(R.id.song_name);
        mAlbumImage = findViewById(R.id.album_image);
        mRankImage = findViewById(R.id.rank_image);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(attrs, R.styleable.TmecItemSongImageLeft);
                setRankDrawable(array.getInteger(
                        R.styleable.TmecItemSongImageLeft_image_left_song_rank, RANK_FIRST));
                mAlbumImage.setImageDrawable(array.getDrawable(R.styleable.TmecItemSongImageLeft_image_left_album_image));
                mSongName.setText(array.getString(R.styleable.TmecItemSongImageLeft_image_left_song_name));
                mSingerName.setText(array.getString(R.styleable.TmecItemSongImageLeft_image_left_singer_name));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                array.recycle();
            }
        }
    }

    /**
     * Set rank image
     * @param rank rank number 1,2,3
     */
    public void setRankDrawable(int rank) {
        switch (rank) {
            case RANK_SECOND:
                mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.songs_numbertwo));
                break;
            case RANK_THIRD:
                mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.songs_numberthree));
                break;
            case RANK_FIRST:
            default:
                mRankImage.setImageDrawable(getResources().getDrawable(R.drawable.songs_numberone));
        }
    }

    /**
     * Set song name
     * @param songName song name
     */
    public void setSongName(CharSequence songName) {
        if (songName != null && !songName.equals("")) {
            mSongName.setText(songName);
        }
    }

    /**
     * Set singer name
     * @param singerName singer name
     */
    public void setSingerName(CharSequence singerName) {
        if (singerName != null && !singerName.equals("")) {
            mSingerName.setText(singerName);
        }
    }

    /**
     * Set drawable image of the album
     * @param drawable drawable image
     */
    public void setAlbumImage(Drawable drawable) {
        if (drawable != null) {
            mAlbumImage.setImageDrawable(drawable);
        }
    }


    @Override
    public void onSkinChanged() {

    }
}
