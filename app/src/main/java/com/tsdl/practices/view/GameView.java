package com.tsdl.practices.view;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import androidx.annotation.RequiresApi;

import com.tsdl.practices.R;
import com.tsdl.practices.model.Card;
import com.tsdl.practices.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class GameView extends GridLayout implements View.OnTouchListener {

    private final Card[][] cardsMap = new Card[Constants.CARD_NUM][Constants.CARD_NUM];
    //是否更新一个卡片
    boolean flag = false;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;
    //屏幕触动起始位置和偏移量
    private float startX;
    private float startY;

    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    /**
     * 初始化方法
     */
    private void initGameView() {
        setColumnCount(Constants.CARD_NUM);
        setBackgroundColor(Constants.GAME_VIEW_COLOR);

        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                addCards(getCardWidth());
                getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.move);
        mediaPlayer2 = MediaPlayer.create(getContext(), R.raw.move);
        mediaPlayer3 = MediaPlayer.create(getContext(), R.raw.move);

        setOnTouchListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
        super.onSizeChanged(w, h, oldWidth, oldHeight);
        // startGame();
    }

    private int getCardWidth() {
        //声明屏幕对象
        if (getWidth() < getHeight()) {
            return (getWidth() - 10) / Constants.CARD_NUM;
        } else {
            return (getHeight() - 10) / Constants.CARD_NUM;
        }
    }

    //经过下面的方法添加图片后，x代表的就是横坐标，y代表的就是纵坐标
    private void addCards(int cardSideLength) {
        FrameLayout.LayoutParams cardLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        cardLayoutParams.setMargins(10, 10, 0, 0);
        cardLayoutParams.width = cardSideLength;
        cardLayoutParams.height = cardSideLength;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            travelBy((x, y) -> {
                Card card = new Card(getContext());
                addView(card, cardLayoutParams);
                cardsMap[x][y] = card;
            });
        }
    }

    public void startGame() {
        SharedPreferences preferences = getContext().getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            travelBy((x, y) -> {
                int k = preferences.getInt(x + Constants.SP_KEY_SEPARATOR + y, 0);
                cardsMap[x][y].setNum(k);
            });
        }
        // MainActivity.getMainActivity().clearScore();
        int score = preferences.getInt(Constants.SP_KEY_SCORE, 0);
        // MainActivity.getMainActivity().addScore(score);
    }

    public void startNewGame() {
        // MainActivity.getMainActivity().clearScore();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            travelBy((x, y) -> cardsMap[x][y].setNum(0));
        }
        addRandomNum();
        addRandomNum();
    }

    private void addRandomNum() {
        List<Point> emptyPoints = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            travelBy((x, y) -> {
                if (cardsMap[x][y].getNum() <= 0) {
                    emptyPoints.add(new Point(x, y));
                }
            });
        }

        Point point = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        cardsMap[point.x][point.y].setNum(Math.random() > 0.1 ? 2 : 4);
        playAnimation(cardsMap[point.x][point.y]);
    }

    /**
     * 新增卡片时的动画效果
     */
    public void playAnimation(Card target) {
        ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(100);
        target.setAnimation(sa);
        target.startAnimation(sa);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void travelBy(BiConsumer<Integer, Integer> function) {
        for (int y = 0; y < Constants.CARD_NUM; y++) {
            for (int x = 0; x < Constants.CARD_NUM; x++) {
                function.accept(y, x);
            }
        }
    }

    /**
     * Swipe Next.
     *
     * @param isVertical Is Vertical, true for vertical.
     * @param isStart    Is Start, true and vertical for top, true and horizontal for left.
     */
    private void swipeBy(boolean isVertical, boolean isStart) {
        flag = false;
        boolean isDefault = isVertical && !isStart;
        for (int y = isDefault ? 0 : Constants.CARD_NUM - 1; isDefault ? y < Constants.CARD_NUM : y >= 0;
             y = isDefault ? y + 1 : y - 1) {
            for (int x = 0; x < Constants.CARD_NUM; x++) {
                for (int i = isStart ? (isVertical ? y + 1 : x + 1) : (isVertical ? y - 1 : x - 1);
                     isStart ? i < Constants.CARD_NUM : i >= 0; i = isStart ? i + 1 : i - 1) {
                    if (cardsMap[isVertical ? x : i][isVertical ? i : y].getNum() > 0) {
                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[isVertical ? x : i][isVertical ? i : y].getNum());
                            cardsMap[isVertical ? x : i][isVertical ? i : y].setNum(0);
                            if (isVertical) {
                                y = isStart ? y - 1 : y + 1;
                            } else {
                                x = isStart ? x - 1 : x + 1;
                            }
                            flag = true;
                        } else if (cardsMap[x][y].getNum() == cardsMap[isVertical ? x : i][isVertical ? i : y].getNum()) {
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[isVertical ? x : i][isVertical ? i : y].setNum(0);
                            // MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            flag = true;
                        }
                        break;
                    }
                }
            }
        }
        nextStep(flag);
    }

    private void nextStep(boolean flag) {
        if (flag) {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            } else if (!mediaPlayer2.isPlaying()) {
                mediaPlayer2.start();
            } else {
                mediaPlayer3.start();
            }
            addRandomNum();
            checkComplete();
        }
    }

    private void checkComplete() {
        boolean complete = true;
        ALL:
        for (int y = 0; y < Constants.CARD_NUM; y++) {
            for (int x = 0; x < Constants.CARD_NUM; x++) {
                if (cardsMap[x][y].getNum() == 0 || (x > 0 && cardsMap[x][y].getNum() == cardsMap[x - 1][y].getNum())
                        || (x < 3 && cardsMap[x][y].getNum() == cardsMap[x + 1][y].getNum())
                        || (y > 0 && cardsMap[x][y].getNum() == cardsMap[x][y - 1].getNum())
                        || (y < 3 && cardsMap[x][y].getNum() == cardsMap[x][y + 1].getNum())) {
                    complete = false;
                    break ALL;
                }
            }
        }
        if (complete) {
            new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束")
                    .setPositiveButton("重来", (dialogInterface, i) -> startNewGame()).show();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = motionEvent.getX();
                startY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                float offsetX = motionEvent.getX() - startX;
                float offsetY = motionEvent.getY() - startY;

                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    //横向滑动
                    if (offsetX < -5) {
                        swipeBy(false, true);
                    } else if (offsetX > 5) {
                        swipeBy(false, false);
                    }
                } else {
                    if (offsetY < -5) {
                        swipeBy(true, true);
                    } else if (offsetY > 5) {
                        swipeBy(true, false);
                    }
                }
                break;
        }
        return true;
    }
}
