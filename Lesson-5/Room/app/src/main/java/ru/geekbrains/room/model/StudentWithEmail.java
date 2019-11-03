package ru.geekbrains.room.model;

import androidx.room.ColumnInfo;

// Результат запроса по двум таблицам через соединение
// одним запросом
public class StudentWithEmail {
    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    public String email;
}
