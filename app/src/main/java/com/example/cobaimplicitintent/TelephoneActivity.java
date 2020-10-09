package com.example.cobaimplicitintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TelephoneActivity extends AppCompatActivity {

    @BindView(R.id.edt_nomor_telpon)
    EditText edtNomorTelpon;
    @BindView(R.id.btn_panggil_langsung)
    Button btnPanggilLangsung;
    @BindView(R.id.btn_dial_phone)
    Button btnDialPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.edt_nomor_telpon, R.id.btn_panggil_langsung, R.id.btn_dial_phone})
    public void onViewClicked(View view) {
        String noPhone = edtNomorTelpon.getText().toString();

        switch (view.getId()) {
            case R.id.edt_nomor_telpon:
                Intent getNmrIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                getNmrIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(getNmrIntent, 1);
                break;
            case R.id.btn_panggil_langsung:
                if (TextUtils.isEmpty(noPhone)){
                    Toast.makeText(this, "Can't be empty", Toast.LENGTH_SHORT).show();

                }
                else{
                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    intentCall.setData(Uri.parse("tel:" + noPhone));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if(checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 10);
                            return;

                        }

                    }
                    startActivity(intentCall);
                }
                break;
            case R.id.btn_dial_phone:
                if (TextUtils.isEmpty(noPhone)) {
                    Toast.makeText(this, "Can't be empty", Toast.LENGTH_SHORT).show();

                }
                else{
                    Intent intentDial = new Intent(Intent.ACTION_DIAL);
                    intentDial.setData(Uri.parse("tel: " + noPhone));
                    startActivity(intentDial);

                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                Cursor cursor;
                Uri uri = data.getData();
                cursor = getContentResolver().query(uri, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                        null,
                        null,
                        null,
                        null);

                if (cursor != null && cursor.moveToNext()){
                    String noPhone = cursor.getString(0);
                    edtNomorTelpon.setText(noPhone);

                }

            }
            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Canceled Operation", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
