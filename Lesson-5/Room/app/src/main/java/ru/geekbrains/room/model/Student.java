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
@Entity(indices = {@Index(value = {Student.FIRST_NAME, Student.LAST_NAME})})
public class Student {

    public final static String ID = "id";
    public final static String FIRST_NAME = "first_name";
    public final static String LAST_NAME = "last_name";

    // @PrimaryKey - указывает на ключевую запись,
    // autoGenerate = true - автоматическая генерация ключа
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    public long id;

    // Имя студента
    // @ColumnInfo - позволяет задавать параметры колонки в БД
    // name = "first_name" - такое будет имя колонки
    @ColumnInfo(name = FIRST_NAME)
    public String firstName;

    // Фамилия студента
    @ColumnInfo(name = LAST_NAME)
    public String lastName;

    public Date dateBirth;

    // @Embeddeb - хранить поля вложенного класса, как поля таблицы
    @Embedded
    public Address address;
}
