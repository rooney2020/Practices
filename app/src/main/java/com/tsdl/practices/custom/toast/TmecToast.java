package com.tsdl.practices.custom.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.IdRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tsdl.practices.R;
import com.tsdl.practices.custom.base.ISkinChangedListener;
import com.tsdl.practices.custom.base.TmecImageView;
import com.tsdl.practices.custom.base.TmecSkinConfigurationManager;
import com.tsdl.practices.custom.textview.TmecTextBody;
import com.tsdl.practices.custom.textview.TmecTextExplainBody;

public class TmecToast extends Toast implements ISkinChangedListener {

    /**
     * Toast type No icon shown.
     */
    public static final int TYPE_TEXT = 0;
    /**
     * Toast type Show static image.
     */
    public static final int TYPE_SINGLE_ICON = 1;
    /**
     * Toast type Show double icon.
     */
    public static final int TYPE_DOUBLE_ICON = 2;
    /**
     * Toast type Show loading image.
     */
    public static final int TYPE_PROBAR = 3;
    private static final int MARGIN_216PX = 216;
    private static final int MARGIN_286PX = 286;
    private static final int TEXT_SIZE_36PX = 36;
    private static final int TOAST_HEIGHT_150PX = 150;
    private static final int TOAST_TEXT_MIN_LENGTH = 1;
    private static int mTypeToast = -1;
    private static int mImageBg;
    private static int mImageNightBg;
    private static TmecToast mToast;
    private static RelativeLayout mToastContentRl;
    private static TmecImageView mImageView;
    private static TmecTextBody mTextView;
    private static ProgressBar mToastProBar;
    private static Context mContext;
    private static TmecTextExplainBody mDoubleBottomTv;
    private static String mMessageTv;
    private static String mBottomTv;

    public TmecToast(Context context) {
        super(context);
        TmecSkinConfigurationManager.getInstance().register(this);
    }

    /**
     * showTextToast.
     *
     * @param context context.
     * @param resId   resId.
     * @param time    time.
     * @throws Resources.NotFoundException .
     */
    @SuppressLint("ResourceType")
    public static void showTextToast(Context context, @IdRes int resId, int time)
            throws Resources.NotFoundException {
        showTextToast(context, context.getResources().getText(resId), time);
    }

    /**
     * showTextToast.
     *
     * @param context context.
     * @param time    time.
     * @throws Resources.NotFoundException NotFoundException.
     */
    @SuppressLint("ResourceType")
    public static void showTextToast(Context context, CharSequence text, int time)
            throws Resources.NotFoundException {
        showToast(context, text, null, time, TYPE_TEXT);
    }

    /**
     * makeText.
     *
     * @param context context.
     * @param text    message.
     */
    @SuppressLint("ResourceType")
    public static void showIconToast(Context context, CharSequence text, int time, int imageBg, int imageNightBg)
            throws Resources.NotFoundException {
        mImageBg = imageBg;
        mImageNightBg = imageNightBg;
        showToast(context, text, null, time, TYPE_SINGLE_ICON);
    }

    /**
     * showDoubleIcontoast.
     * @param context context.
     * @param text  text.
     * @param bottomText bottomText.
     * @param time time.
     * @param imageBg imageBg.
     * @param imageNightBg imageNightBg.
     * @throws Resources.NotFoundException NotFoundException.
     */
    public static void showDoubleIcontoast(Context context, CharSequence text, CharSequence bottomText,
                                           int time, int imageBg, int imageNightBg)
            throws Resources.NotFoundException {
        mImageBg = imageBg;
        mImageNightBg = imageNightBg;
        showToast(context, text, bottomText, time, TYPE_DOUBLE_ICON);
    }

    /**
     * makeText.
     *
     * @param context context.
     * @param text    message.
     */
    @SuppressLint("ResourceType")
    /*public static void showProBarToast(Context context, CharSequence text, int time)
            throws Resources.NotFoundException {
        showToast(context, text, time, TYPE_PROBAR);
    }*/

