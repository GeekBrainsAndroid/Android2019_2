package ru.geekbrains.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Задать условия запуска задачи
                OneTimeWorkRequest workRequest = new OneTimeWorkRequest
                        .Builder(MyWorker.class)
                        .setInitialDelay(5, TimeUnit.SECONDS)
                        .build();

                WorkManager workManager = WorkManager.getInstance();

                // Запустить задачу
                workManager.enqueue(workRequest);
            }
        });
    }
}
