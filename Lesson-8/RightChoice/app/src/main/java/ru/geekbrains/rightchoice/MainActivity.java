package ru.geekbrains.rightchoice;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random rnd = new Random();
    private Resources resources;
    private TextView textChoice;

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int choice = rnd.nextInt(10);
            String choicer;
            if (choice > 7) {
                choicer = resources.getString(R.string.delay_choice);
            } else if (choice > 3){
                choicer = resources.getString(R.string.yes);
            } else{
                choicer = resources.getString(R.string.no);
            }
            textChoice.setText(choicer);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        resources = getResources();
        textChoice = findViewById(R.id.textChoice);
        findViewById(R.id.buttonChoice).setOnClickListener(buttonListener);
    }
}
