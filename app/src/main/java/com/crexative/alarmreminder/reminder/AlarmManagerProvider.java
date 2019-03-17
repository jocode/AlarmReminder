package com.crexative.alarmreminder.reminder;

import android.app.AlarmManager;
import android.content.Context;

public class AlarmManagerProvider {

    private static final String TAG = AlarmManager.class.getSimpleName();
    private static AlarmManager sAlarmManager;

    public static synchronized  void injectAlarmManager(AlarmManager alarmManager){

        if (sAlarmManager != null){
            throw new IllegalArgumentException("Alarm manager Already set");
        }
        sAlarmManager = alarmManager;

    }

    /*Package*/
    static synchronized AlarmManager getAlarmManager(Context context){
        if (sAlarmManager == null){
            sAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }
        return  sAlarmManager;
    }

}
