package ru.geekbrains.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.geekbrains.room.model.Student;

// Описание объекта, обрабатывающего данные
// @Dao - доступ к данным
// В этом классе описывается, как будет происходить обработка данных
@Dao
public interface EducationDao {

    // Метод для добавления студента в базу данных
    // @Insert - признак добавления
    // onConflict - что делать, если такая запись уже есть
    // В данном случае просто заменим ее
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);

    // Метод для замены данных студента
    @Update
    void updateStudent(Student student);

    // Удалим данные студента
    @Delete
    void deleteStudent(Student student);

    // Удалим данные студента, просто зная ключ
    @Query("DELETE FROM student WHERE id = :id")
    void deteleStudentById(long id);

    // Заберем данные по всем студентам
    @Query("SELECT * FROM student")
    List<Student> getAllStudents();

    // Получим данные только одного студента по id
    @Query("SELECT * FROM student WHERE id = :id")
    Student getStudentById(long id);
}