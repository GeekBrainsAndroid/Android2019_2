package ru.geekbrains.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
    }

    // Проверим на разрешение чтения контактов
    private void checkPermission() {
        // Разрешение на чтение контактов уже дали?
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // Запросить разрешение у пользователя.
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
            // после запроса, управление передается в метод onRequestPermissionsResult
        } else {
            // Разрешение уже дали
            initRecycler();
        }
    }

    // Инициализация списка
    private void initRecycler() {
        // Получить резолвер контент провайдеров
        ContentResolver contentResolver = getContentResolver();
        // Дать запрос на получение контактов. В query надо добавить строку URI
        // Возвращается курсор
        Cursor cursorContact = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        // Работа со списком
        RecyclerView recyclerContacts = findViewById(R.id.recyclerContacts);
        recyclerContacts.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerContacts.setLayoutManager(layoutManager);

        ContactRecyclerAdapter adapter = new ContactRecyclerAdapter(cursorContact, contentResolver, getResources());
        recyclerContacts.setAdapter(adapter);
    }

    // Обратный вызов, после раздачи разрешений от пользователя
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешение дали
                initRecycler();
            } else {
                // Разрешение не дали
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
