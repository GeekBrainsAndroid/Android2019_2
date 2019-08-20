package ru.geekbrains.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        Button alertSingleList = findViewById(R.id.alertDialogListSingle);
        alertSingleList.setOnClickListener(clickAlertDialogListSingle);
        Button alertMultiList = findViewById(R.id.alertDialogListMulti);
        alertMultiList.setOnClickListener(clickAlertDialogListMulti);
        Button alertView = findViewById(R.id.alertDialogView);
        alertView.setOnClickListener(clickAlertDialogView);
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

    private int chosen = -1;    // Здесь будет храниться выбранный пункт
    private View.OnClickListener clickAlertDialogListSingle = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final String[] items = getResources().getStringArray(R.array.choose);
            // Создаем билдер и передаем контекст приложения
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // в билдере указываем заголовок окна (можно указывать как ресурс, так и строку)
            builder.setTitle(R.string.exclamation)
                    // Добавим список элементов, chosen - выбранный элемент, если = -1, то ни один не выбран
                    .setSingleChoiceItems(items, chosen, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int item) {
                            chosen = item; // Обновим выбранный элемент
                        }
                    })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "Отмена!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (chosen == -1) {
                                Toast.makeText(MainActivity.this, "Ок, пункт не выбран!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(MainActivity.this, String.format("Ок, выбран '%s'!", items[chosen]), Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };
    private View.OnClickListener clickAlertDialogListMulti = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final String[] items = getResources().getStringArray(R.array.choose);
            final boolean[] chosen = {false, true, false};
            // Создаем билдер и передаем контекст приложения
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // в билдере указываем заголовок окна (можно указывать как ресурс, так и строку)
            builder.setTitle(R.string.exclamation)
                    // Добавим список элементов, булевый chosen - массив с выбранными элементами
                    .setMultiChoiceItems(items, chosen, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            chosen[i] = b; // при переключении обновим ячейку в массиве
                        }
                    })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MainActivity.this, "Отмена!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // собираем выбранные элементы в строку
                            StringBuilder sb = new StringBuilder("Ок, выбрано: ");
                            for (int index = 0; index < chosen.length; index++) {
                                if (chosen[index]) {
                                    sb.append(items[index]);
                                    sb.append("; ");
                                }
                            }
                            Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };
    private View.OnClickListener clickAlertDialogView = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Создаем билдер и передаем контекст приложения
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // Вытащим макет диалога
            final View contentView = getLayoutInflater().inflate(R.layout.alert_dialog, null);
            // в билдере указываем заголовок окна (можно указывать как ресурс, так и строку)
            builder.setTitle("Введите имя")
                    // Установим макет диалога (можно устанавливать любой view)
                    .setView(contentView)
                    .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EditText editText = contentView.findViewById(R.id.editText);
                            Toast.makeText(MainActivity.this, String.format("Введено: %s", editText.getText().toString()), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    };
}
