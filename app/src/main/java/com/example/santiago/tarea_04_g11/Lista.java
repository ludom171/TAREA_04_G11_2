package com.example.santiago.tarea_04_g11;

import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lista extends AppCompatActivity {

    String lineatxt;
    String txtcompleto;


    //variables y contenedor listciew
    private ListView lista;

    //almacenar datos de txt en arreglo
    String datos[];
    String datos1[];

    //inicializar expandible lista
    private ExpandableListView expLV;
    private ListaDatos adapter;
    private ArrayList<String> listacategoria;
    private Map<String, ArrayList<String>> mapChild;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);


        //inicializa variable listview
        lista=(ListView)findViewById(R.id.listareg);

        //captura de datos de txt para manipulacion interna
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

        //adaptador contiene datos extraidos de txt para mostrar en listview
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_usuario, datos);
        lista.setAdapter(adapter);


        expLV =(ExpandableListView)findViewById(R.id.listaexpand);
        listacategoria = new ArrayList<>();
        mapChild = new HashMap<>();



        ////SUBLISTAS




        // cargarDatos();


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

    private void cargarDatos(){
        ArrayList<String> lista1=new ArrayList<>();
        ArrayList<String> lista2=new ArrayList<>();


        listacategoria.add("usuario1");
        listacategoria.add("usuario2");


        lista1.add("nombre");
        lista1.add("apellido");
        lista1.add("correo");
        lista1.add("telefono");

        lista2.add("nombre2");
        lista2.add("apellido2");
        lista2.add("correo2");
        lista2.add("telefono2");

        mapChild.put(listacategoria.get(0),lista1);
        mapChild.put(listacategoria.get(1),lista2);

        adapter= new ListaDatos(this, listacategoria,mapChild);
        expLV.setAdapter(adapter);

    }
}
