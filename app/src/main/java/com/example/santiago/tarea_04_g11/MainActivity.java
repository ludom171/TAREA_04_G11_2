package com.example.santiago.tarea_04_g11;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String lineatxt,txtcompleto;

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
            //Toast.makeText(getApplicationContext(),"Fichero Existente",Toast.LENGTH_SHORT).show();
            fin.close();
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("meminterna.txt"));
                BufferedReader br = new BufferedReader(archivo);
                lineatxt = br.readLine();
                txtcompleto= "";


                br.close();
                archivo.close();
                //contraseña.setText(txtcompleto);

            }catch (IOException e){

            }
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
            //Toast.makeText(getApplicationContext(),"Fichero no Existente",Toast.LENGTH_SHORT).show();
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

                try {
                    InputStreamReader archivo = new InputStreamReader(openFileInput("meminterna.txt"));
                    BufferedReader br = new BufferedReader(archivo);
                    lineatxt = br.readLine();


                    if (lineatxt !=null){
                        Intent newform = new Intent(MainActivity.this,Lista.class);
                        finish();
                        startActivity(newform);
                    }else{
                        Toast.makeText(getApplicationContext(),"No existe Registros Disponibles",Toast.LENGTH_SHORT).show();
                    }
                    br.close();
                    archivo.close();

                }catch (IOException e){

                }



            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newform = new Intent(MainActivity.this,Registro.class);
                finish();
                startActivity(newform);
            }
        });

    }

    //submenu salir
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.submenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onClick(View v) {


    }


}

