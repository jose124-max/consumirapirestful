package com.example.consumirapirestful;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import webservice.Asynchtask;
import webservice.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://uealecpeterson.net/turismo/categoria/getlistadoCB", datos,
                MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtcate = (TextView)findViewById(R.id.txtcategorias);

        String lstcate="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject categoria= JSONlista.getJSONObject(i);
            lstcate = lstcate + "\n" +
                    categoria.getString("id").toString()+" -"+
                    categoria.getString("descripcion").toString();
        }
        txtcate.setText("" + lstcate);

    }
    public void seleccionar(View view){
        TextView txtidselec = (TextView)findViewById(R.id.txtselector);
        if(txtidselec.toString()!=""){

            Intent intent = new Intent(MainActivity.this, subcategorias.class);
            Bundle b = new Bundle();
            b.putString("ID", txtidselec.getText().toString());
            intent.putExtras(b);
            startActivity(intent);
        }

    }
}