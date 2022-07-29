package com.tsdl.practices.service;

import static android.app.NotificationManager.IMPORTANCE_LOW;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.tsdl.practices.R;
import com.tsdl.practices.activity.MainActivity;
import com.tsdl.practices.activity.MusicActivity;
import com.tsdl.practices.util.LogUtils;

public class MusicService extends Service {

    public static final String TAG = MusicService.class.getSimpleName();
    private final MusicBinder mBinder = new MusicBinder();

    public MusicService() {
        LogUtils.logD(TAG, "MusicService");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logD(TAG, "onCreate");
        Intent intent = new Intent(this, MusicActivity.class);
        PendingIntent pi;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        } else {
            pi = PendingIntent.getActivity(this, 0, intent, 0);
        }
        String channelId = getPackageName();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelId, IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
        }
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.logD(TAG, "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        boolean result = super.onUnbind(intent);
        LogUtils.logD(TAG, "onUnbind");
        return result;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        LogUtils.logD(TAG, "onRebind");
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.logD(TAG, "onBind");
        return mBinder;
    }

    public static class MusicBinder extends Binder {
        public void play() {
            LogUtils.logD(TAG, "play");
        }

        public void pause() {
            LogUtils.logD(TAG, "pause");
        }

        public void stop() {
            LogUtils.logD(TAG, "stop");
        }
    }
}