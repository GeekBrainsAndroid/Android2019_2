package ru.geekbrains.room;

import android.app.Application;

import androidx.room.Room;

import ru.geekbrains.room.dao.EducationDao;
import ru.geekbrains.room.database.EducationDatabase;

// паттерн синглтон, наследуем класс Application
// создаем базу данных в методе onCreate
public class App extends Application {

    private static App instance;

    // база данных
    private EducationDatabase db;

    // Так получаем объект приложения
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Это для синглтона, сохраняем объект приложения
        instance = this;

        // строим базу
        db = Room.databaseBuilder(
                    getApplicationContext(),
                    EducationDatabase.class,
                    "education_database")
                .allowMainThreadQueries() //Только для примеров и тестирования.
                .build();
    }

    // Получаем EducationDao для составления запросов
    public EducationDao getEducationDao() {
        return db.getEducationDao();
    }
}
