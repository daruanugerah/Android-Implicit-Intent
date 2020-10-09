package com.example.cobaimplicitintent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmActivity extends AppCompatActivity {

    @BindView(R.id.analog_clock)
    AnalogClock analogClock;
    @BindView(R.id.btn_set_alarm)
    Button btnSetAlarm;
    @BindView(R.id.tv_set_alarm)
    TextView tvSetAlarm;

    private TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) { //dari int i ganti jd int hourofday
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);

            if (calSet.compareTo(calNow)<=0){
                calSet.add(Calendar.DATE, 1);

            }
            else if (calSet.compareTo(calNow)>0){
                Toast.makeText(AlarmActivity.this, "Set Alarm pada " + calSet.getTime(), Toast.LENGTH_SHORT).show();

            }

            tvSetAlarm.setText("Alarm Berbunyi pada " + calSet.getTime());

            Intent setIntentAlarm = new Intent(getBaseContext(), AlarmReceiver.class);
            PendingIntent pendingIntentAlarm = PendingIntent.getBroadcast(getBaseContext(), 1, setIntentAlarm, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(), pendingIntentAlarm);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_set_alarm)
    public void onViewClicked() {
        Calendar calender = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener,
                calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }
}
