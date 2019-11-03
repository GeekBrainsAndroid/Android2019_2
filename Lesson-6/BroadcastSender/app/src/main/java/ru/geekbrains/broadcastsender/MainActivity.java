package ru.geekbrains.broadcastsender;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Имя ACTION для Broadcast, по нему Receiver и будет определяться
    private static final String ACTION_SEND_MSG = "ru.geekbrains.broadcastsender.message";
    // Имя передаваемого параметра
    private static final String NAME_MSG = "MSG";
    // Эта константа спрятана в Intent классе,
    // но именно при помощи ее можно поднять приложение
    public static final int FLAG_RECEIVER_INCLUDE_BACKGROUND = 0x01000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtonSend();
    }

    private void initButtonSend() {
        final EditText message = findViewById(R.id.textMessage);
        Button send = findViewById(R.id.buttonSend);
        send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                // Формируем интент
                String msg = message.getText().toString();
                Intent intent = new Intent();
                // Укажем ACTION по которому будем ловить сообщение
                intent.setAction(ACTION_SEND_MSG);
                // Добавим параметр.
                intent.putExtra(NAME_MSG, msg);
                // Указываем флаг поднятия приложения
                // (без него будут получать уведомления только
                // загруженные приложения)
                intent.addFlags(FLAG_RECEIVER_INCLUDE_BACKGROUND);
                // Отправка сообщения
                sendBroadcast(intent);
            }
        });
    }
}
