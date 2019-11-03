package ru.geekbrains.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BoundService extends Service {

    // Для связывания активити и сервиса
    private final IBinder binder = new ServiceBinder();

    // Для расчета чисел Фибоначи
    private long fibonacci1 = 0;
    private long fibonacci2 = 1;

    // Вызывается только 1 раз при создании сервиса
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    // Обработка (происходит в основном потоке приложения,
    // о потоках надо заботится самим)
    public long getNextFibonacci(){
        long result = fibonacci1 + fibonacci2;
        fibonacci1 = fibonacci2;
        fibonacci2 = result;
        return result;
    }

    // Класс связи между клиентом и сервисом
    class ServiceBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
        long getNextFibonacci(){
            return getService().getNextFibonacci();
        }
    }
}
