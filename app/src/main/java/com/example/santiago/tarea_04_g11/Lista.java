package com.example.santiago.tarea_04_g11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class Lista extends AppCompatActivity {

    String lineatxt;
    String txtcompleto;

    private ListView lista;
    String datos[];
    String datos1[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        lista=(ListView)findViewById(R.id.listareg);

        try {
            InputStreamReader archivo = new InputStreamReader(openFileInput("meminterna.txt"));
            BufferedReader br = new BufferedReader(archivo);
            lineatxt = br.readLine();
            txtcompleto= "";

            while (lineatxt !=null){
                txtcompleto=txtcompleto + lineatxt + "\n";
                datos= txtcompleto.split(";");
                lineatxt=br.readLine();
            }
            br.close();
            archivo.close();
            //contraseña.setText(txtcompleto);

        }catch (IOException e){

        }

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        lista.setAdapter(adapter);



    }
}
