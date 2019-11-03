package ru.geekbrains.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.util.Calendar;
import java.util.Date;

public class CalculationService extends IntentService {

    private static final String EXTRA_SECONDS = "ru.geekbrains.service.calculation.SECONDS";
    static final String EXTRA_RESULT = "ru.geekbrains.service.calculation.RESULT";

    public CalculationService() {
        super("CalculationService");
    }

    /**
     * Фабричный метод, для старта сервиса (чтобы не перегружать класс MainActivity)
     */
    public static void startCalculationService(Context context, int seconds) {
        Intent intent = new Intent(context, CalculationService.class);
        intent.putExtra(EXTRA_SECONDS, seconds);
        context.startService(intent);
    }

    /**
     * Обработка в фоновом режиме алгоритма в сервисе
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        int seconds = intent.getIntExtra(EXTRA_SECONDS, 0);
        long result = calculateLong(seconds);
        sendBrodcast(result);
    }

    // Долгий расчет чего-либо
    private long calculateLong(int seconds) {
        long result = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, seconds);
        Date finishTime = calendar.getTime();
        while (Calendar.getInstance().getTime().before(finishTime)) {
            if (result == Long.MAX_VALUE) {
                result = 0;
            }
            result++;
        }
        return result;
    }

    //отправить уведомление о завершении сервиса
    private void sendBrodcast(long result) {
        Intent broadcastIntent = new Intent(MainActivity.BROADCAST_ACTION_CALCFINISHED);
        broadcastIntent.putExtra(EXTRA_RESULT, result);
        sendBroadcast(broadcastIntent);
    }
}
