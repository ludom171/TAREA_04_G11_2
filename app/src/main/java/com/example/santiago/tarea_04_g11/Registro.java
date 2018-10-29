package com.example.santiago.tarea_04_g11;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

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
    //declarar spinner
    Spinner dia,mes,anio;
    int numero;

    //Cargar Imagen
    ImageView imagen;

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
        //inicializar spinner
        dia=(Spinner)findViewById(R.id.dia);
        dia.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        mes=(Spinner)findViewById(R.id.mes);
        anio=(Spinner)findViewById(R.id.anio);

        numero=1960;
        //valores spinner dia y mes
        String [] opcdia={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String [] opcmes={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        //generar valores spinner años
        int year =Calendar.getInstance().get(Calendar.YEAR);
        int size =year-numero;

        String[] years = new String[size+1];
        for (int x=0;x<years.length;x++){
            years[x]=String.valueOf(numero+x);
        }

        //Image View
        imagen= (ImageView) findViewById(R.id.foto);



        //llenar spiiner con valores generados
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcdia);
        dia.setAdapter(adapter);

        ArrayAdapter <String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcmes);
        mes.setAdapter(adapter1);

        ArrayAdapter <String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        anio.setAdapter(adapter2);

    }


    @Override
    public void onClick(View v) {

    }


    //metodo guardar datos en fichero txt
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
        //guardar datos txt
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("meminterna.txt", Activity.MODE_PRIVATE));
            //ValidarFecha();

            archivo.write(txtcompleto + usuario.getText().toString() + ";" + contraseña.getText().toString() + ";" +nombre.getText().toString() + ";" +apellido.getText().toString() + ";" +correo.getText().toString() + ";" +celular.getText().toString()+";");
            archivo.flush();
            archivo.close();
        }catch (IOException e){

        }
        Toast.makeText(this, "Guardado",Toast.LENGTH_SHORT).show();
        Intent newform = new Intent(Registro.this,MainActivity.class);
        finish();
        startActivity(newform);
    }

    private void ValidarFecha(Integer dia, Integer mes, Integer anio) {
        dia=dia.intValue();
        mes=mes.intValue();
        anio=anio.intValue();

        if(anio>=1&&dia<=31){
            Toast.makeText(this, "es un dia valido",Toast.LENGTH_SHORT).show();
        }


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

    public void onclick(View view) {
        Intent intent= new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicacion"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path= data.getData();
            imagen.setImageURI(path);
        }
    }



}
