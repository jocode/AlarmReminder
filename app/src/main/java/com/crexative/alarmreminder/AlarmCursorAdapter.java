package com.crexative.alarmreminder;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crexative.alarmreminder.data.AlarmReminderContract;

public class AlarmCursorAdapter extends CursorAdapter {

    private TextView mTitleText, mDateAndTimeText, mRepeatInfoText;
    private ImageView mActiveImage, mThumbnailImage;

    public AlarmCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /*Flags*/);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.alarm_items, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        mTitleText = view.findViewById(R.id.recycle_title);
        mDateAndTimeText = view.findViewById(R.id.recycle_title);
        mRepeatInfoText = view.findViewById(R.id.recycle_repeat_info);
        mActiveImage = view.findViewById(R.id.active_image);
        mThumbnailImage = view.findViewById(R.id.thumbnail_image);

        int titleColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_TITLE);
        int dateColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_DATE);
        int timeColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_TIME);
        int repeatColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT);
        int repeatNOColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO);
        int repeatTypeColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE);
        int activeColumnIndex = cursor.getColumnIndex(AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE);

        String title = cursor.getString(titleColumnIndex);
        String date = cursor.getString(dateColumnIndex);
        String time = cursor.getString(timeColumnIndex);
        String repeat = cursor.getString(repeatColumnIndex);
        String repeatNO = cursor.getString(repeatNOColumnIndex);
        String repeatType = cursor.getString(repeatTypeColumnIndex);
        String active = cursor.getString(activeColumnIndex);
        
        String dateTime = date + " " + time;
        
        setReminderTitle(title);
        setReminderDate(dateTime);
        setReminderRepeatInfo(repeat, repeatNO, repeatType);
        setActiveImage(active);

    }

    // Set reminder title view
    private void setReminderTitle(String title) {
        mTitleText.setText(title);
        String letter = "A";

        if (title != null && !title.isEmpty()){
            letter = title.substring(0,1);
        }

        /*
        ColorGenerator
        int color = mColorGenerator.getRandomColor();

        // Create a circuilar icon consisting of a random backgroung colour and first letter  of title
         mDrawableBuilder = TextDrawable.builder()
                    .buildRound(letter, color)
         mThumbnailImahe.setImageDrawable(mDrawableBuilder);
         */
    }


    // Set date and time views
    private void setReminderDate(String dateTime) {
        mDateAndTimeText.setText(dateTime);
    }

    // Set repeat views
    private void setReminderRepeatInfo(String repeat, String repeatNO, String repeatType) {
        if (repeat.equals("true")){
            mRepeatInfoText.setText("Every " + repeatNO + " " + repeatType + "(s)");
        } else if (repeat.equals("false")){
            mRepeatInfoText.setText("Repeat Off");
        }
    }

    // Set active image as on or off
    private void setActiveImage(String active) {
        if (active.equals("true")){
            mActiveImage.setImageResource(android.R.drawable.ic_dialog_info);
        } else if (active.equals("false")){
            mActiveImage.setImageResource(android.R.drawable.ic_delete);
        }
    }

}
