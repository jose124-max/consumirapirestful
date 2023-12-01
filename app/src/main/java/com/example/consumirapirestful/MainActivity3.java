package com.example.consumirapirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import webservice.Asynchtask;
import webservice.WebService;

public class MainActivity3 extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGridLT/"+bundle.getString("ID")+"/"+bundle.getString("ID2"), datos,
                MainActivity3.this, MainActivity3.this);
        ws.execute("GET");


    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtcate = (TextView)findViewById(R.id.txtcategorias3);

        String lstcate="";
        JSONObject jsonResponse = new JSONObject(result);
        JSONArray JSONlista = jsonResponse.getJSONArray("data");


        for(int i=0; i< JSONlista.length();i++){
            JSONObject categoria= JSONlista.getJSONObject(i);
            lstcate = lstcate + "\n" +
                    categoria.getString("id").toString()+" -"+
                    categoria.getString("subcategoria_id").toString();

            categoria.getString("nombre_lugar").toString();
        }
        txtcate.setText(lstcate);
    }
}