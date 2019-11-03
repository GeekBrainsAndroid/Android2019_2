package ru.geekbrains.broadcastreceiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MessageReceiver extends BroadcastReceiver {

    private static final String NAME_MSG = "MSG";
    private static final String TAG = "MessageBroadcastReceiver";
    private int messageId=0;

    // Сюда приходит широковещательное оповещение
    @Override
    public void onReceive(Context context, Intent intent) {
        // Получить параметр сообщения
        String message = intent.getStringExtra(NAME_MSG);
        if (message == null){
            message = "";
        }
        Log.d(TAG, message);
        // создать нотификацию
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "2")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Broadcast Receiver")
                .setContentText(message);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(messageId++, builder.build());
    }
}
