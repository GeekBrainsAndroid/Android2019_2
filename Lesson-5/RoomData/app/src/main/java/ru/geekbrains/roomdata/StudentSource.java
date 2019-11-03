package ru.geekbrains.roomdata;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class StudentSource {

    // URI для доступа к контент провайдеру
    private final static Uri STUDENT_URI = Uri.parse("content://ru.geekbrains.education.provider/student");

    // работа с контент провайдером осуществляется через этот класс
    private ContentResolver contentResolver;
    private Cursor cursor;

    public StudentSource(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }

    // Получить запрос
    public void query(){
        cursor = contentResolver.query(STUDENT_URI, null, null, null, null);
    }

    // Получить данные по студенту по позиции
    public Student getStudentByPosition(int position){
        cursor.moveToPosition(position);
        return StudentMapper.toStudent(cursor);
    }

    // Количество студентов
    public int getCount(){
        return cursor.getCount();
    }

    // Добавить студента
    public void insert(Student student){
        ContentValues content = StudentMapper.toContent(student);
        contentResolver.insert(STUDENT_URI, content);
        query(); // Переоткрываем курсор для перечитывания данных
    }

    // Редактировать данные
    public void update(Student student){
        Uri uri = ContentUris.withAppendedId(STUDENT_URI, student.getId());
        contentResolver.update(uri, StudentMapper.toContent(student), null, null);
        query(); // Переоткрываем курсор для перечитывания данных
    }

    // Удалить запись о студенте
    public void delete(Student student) {
        Uri uri = ContentUris.withAppendedId(STUDENT_URI, student.getId());
        contentResolver.delete(uri, null, null);
        query(); // Переоткрываем курсор для перечитывания данных
    }
}
