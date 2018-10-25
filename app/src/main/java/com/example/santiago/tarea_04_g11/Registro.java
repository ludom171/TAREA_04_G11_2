package com.example.santiago.tarea_04_g11;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText usuario;
    EditText contraseña;
    EditText nombre;
    EditText apellido;
    EditText correo;
    EditText celular;

    String lineatxt;
    String txtcompleto;

    Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        //inicializacion de variables
        usuario = (EditText)findViewById(R.id.usuario_registro);
        contraseña = (EditText)findViewById(R.id.contraseña_registro);
        nombre  = (EditText)findViewById(R.id.nombre);
        apellido = (EditText)findViewById(R.id.apellido);
        correo  = (EditText)findViewById(R.id.correo);
        celular  = (EditText)findViewById(R.id.celular);

        aceptar=(Button)findViewById(R.id.aceptar);





    }
    @Override
    public void onClick(View v) {

    }

    public  void  Guardar(View view){

        try {
            InputStreamReader archivo = new InputStreamReader(openFileInput("meminterna.txt"));
            BufferedReader br = new BufferedReader(archivo);
            lineatxt = br.readLine();
            txtcompleto= "";

            while (lineatxt !=null){
                txtcompleto=txtcompleto + lineatxt + "\n";
                lineatxt=br.readLine();
            }
            br.close();
            archivo.close();
            //contraseña.setText(txtcompleto);

        }catch (IOException e){

        }

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("meminterna.txt", Activity.MODE_PRIVATE));
            archivo.write(txtcompleto + usuario.getText().toString() + ";" + contraseña.getText().toString() + ";" +nombre.getText().toString() + ";" +apellido.getText().toString() + ";" +correo.getText().toString() + ";" +celular.getText().toString());
            archivo.flush();
            archivo.close();
        }catch (IOException e){

        }
        Toast.makeText(this, "Guardado",Toast.LENGTH_SHORT).show();
    }
}
