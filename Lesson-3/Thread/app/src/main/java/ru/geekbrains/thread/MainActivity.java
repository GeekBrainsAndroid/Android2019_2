package ru.geekbrains.thread;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private int counterThread = 0;
    private TextView textView;
    private TextView textIndicator;
    private EditText seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        textIndicator = findViewById(R.id.textIndicator);
        seconds = findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = Long.toString(calculate(Integer.parseInt(seconds.getText().toString())));
                textIndicator.setText("В главном потоке");
                textView.setText(result);
            }
        });

        initButtonThread();
    }

    private void initButtonThread(){
        Button calcThread = findViewById(R.id.calcThreadBtn);
        calcThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterThread++;
                final int numberThread = counterThread;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = Long.toString(calculate(Integer.parseInt(seconds.getText().toString())));
                        textIndicator.setText(String.format("Из потока %d", numberThread));
                        textView.setText(result);
                    }
                }).start();
            }
        });
    }

    private long calculate(int seconds) {
        Date date = new Date();
        long diffInSec;
        do{
            Date currentDate = new Date();
            long diffInMs = currentDate.getTime() - date.getTime();
            diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs);
        }while(diffInSec < seconds);
        return diffInSec;
    }
}
