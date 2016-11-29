package br.com.interaje.felipe.coxinhadroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.com.interaje.felipe.coxinhadroid.R;


public class SignUpActivity extends AppCompatActivity {

    private EditText edtxtName, edtxtEmail, edtxtPassword, edtxtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtxtName = (EditText) findViewById(R.id.sign_up_name);
        edtxtEmail = (EditText) findViewById(R.id.sign_up_email);
        edtxtPassword = (EditText) findViewById(R.id.sign_up_password);
        edtxtPhone = (EditText) findViewById(R.id.sign_up_phone);

    }

    public void signUpUser(View v) {
        String name = edtxtName.getText().toString();
        String email = edtxtEmail.getText().toString();
        String password = edtxtPassword.getText().toString();
        String phone = edtxtPhone.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !phone.isEmpty()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
