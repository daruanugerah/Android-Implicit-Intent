package com.example.cobaimplicitintent;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends AppCompatActivity {

    @BindView(R.id.btn_ring)
    Button btnRing;
    @BindView(R.id.btn_silent)
    Button btnSilent;
    @BindView(R.id.btn_vibrate)
    Button btnVibrate;

    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
    }

    @OnClick({R.id.btn_ring, R.id.btn_silent, R.id.btn_vibrate})
    public void onViewClicked(View view) {

        //untuk bikin notif di atas
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()){
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS ); //yg android.provider settingnya
            startActivity(intent);

        }
        switch (view.getId()) {
            case R.id.btn_ring:
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(this, "Mode Normal Activated", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_silent:
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(this, "Mode Silent Activated", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_vibrate:
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(this, "Mode Vibrate Activated", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
