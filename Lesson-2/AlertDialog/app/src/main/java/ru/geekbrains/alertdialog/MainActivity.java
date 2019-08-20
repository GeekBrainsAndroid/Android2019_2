package ru.geekbrains.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alert1 = findViewById(R.id.alertDialog1);
        alert1.setOnClickListener(clickAlertDialog1);
        Button alert3 = findViewById(R.id.alertDialog3);
        alert3.setOnClickListener(clickAlertDialog3);
        Button alertList = findViewById(R.id.alertDialogList);
        alertList.setOnClickListener(clickAlertDialogList);
    }

    private View.OnClickListener clickAlertDialog1 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            // Создаем билдер и передаем контекст приложения
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // в билдере указываем заголовок окна (можно указывать как ресурс, так и строку)
            builder.setTitle(R.string.exclamation)
                    // указываем сообщение в окне (также есть вариант со строковым параметром)
                    .setMessage(R.string.press_button)
                    // можно указать и пиктограмму
                    .setIcon(R.mipmap.ic_launcher_round)
                    // из этого окна нельзя выйти кнопкой back
                    .setCancelable(false)
                    // устанавливаем кнопку (название кнопки также можно задавать строкой)
                    .setPositiveButton(R.string.button,
                            // Ставим слушатель, нажатие будем обрабатывать
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(MainActivity.this, "Кнопка нажата", Toast.LENGTH_SHORT).show();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
            Toast.makeText(MainActivity.this, "Диалог открыт", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener clickAlertDialog3 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            // Создаем билдер и передаем контекст приложения
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // в билдере указываем заголовок окна (можно указывать как ресурс, так и строку)
            builder.setTitle(R.string.exclamation)
                    // указываем сообщение в окне (также есть вариант со строковым параметром)
                    .setMessage("2 + 2 = 4 ?")
                    // из этого окна нельзя выйти кнопкой back
                    .setCancelable(false)
                    // устанавливаем отрицательную кнопку
                    .setNegativeButton(R.string.no,
                            // Ставим слушатель, нажатие будем обрабатывать
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(MainActivity.this, "Нет!", Toast.LENGTH_SHORT).show();
                                }
                            })
                    // устанавливаем нейтральную кнопку
                    .setNeutralButton(R.string.dunno,
                            // Ставим слушатель, нажатие будем обрабатывать
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(MainActivity.this, "Не знаю!", Toast.LENGTH_SHORT).show();
                                }
                            })
                    // устанавливаем кнопку (название кнопки также можно задавать строкой)
                    .setPositiveButton(R.string.yes,
                            // Ставим слушатель, нажатие будем обрабатывать
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(MainActivity.this, "Да!", Toast.LENGTH_SHORT).show();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };
    private View.OnClickListener clickAlertDialogList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String[] items = getResources().getStringArray(R.array.choose);
            // Создаем билдер и передаем контекст приложения
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // в билдере указываем заголовок окна (можно указывать как ресурс, так и строку)
            builder.setTitle(R.string.exclamation)
                    // Добавим список элементов
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int item) {
                            Toast.makeText(MainActivity.this, String.format("Выбран пункт %d", item + 1), Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };
}