    /**
     * show toast.
     *
     * @param context   context.
     * @param text      displayed text.
     * @param time      display time.
     * @param typeToast type toast.
     */
    private static void showToast(Context context, CharSequence text, CharSequence bottomText, int time,
                                  int typeToast) {
        if (mToast != null) {
            mToast.cancel();
        }
        initToast(context, text, bottomText, typeToast);
        mToast.setDuration(time == Toast.LENGTH_LONG ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        mToast.show();
    }

    /**
     * makeText.
     *
     * @param context   context.
     * @param text      message.
     * @param typeToast 0:text tost.
     *                  1:icon toast.
     *                  2:progressBar toast.
     * @return TmecToast.
     */
    @SuppressLint("WrongConstant")
    private static TmecToast initToast(Context context, CharSequence text, CharSequence bottomText, int typeToast) {
        mContext = context;
        mTypeToast = typeToast;
        initView(context, text, bottomText);
        return mToast;
    }

    private static void initView(Context context, CharSequence text, CharSequence bottomText) {
        mMessageTv = TextUtils.isEmpty(text) ? "" : text.toString();
        mBottomTv = TextUtils.isEmpty(bottomText) ? "" : bottomText.toString();
        mToast = new TmecToast(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.v_layout_toast, null);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mToastContentRl = view.findViewById(R.id.toast_rl);
        mDoubleBottomTv = view.findViewById(R.id.toast_double_bottom_tv);
        mTextView = view.findViewById(R.id.toast_message_tv);
        mToastProBar = view.findViewById(R.id.toast_proBar);
        mImageView = view.findViewById(R.id.toast_icon_iv);

        initTypeToast();
        setToastWidth(mMessageTv, mBottomTv);
        updateSkinChanged();
        mTextView.setText(mMessageTv);
        mDoubleBottomTv.setText(mBottomTv);
        mToast.setView(view);
        mToast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, mContext.getResources()
                .getDimensionPixelOffset(R.dimen.toast_x_offset), mContext.getResources()
                .getDimensionPixelOffset(R.dimen.toast_y_offset));

    }

    private static void setToastWidth(String text, String bottomText) {
        RelativeLayout.LayoutParams llParams
                = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, TOAST_HEIGHT_150PX);
        int textWidth = TextUtils.isEmpty(text) ? TOAST_TEXT_MIN_LENGTH : text.length() * TEXT_SIZE_36PX;
        llParams.width = mTypeToast == TYPE_TEXT ? MARGIN_216PX + textWidth : MARGIN_286PX + textWidth;
        mToastContentRl.setLayoutParams(llParams);

    }

    private static void initTypeToast() {
        if (mTypeToast == TYPE_TEXT) {
            mToastProBar.setVisibility(View.GONE);
            mImageView.setVisibility(View.GONE);
            mDoubleBottomTv.setVisibility(View.GONE);
        } else if (mTypeToast == TYPE_SINGLE_ICON) {
            mToastProBar.setVisibility(View.GONE);
            mImageView.setVisibility(View.VISIBLE);
            mDoubleBottomTv.setVisibility(View.GONE);
        } else if (mTypeToast == TYPE_DOUBLE_ICON) {
            mToastProBar.setVisibility(View.GONE);
            mImageView.setVisibility(View.VISIBLE);
            mDoubleBottomTv.setVisibility(View.VISIBLE);
        } else if (mTypeToast == TYPE_PROBAR) {
            mToastProBar.setVisibility(View.VISIBLE);
            mImageView.setVisibility(View.GONE);
            mDoubleBottomTv.setVisibility(View.GONE);
        }
    }

    private static void updateSkinChanged() {
        mToastContentRl.setBackground(TmecSkinConfigurationManager.getInstance().isNight()
                ? mContext.getDrawable(R.mipmap.ic_toast_night_brage) :
                mContext.getDrawable(R.mipmap.ic_toast_bg));
        mImageView.setImageNightBg(mImageNightBg);
        mImageView.setImageBg(mImageBg);
        mToastProBar.setIndeterminateDrawable(TmecSkinConfigurationManager.getInstance().isNight()
                ? mContext.getDrawable(R.drawable.toast_progress) :
                mContext.getDrawable(R.drawable.toast_night_progress));

    }

    @Override
    public void onSkinChanged() {
        updateSkinChanged();
    }
}
