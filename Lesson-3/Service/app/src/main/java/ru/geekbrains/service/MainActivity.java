package ru.geekbrains.service;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String BROADCAST_ACTION_CALCFINISHED = "ru.geekbrains.service.calculationfinished";
    private TextView textResult;
    private EditText editSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initNotificationChannel();
    }

    // Для регистрации Broadcast Receiver
    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(calculationFinishedReceiver, new IntentFilter(BROADCAST_ACTION_CALCFINISHED));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(calculationFinishedReceiver);
    }

    private void initView() {
        Button buttonStartService = findViewById(R.id.buttonStartService);
        buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MainService.class));
            }
        });

        textResult = findViewById(R.id.textResult);
        editSeconds = findViewById(R.id.editSeconds);
        findViewById(R.id.buttonCalcService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seconds = Integer.parseInt(editSeconds.getText().toString());
                CalculationService.startCalculationService(MainActivity.this, seconds);
            }
        });
    }

    // На Андроидах версии 26 и выше необходимо создавать канал нотификации
    // На старых версиях канал создавать не надо
    private void initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel("2", "name", importance);
            notificationManager.createNotificationChannel(mChannel);
        }
    }

    // Получатель широковещательного сообщения
    private BroadcastReceiver calculationFinishedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final long result = intent.getLongExtra(CalculationService.EXTRA_RESULT, 0);
            // Потокобезопасный вывод данных
            textResult.post(new Runnable() {
                @Override
                public void run() {
                    textResult.setText(Long.toString(result));
                }
            });
        }
    };
}
