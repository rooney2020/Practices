package com.tsdl.practices.service;

import static android.app.NotificationManager.IMPORTANCE_LOW;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.tsdl.common.util.LogUtils;
import com.tsdl.practices.R;
import com.tsdl.practices.activity.MusicActivity;
import com.tsdl.practices.receiver.SmsReceiver;

public class MusicService extends Service {

    public static final String TAG = MusicService.class.getSimpleName();
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";

    private final MusicBinder mBinder = new MusicBinder();

    public MusicService() {
        LogUtils.logD(TAG, "MusicService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.logD(TAG, "onCreate");
        Intent intent = new Intent(this, MusicActivity.class);
        PendingIntent pi;
        pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        String channelId = getPackageName();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel(channelId, channelId, IMPORTANCE_LOW);
        manager.createNotificationChannel(channel);
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("Content Title")
                .setContentText("Content Text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
        SmsReceiver smsReceiver = new SmsReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SMS_RECEIVED_ACTION);
        registerReceiver(smsReceiver, filter);
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