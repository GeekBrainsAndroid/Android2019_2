package ru.geekbrains.roomdata;

import android.content.ContentValues;
import android.database.Cursor;

public class StudentMapper {

    private final static String ID = "id";
    private final static String FIRST_NAME = "first_name";
    private final static String LAST_NAME = "last_name";

    public static Student toStudent(Cursor cursor){
        return new Student(cursor.getLong(cursor.getColumnIndex(ID)),
                cursor.getString(cursor.getColumnIndex(FIRST_NAME)),
                cursor.getString(cursor.getColumnIndex(LAST_NAME)));
    }

    public static ContentValues toContent(Student student){
        ContentValues answer = new ContentValues();
        answer.put(ID, student.getId());
        answer.put(FIRST_NAME, student.getFirstName());
        answer.put(LAST_NAME, student.getLastName());
        return answer;
    }
}
