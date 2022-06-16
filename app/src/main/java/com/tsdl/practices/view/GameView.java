package com.tsdl.practices.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

public class GameView extends GridLayout {
//    private final Card[][] cardsMap = new Card[Constants.CARD_NUM][Constants.CARD_NUM];
//    private final int[][] cardMapNum = new int[Constants.CARD_NUM][Constants.CARD_NUM];
//    private final List<Point> emptyPoints = new ArrayList<>();
//    //是否更新一个卡片
//    boolean flag = false;
//    static int card_width;
//    private MediaPlayer mediaPlayer;
//    private MediaPlayer mediaPlayer2;
//    private MediaPlayer mediaPlayer3;
//    private static GameView gameView = null;
//    private final AnimLayer animLayer = new AnimLayer(this.getContext());
//
//    public static GameView getGameView() {
//        return gameView;
//    }
//
//    public int[][] getCardMapNum() {
//        for (int y = 0; y < Constants.CARD_NUM; y++) {
//            for (int x = 0; x < Constants.CARD_NUM; x++) {
//                cardMapNum[x][y] = cardsMap[x][y].getNum();
//            }
//        }
//        return cardMapNum;
//    }

    public GameView(Context context) {
        super(context);
//        gameView = this;
//        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        gameView = this;
//        initGameView();
    }

//    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        gameView = this;
//        initGameView();
//    }
//
//    /**
//     * 初始化方法
//     */
//    private void initGameView() {
//        setColumnCount(Constants.CARD_NUM);
//        setBackgroundColor(0xffbbadc0);
//        card_width = getCardWitch();
//        addCards(card_width);
//        mediaPlayer = MediaPlayer.create(getContext(), R.raw.move);
//        mediaPlayer2 = MediaPlayer.create(getContext(), R.raw.move);
//        mediaPlayer3 = MediaPlayer.create(getContext(), R.raw.move);
//
//        setOnTouchListener(new OnTouchListener() {
//            //屏幕触动起始位置和偏移量
//            private float startX;
//            private float startY;
//
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        startX = motionEvent.getX();
//                        startY = motionEvent.getY();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        float offsetX = motionEvent.getX() - startX;
//                        float offsetY = motionEvent.getY() - startY;
//
//                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
//                            //横向滑动
//                            if (offsetX < -5) {
//                                swipeLeft();
//                            } else if (offsetX > 5) {
//                                swipeRight();
//                            }
//                        } else {
//                            if (offsetY < -5) {
//                                swipeUp();
//                            } else if (offsetY > 5) {
//                                swipeDown();
//                            }
//                        }
//                        break;
//                }
//                return true;
//            }
//        });
//    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
//        super.onSizeChanged(w, h, oldWidth, oldHeight);
//
//        startGame();
//    }
//
//    private int getCardWitch() {
//        //声明屏幕对象
//        DisplayMetrics displayMetrics;
//        displayMetrics = getResources().getDisplayMetrics();
//        return (displayMetrics.widthPixels - 10) / Constants.CARD_NUM;
//    }
//
//    //经过下面的方法添加图片后，x代表的就是横坐标，y代表的就是纵坐标
//    private void addCards(int cardWidth) {
//        Card card;
//        for (int y = 0; y < Constants.CARD_NUM; y++) {
//            for (int x = 0; x < Constants.CARD_NUM; x++) {
//                card = new Card(getContext());
//                card.setNum(0);
//                addView(card, cardWidth, cardWidth);
//                cardsMap[x][y] = card;
//            }
//        }
//    }
//
//    public void startGame() {
//        SharedPreferences preferences = getContext().getSharedPreferences("game2048data", MODE_PRIVATE);
//        for (int y = 0; y < Constants.CARD_NUM; y++) {
//            for (int x = 0; x < Constants.CARD_NUM; x++) {
//                int k = preferences.getInt(x + "-" + y, 0);
//                cardsMap[x][y].setNum(k);
//            }
//        }
//        MainActivity.getMainActivity().clearScore();
//        int score = preferences.getInt("score", 0);
//        MainActivity.getMainActivity().addScore(score);
//    }
//
//    public void startNewGame() {
//        MainActivity.getMainActivity().clearScore();
//        for (int y = 0; y < Constants.CARD_NUM; y++) {
//            for (int x = 0; x < Constants.CARD_NUM; x++) {
//                cardsMap[x][y].setNum(0);
//            }
//        }
//        addRandomNum();
//        addRandomNum();
//    }
//
//    private void addRandomNum() {
//        emptyPoints.clear();
//        for (int y = 0; y < Constants.CARD_NUM; y++) {
//            for (int x = 0; x < Constants.CARD_NUM; x++) {
//                if (cardsMap[x][y].getNum() <= 0) {
//                    emptyPoints.add(new Point(x, y));
//                }
//            }
//        }
//
//        Point point = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
//        cardsMap[point.x][point.y].setNum(Math.random() > 0.1 ? 2 : 4);
//        animLayer.createScaleTo1(cardsMap[point.x][point.y]);
//    }
//
//    private void swipeLeft() {
//        flag = false;
//        for (int y = 0; y < Constants.CARD_NUM; y++) {
//            for (int x = 0; x < Constants.CARD_NUM; x++) {
//                for (int x1 = x + 1; x1 < Constants.CARD_NUM; x1++) {
//                    if (cardsMap[x1][y].getNum() > 0) {
//                        if (cardsMap[x][y].getNum() <= 0) {
//                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
//                            cardsMap[x1][y].setNum(0);
//                            x--;
//                            flag = true;
//                        } else if (cardsMap[x][y].getNum() == cardsMap[x1][y].getNum()) {
//                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
//                            cardsMap[x1][y].setNum(0);
//                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
//                            flag = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if (flag) {
//            if (!mediaPlayer.isPlaying()) {
//                mediaPlayer.start();
//            } else if (!mediaPlayer2.isPlaying()) {
//                mediaPlayer2.start();
//            } else {
//                mediaPlayer3.start();
//            }
//            addRandomNum();
//            checkComplete();
//        }
//    }
//
//    private void swipeRight() {
//        flag = false;
//        for (int y = 0; y < Constants.CARD_NUM; y++) {
//            for (int x = 3; x >= 0; x--) {
//                for (int x1 = x - 1; x1 >= 0; x1--) {
//                    if (cardsMap[x1][y].getNum() > 0) {
//                        if (cardsMap[x][y].getNum() <= 0) {
//                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
//                            cardsMap[x1][y].setNum(0);
//                            x++;
//                            flag = true;
//                        } else if (cardsMap[x][y].getNum() == cardsMap[x1][y].getNum()) {
//                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
//                            cardsMap[x1][y].setNum(0);
//                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
//                            flag = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if (flag) {
//            if (!mediaPlayer.isPlaying()) {
//                mediaPlayer.start();
//            } else if (!mediaPlayer2.isPlaying()) {
//                mediaPlayer2.start();
//            } else {
//                mediaPlayer3.start();
//            }
//            addRandomNum();
//            checkComplete();
//        }
//    }
//
//    private void swipeUp() {
//        flag = false;
//        for (int x = 0; x < Constants.CARD_NUM; x++) {
//            for (int y = 0; y < Constants.CARD_NUM; y++) {
//                for (int y1 = y + 1; y1 < Constants.CARD_NUM; y1++) {
//                    if (cardsMap[x][y1].getNum() > 0) {
//                        if (cardsMap[x][y].getNum() <= 0) {
//                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
//                            cardsMap[x][y1].setNum(0);
//                            y--;
//                            flag = true;
//                        } else if (cardsMap[x][y].getNum() == cardsMap[x][y1].getNum()) {
//                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
//                            cardsMap[x][y1].setNum(0);
//                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
//                            flag = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if (flag) {
//            if (!mediaPlayer.isPlaying()) {
//                mediaPlayer.start();
//            } else if (!mediaPlayer2.isPlaying()) {
//                mediaPlayer2.start();
//            } else {
//                mediaPlayer3.start();
//            }
//            addRandomNum();
//            checkComplete();
//        }
//    }
//
//    private void swipeDown() {
//        flag = false;
//        for (int x = 0; x < Constants.CARD_NUM; x++) {
//            for (int y = 3; y >= 0; y--) {
//                for (int y1 = y - 1; y1 >= 0; y1--) {
//                    if (cardsMap[x][y1].getNum() > 0) {
//                        if (cardsMap[x][y].getNum() <= 0) {
//                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
//                            cardsMap[x][y1].setNum(0);
//                            y++;
//                            flag = true;
//                        } else if (cardsMap[x][y].getNum() == cardsMap[x][y1].getNum()) {
//                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
//                            cardsMap[x][y1].setNum(0);
//                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
//                            flag = true;
//                        }
//                        break;
//                    }
//                }
//            }
//        }
//        if (flag) {
//            if (!mediaPlayer.isPlaying()) {
//                mediaPlayer.start();
//            } else if (!mediaPlayer2.isPlaying()) {
//                mediaPlayer2.start();
//            } else {
//                mediaPlayer3.start();
//            }
//            addRandomNum();
//            checkComplete();
//        }
//    }
//
//    private void checkComplete() {
//        boolean complete = true;
//        ALL:
//            for (int y = 0; y < Constants.CARD_NUM; y++) {
//            for (int x = 0; x < Constants.CARD_NUM; x++) {
//                if (cardsMap[x][y].getNum() == 0 || (x > 0 && cardsMap[x][y].getNum() == cardsMap[x - 1][y].getNum())
//                        || (x < 3 && cardsMap[x][y].getNum() == cardsMap[x + 1][y].getNum())
//                        || (y > 0 && cardsMap[x][y].getNum() == cardsMap[x][y - 1].getNum())
//                        || (y < 3 && cardsMap[x][y].getNum() == cardsMap[x][y + 1].getNum())) {
//                    complete = false;
//                    break ALL;
//                }
//            }
//        }
//        if (complete) {
//            new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束")
//                    .setPositiveButton("重来", (dialogInterface, i) -> startNewGame()).show();
//        }
//    }
}
