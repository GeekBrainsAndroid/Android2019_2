package ru.geekbrains.room;

import java.util.List;

import ru.geekbrains.room.dao.EducationDao;
import ru.geekbrains.room.model.Student;

// Вспомогательный класс, развязывающий
// зависимость между Room и RecyclerView
public class EducationSource {

    private final EducationDao educationDao;

    // Буфер с данными, сюда будем подкачивать данные из БД
    private List<Student> students;

    public EducationSource(EducationDao educationDao){
        this.educationDao = educationDao;
    }

    // Получить всех студентов
    public List<Student> getStudents(){
        // Если объекты еще не загружены, то загружаем их.
        // Сделано для того, чтобы не делать запросы в БД каждый раз
        if (students == null){
            LoadStudents();
        }
        return students;
    }

    // Загрузить студентов в буфер
    public void LoadStudents(){
        students = educationDao.getAllStudents();
    }

    // Получить количество записей
    public long getCountStudents(){
        return educationDao.getCountStudents();
    }

    // Добавить студента
    public void addStudent(Student student){
        educationDao.insertStudent(student);
        // После изменения БД надо перечитать буфер
        LoadStudents();
    }

    // Заменить студента
    public void updateStudent(Student student){
        educationDao.updateStudent(student);
        LoadStudents();
    }

    // Удалить студента из базы
    public void removeStudent(long id){
        educationDao.deteleStudentById(id);
        LoadStudents();
    }

}
