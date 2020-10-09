package com.example.cobaimplicitintent;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsActivity extends AppCompatActivity {

    @BindView(R.id.edt_nomor_tujuan)
    EditText edtNomorTujuan;
    @BindView(R.id.edt_body_sms)
    EditText edtBodySms;
    @BindView(R.id.btn_sms_langsung)
    Button btnSmsLangsung;
    @BindView(R.id.btn_sms_intent)
    Button btnSmsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.edt_nomor_tujuan, R.id.btn_sms_langsung, R.id.btn_sms_intent})
    public void onViewClicked(View view) {

        String noTelp = edtNomorTujuan.getText().toString();
        String bodySms = edtBodySms.getText().toString();

        switch (view.getId()) {
            case R.id.edt_nomor_tujuan:
                Intent contactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                contactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(contactIntent, 1);
                break;
            case R.id.btn_sms_langsung:
                if (TextUtils.isEmpty(noTelp) || TextUtils.isEmpty(bodySms)){
                    Toast.makeText(this, "Can't be empty", Toast.LENGTH_SHORT).show();

                }
                else {
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(noTelp, null, bodySms, null, null);
                        Toast.makeText(this, "Sent SMS", Toast.LENGTH_SHORT).show();

                    } catch (Exception e){
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

                break;
            case R.id.btn_sms_intent:
                if (TextUtils.isEmpty(noTelp) || TextUtils.isEmpty(bodySms)){
                    Toast.makeText(this, "Can't be empty", Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent intentSms = new Intent(Intent.ACTION_SENDTO);
                    intentSms.setData(Uri.parse("smsto: " + Uri.encode(noTelp)));
                    intentSms.putExtra("sms_body", bodySms);
                    startActivity(intentSms);

                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                Cursor cursor;
                Uri uri = data.getData();
                cursor = getContentResolver().query(uri, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                        null,
                        null,
                        null, null);

                if (cursor !=null && cursor.moveToNext()){
                    String telpNumber = cursor.getString(0);
                    edtNomorTujuan.setText(telpNumber);

                }

            }

            else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Canceled get number phone", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
