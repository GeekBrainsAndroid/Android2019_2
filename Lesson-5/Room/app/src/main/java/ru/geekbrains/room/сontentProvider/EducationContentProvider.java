package ru.geekbrains.room.сontentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import ru.geekbrains.room.App;
import ru.geekbrains.room.R;
import ru.geekbrains.room.dao.EducationDao;
import ru.geekbrains.room.model.Student;

public class EducationContentProvider extends ContentProvider {

    // Типы URI для определения запроса
    private static final int URI_ALL = 1;   //URI для всех записей
    private static final int URI_ID = 2;    //URI для конкретрной записи

    // Часть пути (будем определять ее до таблицы student)
    private static final String STUDENTS_PATH = "student";

    // Адрес URI
    private String authorities;

    // Помогает определить тип URI адреса
    private UriMatcher uriMatcher;

    // Типы данных
    // набор строк
    private String student_content_type;
    // одна строка
    private String student_content_item_type;

    // адрес URI провайдера
    private Uri content_uri;

    @Override
    public boolean onCreate() {

        // Прочитаем часть пути из ресурсов
        authorities = getContext().getResources().getString(R.string.authorities);

        // Вспомогательный класс, для определения типа запроса
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // Интересуют объекты
        uriMatcher.addURI(authorities, STUDENTS_PATH, URI_ALL);
        // Интересует только один объект
        uriMatcher.addURI(authorities, STUDENTS_PATH + "/#", URI_ID);

        // тип содержимого - все объекты
        student_content_type = "vnd.android.cursor.dir/vnd." + authorities + "." + STUDENTS_PATH;
        // тип содержимого - один объект
        student_content_item_type = "vnd.android.cursor.item/vnd." + authorities + "." + STUDENTS_PATH;

        // Строка для доступа к провайдеру
        content_uri = Uri.parse("content://" + authorities + "/" + STUDENTS_PATH);
        return true;
    }

    // Провайдер требует переопределения этого метода,
    // чтобы понимать тип запроса
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case URI_ALL:
                return student_content_type;
            case URI_ID:
                return student_content_item_type;
        }
        return null;
    }

    // Получить данные
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // получить доступ к данным
        EducationDao educationDao = App.getInstance().getEducationDao();
        Cursor cursor;
        // При помощи UriMatcher понимаем,
        // какой это запрос, одного элемента или всех
        switch (uriMatcher.match(uri)) {
            case URI_ALL:
                // Запрос в базу данных для всех элементов
                cursor = educationDao.getStudentCursor();
                break;
            case URI_ID:
                // Определяем id из uri адреса.
                // Класс ContentUris помогает это сделать
                long id = ContentUris.parseId(uri);
                // Запрос в базу данных для одного элемента
                cursor = educationDao.getStudentCursor(id);
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        // Установим нотификацию при изменении данных в content_uri
        cursor.setNotificationUri(getContext().getContentResolver(), content_uri);
        return cursor;
    }

    // Удалить запись
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (uriMatcher.match(uri) != URI_ID)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        // получить доступ к данным
        EducationDao educationDao = App.getInstance().getEducationDao();

        // Получаем идентификатор записи по адресу
        long id = ContentUris.parseId(uri);
        // Удалить запись по идентификатору
        educationDao.deteleStudentById(id);
        // Нотификация на изменение курсора
        getContext().getContentResolver().notifyChange(uri, null);
        return 1;
    }

    // Добавить новую запись
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != URI_ALL)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        // получить доступ к данным
        EducationDao educationDao = App.getInstance().getEducationDao();

        // Добавление записи о студенте
        long id = educationDao.insertStudent(map(values));
        Uri resultUri = ContentUris.withAppendedId(content_uri, id);
        // уведомляем ContentResolver, что данные по адресу resultUri изменились
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    // Изменить запись
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        if (uriMatcher.match(uri) != URI_ID)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        // получить доступ к данным
        EducationDao educationDao = App.getInstance().getEducationDao();

        educationDao.updateStudent(map(values));
        getContext().getContentResolver().notifyChange(uri, null);
        return 1;
    }

    // Преобразование из оъекта передачи данных (ключ-значение)
    // в объект Student
    private Student map(ContentValues values){
        Student student = new Student();
        if (values.containsKey(Student.ID))
            student.id = (long) values.get(Student.ID);
        student.firstName = (String) values.get(Student.FIRST_NAME);
        student.lastName = (String) values.get(Student.LAST_NAME);
        return student;
    }
}
