package com.example.cobaimplicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_audio)
    Button btnAudio;
    @BindView(R.id.btn_alarm)
    Button btnAlarm;
    @BindView(R.id.btn_camera)
    Button btnCamera;
    @BindView(R.id.btn_email)
    Button btnEmail;
    @BindView(R.id.btn_notif)
    Button btnNotif;
    @BindView(R.id.btn_sms)
    Button btnSms;
    @BindView(R.id.btn_wifi)
    Button btnWifi;
    @BindView(R.id.btn_telephone)
    Button btnTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_audio, R.id.btn_alarm, R.id.btn_camera, R.id.btn_email, R.id.btn_notif, R.id.btn_sms, R.id.btn_wifi, R.id.btn_telephone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_audio:
                startActivity(new Intent(MainActivity.this, AudioManagerActivity.class));
                break;
            case R.id.btn_alarm:
                startActivity(new Intent(MainActivity.this, AlarmActivity.class));
                break;
            case R.id.btn_camera:
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case R.id.btn_email:
                startActivity(new Intent(this, EmailActivity.class));
                break;
            case R.id.btn_notif:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.btn_sms:
                startActivity(new Intent(this, SmsActivity.class));
                break;
            case R.id.btn_wifi:
                startActivity(new Intent(this, WifiActivity.class));
                break;
            case R.id.btn_telephone:
                startActivity(new Intent(this, TelephoneActivity.class));
                break;
        }
    }
}
