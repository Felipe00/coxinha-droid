package br.com.interaje.felipe.coxinhadroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.interaje.felipe.coxinhadroid.R;
import br.com.interaje.felipe.coxinhadroid.models.Admin;

public class SignInActivity extends AppCompatActivity {

    private EditText edtxtEmail, edtxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtxtEmail = (EditText) findViewById(R.id.sign_in_email);
        edtxtPassword = (EditText) findViewById(R.id.sign_in_password);
    }

    public void signUpBtn(View v) {
        startActivity(new Intent(this, SignUpActivity.class));
        finish();
    }

    public void signInBtn(View v) {
        String email = edtxtEmail.getText().toString();
        String password = edtxtPassword.getText().toString();

        // Se o email contem @ e . ===> Verdadeiro
        if (email.contains("@") && email.contains(".")) {
            // Se a senha eh diferente de vazio e maior ou igual a 4 ===> Verdadeiro
            if (!password.isEmpty() && password.length() >= 4) {
                // Se a combinaçao Email + Senha estiver correta. ===> Verdadeiro
                List<Admin> adminList = Admin.find(Admin.class, "email = ? and limit 2", email);
                if (adminList.size() > 0) {
                    Admin adminDb = adminList.get(0);
                    if (adminDb.getPassword().equals(password)) {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, R.string.sign_in_toast_invalid_email_password, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, R.string.sign_in_toast_invalid_email_password, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.sign_in_toast_short_password, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.sign_in_toast_invalid_email, Toast.LENGTH_SHORT).show();
        }

    }
}
