package ru.geekbrains.room.model;

import androidx.room.ColumnInfo;

// Класс, выделенный для обработки из класса Student
// Однако в таблице, этот класс будет являтся полями таблицы student
public class Address {
    public String street;
    public String city;

    @ColumnInfo(name = "post_code")
    public String postCode;
}
