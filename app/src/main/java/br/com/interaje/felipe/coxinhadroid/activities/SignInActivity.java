package br.com.interaje.felipe.coxinhadroid.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.interaje.felipe.coxinhadroid.R;
import cz.msebera.android.httpclient.Header;

public class SignInActivity extends AppCompatActivity {

    private EditText edtxtEmail, edtxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SharedPreferences sharedPref = getSharedPreferences("prefsUser", Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "erro@erro.com");

        if (!email.isEmpty() && !email.equals("erro@erro.com")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

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

                doLogin(email, password);
                /*
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
                }*/
            } else {
                Toast.makeText(this, R.string.sign_in_toast_short_password, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.sign_in_toast_invalid_email, Toast.LENGTH_SHORT).show();
        }

    }

    private void doLogin(String email, String password) {
        AsyncHttpClient clientHttp = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.add("email", email);
        params.add("password", password);

        clientHttp.get("http://192.168.1.77:3000/users/login", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // Log.d("SIGN_IN_ACT", "Sucesso!" + new String (responseBody));
                String response = new String(responseBody);

                JSONArray arrayRoot = null;
                JSONObject objectUser = null;
                SharedPreferences sharedPref = getSharedPreferences("prefsUser", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                try {
                    arrayRoot = new JSONArray(response);
                    objectUser = arrayRoot.getJSONObject(1);

                    editor.putLong("id", objectUser.getLong("id"));
                    editor.putString("name", objectUser.getString("name"));
                    editor.putString("email", objectUser.getString("email"));

                    editor.commit();

                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                } catch (JSONException e) {
                    Log.e("SIGN_IN_ACT", "{doLogin}\nErro ao converter json: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String s = new String(responseBody);
                Log.d("SIGN_IN_ACT", "Falhou :(" + s);
            }
        });
    }
}
