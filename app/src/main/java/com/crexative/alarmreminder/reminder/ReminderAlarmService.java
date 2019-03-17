package com.crexative.alarmreminder.reminder;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;


import com.crexative.alarmreminder.AddReminderActivity;

public class ReminderAlarmService extends IntentService {

    private static final String TAG = ReminderAlarmService.class.getSimpleName();
    private static final int NOTIFICATION_ID = 42;

    public ReminderAlarmService() {
        super(TAG);
    }

    //This is a deep link intent, and needs the task stack
    public static PendingIntent getReminderPendingIntent(Context context, Uri uri){
        Intent action = new Intent(context, ReminderAlarmService.class);
        action.setData(uri);
        return PendingIntent.getService(context, 0, action, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Uri uri = intent.getData();

        // Display a notification to view the taskDetails
        Intent action = new Intent(this, AddReminderActivity.class);
        action.setData(uri);
        PendingIntent operation = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(action)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Grab the task description
        if (uri != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Cursor cursor = getContentResolver().query(uri, null, null, null);
            }
        }
        String description = "";

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("AlarmReminder")
                .setContentText(description)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setContentIntent(operation)
                .setAutoCancel(true)
                .build();

        manager.notify(NOTIFICATION_ID, notification);
    }
}
