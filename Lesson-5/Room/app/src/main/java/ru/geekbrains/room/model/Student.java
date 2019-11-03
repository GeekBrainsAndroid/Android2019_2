package ru.geekbrains.room.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

// @Entity - это признак табличного объекта, то есть объект
// будет сохранятся в базе данных в виде строки
// indices - указывает на индексы в таблице
@Entity(indices = {@Index(value = {"first_name", "last_name"})})
public class Student {

    // @PrimaryKey - указывает на ключевую запись,
    // autoGenerate = true - автоматическая генерация ключа
    @PrimaryKey(autoGenerate = true)
    public long id;

    // Имя студента
    // @ColumnInfo - позволяет задавать параметры колонки в БД
    // name = "first_name" - такое будет имя колонки
    @ColumnInfo(name = "first_name")
    public String firstName;

    // Фамилия студента
    @ColumnInfo(name = "last_name")
    public String lastName;

    public Date dateBirth;

    // @Embeddeb - хранить поля вложенного класса, как поля таблицы
    @Embedded
    public Address address;
}
