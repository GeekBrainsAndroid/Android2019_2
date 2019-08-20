package ru.geekbrains.battery;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

// Кастомный пользовательский элемент
// Нарисуем батарею
public class BatteryView extends View {

    // Цвет батареи
    private int batteryColor = Color.GRAY;
    // Цвет уровня заряда
    private int levelColor = Color.GREEN;
    // Изображение батареи
    private RectF batteryRectangle = new RectF();
    // Изображение уровня заряда
    private Rect levelRectangle = new Rect();
    // Изображение головы батареи
    private Rect headRectangle = new Rect();
    // "Краска" батареи
    private Paint batteryPaint;
    // "Краска" заряда
    private Paint levelPaint;
    // Ширина элемента
    private int width = 0;
    // Высота элемента
    private int height = 0;

    // Уровень заряда
    private int level = 100;

    // Константы
    // Отступ элементов
    private final static int padding = 10;
    // Скругление углов батареи
    private final static int round = 5;
    // Ширина головы батареи
    private final static int headWidth = 10;

    public BatteryView(Context context) {
        super(context);
        init();
    }

    // Вызывается при добавлении элемента в макет
    // AttributeSet attrs - набор параметров, указанных в макете для этого элемента
    public BatteryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    // Вызывается при добавлении элемента в макет с установленными стилями
    // AttributeSet attrs - набор параметров, указанных в макете для этого элемента
    // int defStyleAttr - базовый установленный стиль
    public BatteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    // Вызывается при добавлении элемента в макет с установленными стилями
    // AttributeSet attrs - набор параметров, указанных в макете для этого элемента
    // int defStyleAttr - базовый установленный стиль
    // int defStyleRes - ресурс стиля, если он не был определен в предыдущем параметре
    public BatteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context, attrs);
        init();
    }

    // Инициализация атрибутов пользовательского элемента из xml
    private void initAttr(Context context, AttributeSet attrs){

        // При помощи этого метода получаем доступ к набору атрибутов.
        // На выходе массив со значениями
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BatteryView, 0, 0);

        // Чтобы получить какое-либо значение из этого массива,
        // надо вызвать соответсвующий метод, и передав в этот метод имя ресурса
        // указанного в файле определения атрибутов (attrs.xml)
        batteryColor = typedArray.getColor(R.styleable.BatteryView_battery_color, Color.GRAY);

        // вторым параметром идет значение по умолчанию, если атрибут не указан в макете,
        // то будет подставлятся эначение по умолчанию.
        levelColor = typedArray.getColor(R.styleable.BatteryView_level_color, Color.GREEN);

        // Обратите внимание, что первый параметр пишется особым способом
        // через подчерки. первое слово означает имя определения
        // <declare-styleable name="BatteryView">
        // следующее слово имя атрибута
        // <attr name="level" format="integer" />
        level = typedArray.getInteger(R.styleable.BatteryView_level, 100);

        // В конце работы дадим сигнал,
        // что нам больше массив со значениями атрибутов не нужен
        // Система в дальнейшем будет переиспользовать этот объект,
        // и мы никогда не получим к нему доступ из этого элемента
        typedArray.recycle();
    }

    // Начальная инициализация полей класса
    private void init(){
        batteryPaint = new Paint();
        batteryPaint.setColor(batteryColor);
        batteryPaint.setStyle(Paint.Style.FILL);
        levelPaint = new Paint();
        levelPaint.setColor(levelColor);
        levelPaint.setStyle(Paint.Style.FILL);
    }

    // Когда система Андроид создает пользовательский экран, то еще неизвестны размеры элемента.
    // Это связанно с тем, что используются расчетные единица измерения,
    // то есть, чтобы элемент выглядил одинаково на разных усройствах,
    // необходимы расчеты размера элемента, в привязке к размеру экрана, его разрешения и плотности пикселей.
    // Этот метод вызывается при необходимости изменения размера элемента
    // Такая необходимость возникает каждый раз при отрисовке экрана.
    // Если нам надо нарисовать свой элемент, то переопределяем этот метод,
    // и задаем новые размеры нашим элементам внутри view
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Получить реальные ширину и высоту
        width = w - getPaddingLeft() - getPaddingRight();
        height = h - getPaddingTop() - getPaddingBottom();
        // Отрисовка батареи
        batteryRectangle.set(padding,
                padding,
                width - padding - headWidth,
                height - padding);
        headRectangle.set(width - padding - headWidth,
                2 * padding,
                width - padding,
                height - 2 * padding);
        levelRectangle.set(2 * padding,
                2 * padding,
                (int)((width - 2 * padding - headWidth)*((double)level/(double)100)),
                height - 2 * padding);
    }

    // Вызывается, когда надо нарисовать элемент
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(batteryRectangle, round, round, batteryPaint);
        canvas.drawRect(levelRectangle, levelPaint);
        canvas.drawRect(headRectangle, batteryPaint);
    }
}
