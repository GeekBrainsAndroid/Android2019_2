package ru.geekbrains.bottomdialog;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyBottomSheetDialogFragment dialogFragment =
                        MyBottomSheetDialogFragment.newInstance();
                dialogFragment.show(getSupportFragmentManager(),
                        "dialog_fragment");
            }
        });
    }
}
