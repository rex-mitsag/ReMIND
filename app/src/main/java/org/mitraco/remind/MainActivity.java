package org.mitraco.remind;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tp = findViewById(R.id.timePicker);

        findViewById(R.id.buttonTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();

                if (Build.VERSION.SDK_INT >= 23) {
                    cal.set(
                            cal.get(Calendar.YEAR),
                            cal.get(Calendar.MONTH),
                            cal.get(Calendar.DAY_OF_MONTH),
                            tp.getHour(),
                            tp.getMinute(),
                            0
                    );
                }
                else{
                    cal.set(
                            cal.get(Calendar.YEAR),
                            cal.get(Calendar.MONTH),
                            cal.get(Calendar.DAY_OF_MONTH),
                            tp.getCurrentHour(),
                            tp.getCurrentMinute(),
                            0
                    );
                }

                setAlarm(cal.getTimeInMillis());

            }
        });
    }

    private void setAlarm(long timeInMillis) {
        AlarmManager al = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent in = new Intent(this, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, in, 0);

        al.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pi);

        Toast.makeText(this, "Task is set", Toast.LENGTH_SHORT).show();
    }
}
