package com.example.cobaimplicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailActivity extends AppCompatActivity {

    @BindView(R.id.edt_email_tujuan)
    EditText edtEmailTujuan;
    @BindView(R.id.edt_subjek)
    EditText edtSubjek;
    @BindView(R.id.edt_body_email)
    EditText edtBodyEmail;
    @BindView(R.id.btn_kirim)
    Button btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_kirim)
    public void onViewClicked() {
        String email = edtEmailTujuan.getText().toString();
        String subjek = edtSubjek.getText().toString();
        String bodyEmail = edtBodyEmail.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(subjek) || TextUtils.isEmpty(bodyEmail)){
            Toast.makeText(this, "Can't be empty", Toast.LENGTH_SHORT).show();

        }
        else {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email}); //objek baru dibuat string array karena menampung multiple email
            intent.putExtra(Intent.EXTRA_SUBJECT, subjek);
            intent.putExtra(Intent.EXTRA_TEXT, bodyEmail);
            intent.setType("message/rfc822");
            startActivity(intent);

        }

    }
}
