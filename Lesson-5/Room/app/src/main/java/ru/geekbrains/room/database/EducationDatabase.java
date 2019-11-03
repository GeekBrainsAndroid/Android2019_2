package ru.geekbrains.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.geekbrains.room.dao.EducationDao;
import ru.geekbrains.room.model.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class EducationDatabase extends RoomDatabase {
    public abstract EducationDao getEducationDao();
}
