package br.com.interaje.felipe.coxinhadroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import br.com.interaje.felipe.coxinhadroid.R;
import br.com.interaje.felipe.coxinhadroid.models.Coxinha;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AddCoxinhaActivity extends AppCompatActivity {

    private EditText edtxFlavor, edtxDescription, edtxPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coxinha);

        edtxFlavor = (EditText) findViewById(R.id.coxinha_flavor);
        edtxDescription = (EditText) findViewById(R.id.coxinha_description);
        edtxPrice = (EditText) findViewById(R.id.coxinha_price);

    }

    public void registerCoxinha(View v) {
        String flavor = edtxFlavor.getText().toString();
        String desc = edtxDescription.getText().toString();
        Double price = Double.parseDouble(edtxPrice.getText().toString());

        Coxinha coxinha = new Coxinha(flavor, desc, price);

        coxinha.save();

        AsyncHttpClient clientHttp = new AsyncHttpClient();

        JSONObject object = new JSONObject();
        JSONObject rootParam = new JSONObject();
        try {
            object.put("id", coxinha.getId());
            object.put("description", coxinha.getFlavor());
            object.put("price", coxinha.getPrice());
            rootParam.put("coxinha", object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity entity = null;

        try {
            entity = new StringEntity(object.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        clientHttp.post(this, "http://192.168.1.77:3000/coxinhas/registerCoxinha", entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("ADD_COXINHA_ACT", "Sucesso!\n Resultado do servidor: " + new String(responseBody));
                startActivity(new Intent(AddCoxinhaActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("ADD_COXINHA_ACT", "Deu Errado!\n Resultado do servidor: " + new String(responseBody));
            }
        });

    }
}
