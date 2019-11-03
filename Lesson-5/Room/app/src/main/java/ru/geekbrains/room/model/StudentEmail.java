package ru.geekbrains.room.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

// Результат запроса через Relation
// первый запрос по студентам,
// далее запросы по каждому студенту для получения Email
public class StudentEmail {

    @Embedded
    public Student student;

    @Relation(parentColumn = "id", entityColumn = "student_id")
    public List<Email> emails;
}
