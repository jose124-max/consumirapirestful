package com.example.consumirapirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import webservice.Asynchtask;
import webservice.WebService;

public class subcategorias extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategorias);
        TextView txtidselec = (TextView)findViewById(R.id.textView);

        Bundle bundle = this.getIntent().getExtras();
        txtidselec.setText(bundle.getString("ID"));
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://uealecpeterson.net/turismo/subcategoria/getlistadoCB/"+bundle.getString("ID"), datos,
                subcategorias.this, subcategorias.this);
        ws.execute("GET");




    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtcate = (TextView)findViewById(R.id.txtsubcategorias);

        String lstcate="";
        //JSONObject jsonResponse = new JSONObject(result);
        //JSONArray JSONlista = jsonResponse.getJSONArray("data");


        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++){
            JSONObject categoria= JSONlista.getJSONObject(i);
            lstcate = lstcate + "\n" +
                    categoria.getString("id").toString()+" -"+
                    categoria.getString("descripcion").toString();
        }
        txtcate.setText(lstcate);

    }

    public void seleccionar2(View view){
        TextView txtidselec = (TextView)findViewById(R.id.txtselector2);
        if(txtidselec.toString()!=""){

            Intent intent = new Intent(subcategorias.this, MainActivity3.class);
            Bundle b = new Bundle();
            b.putString("ID2", txtidselec.getText().toString());
            intent.putExtras(b);
            startActivity(intent);
        }

    }
}