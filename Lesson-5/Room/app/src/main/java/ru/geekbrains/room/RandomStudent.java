package ru.geekbrains.room;

import android.content.res.Resources;

import java.util.Random;

import ru.geekbrains.room.model.Student;

// Формирование студента случайным образом
public class RandomStudent {

    private Resources resources;
    private Random rnd = new Random();

    public RandomStudent(Resources resources){
        this.resources = resources;
    }

    // Не меняя ключа, изменим имя и фамилию
    public Student rndUpdateStudent(Student student){
        student.firstName = randomFirstName();
        student.lastName = randomLastName();
        return student;
    }

    // Новая запись со случайным именем и фамилией
    public Student rndStudent(){
        Student student = new Student();
        return rndUpdateStudent(student);
    }

    // Выбор случайным образом имени из списка
    private String randomFirstName(){
        String[] firstNames = resources.getStringArray(R.array.firstNames);
        return firstNames[rnd.nextInt(firstNames.length)];
    }

    // Выбор случайным образом фамилии из списка
    private String randomLastName(){
        String[] lastNames = resources.getStringArray(R.array.lastNames);
        return lastNames[rnd.nextInt(lastNames.length)];
    }
}
