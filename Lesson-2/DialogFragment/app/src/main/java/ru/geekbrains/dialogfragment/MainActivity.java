package ru.geekbrains.dialogfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DialogCustomFragment dlgCustom;
    DialogBuilderFragment dlgBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlgCustom = new DialogCustomFragment();
        dlgBuilder = new DialogBuilderFragment();
    }

    // вызов диалога с билдером
    public void onClickDialogBuilder(View view) {
        dlgBuilder.show(getSupportFragmentManager(), "dialogBuilder");
    }

    // вызов диалога с макетом
    public void onClickDialogCustom(View view) {
        dlgCustom.show(getSupportFragmentManager(), "dialogCustom");
    }

    // Метод для общения с диалоговыми окнами
    public void onDialogResult(String resultDialog){
        Toast.makeText(this, "Выбрано " + resultDialog, Toast.LENGTH_SHORT).show();
    }
}
