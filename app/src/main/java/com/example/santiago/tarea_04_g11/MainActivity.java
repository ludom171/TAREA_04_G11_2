package com.example.santiago.tarea_04_g11;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Button ingresar;
        Button registro;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresar=(Button)findViewById(R.id.ingresar);
        registro=(Button)findViewById(R.id.registrar);

        //Verificacion de archivo
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("meminterna.txt")));

            String texto = fin.readLine();
            Toast.makeText(getApplicationContext(),"Fichero Existente",Toast.LENGTH_SHORT).show();
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
            Toast.makeText(getApplicationContext(),"Fichero no Existente",Toast.LENGTH_SHORT).show();
            //Creacion de archivo

            try
            {
                OutputStreamWriter fout=
                        new OutputStreamWriter(
                                openFileOutput("meminterna.txt", Context.MODE_PRIVATE));
                fout.close();
            }
            catch (Exception e)
            {
                Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
            }
        }

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String user = ((EditText)findViewById(R.id.nombre)).getText().toString();
                // String pas = ((EditText)findViewById(R.id.contraseña)).getText().toString();
                Intent newform = new Intent(MainActivity.this,Lista.class);
                startActivity(newform);

            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String user = ((EditText)findViewById(R.id.nombre)).getText().toString();
                // String pas = ((EditText)findViewById(R.id.contraseña)).getText().toString();
                Intent newform = new Intent(MainActivity.this,Registro.class);
                startActivity(newform);

            }
        });

    }
    public void onClick(View v) {


    }


}

