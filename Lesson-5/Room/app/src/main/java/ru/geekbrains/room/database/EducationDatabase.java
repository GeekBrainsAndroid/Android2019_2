package ru.geekbrains.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import ru.geekbrains.room.dao.EducationDao;
import ru.geekbrains.room.model.DateConverter;
import ru.geekbrains.room.model.Email;
import ru.geekbrains.room.model.Student;

@Database(entities = {Student.class, Email.class}, version = 2)
@TypeConverters(DateConverter.class)
public abstract class EducationDatabase extends RoomDatabase {
    public abstract EducationDao getEducationDao();
}
