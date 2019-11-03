package ru.geekbrains.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    private int messageId=1000;

    private long factorial = 1;

    // Признак прекращения расчета
    private boolean running;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Метод startForeground надо обязательно вызвать для такого сервиса
        startForeground(messageId++, makeNotification("Foreground Service"));

        running = true;
        // Делаем тяжелую работу в потоке
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    factorial *= ++factorial;
                    Notification notification = makeNotification("Next factorial " + Long.toString(factorial));
                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(messageId, notification);
                }
            }
        }).start();

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        // Если признак не скинуть, рабочий поток непонятно когда остановится
        running = false;
        super.onDestroy();
    }

    // вывод уведомления в строке состояния
    private Notification makeNotification(String message){
        return new NotificationCompat.Builder(this, "2")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Main service notification")
                .setContentText(message)
                .build();
    }
}
