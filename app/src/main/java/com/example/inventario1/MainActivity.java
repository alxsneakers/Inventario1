package com.example.inventario1;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventario1.adaptadores.ListaProductosAdapter;
import com.example.inventario1.db.AdminSQLiteOpenHelper;
import com.example.inventario1.db.DbProductos;
import com.example.inventario1.entidades.Productos;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    RecyclerView listaProductos;
    ArrayList<Productos> listaArrayProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaProductos = findViewById(R.id.listaProductos);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DbProductos dbProductos = new DbProductos(MainActivity.this);

        listaArrayProductos = new ArrayList<>();

        //Le enviamos todos los resultados de la consulta
        ListaProductosAdapter adapter = new ListaProductosAdapter(dbProductos.mostrarProductos());
        //Traemos a listacontactos y le enviamos el adaptador
        listaProductos.setAdapter(adapter);

        //Timer para cargar la siguiente actividad
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 1000);


/*
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        //Creamos la base de datos
        SQLiteDatabase db = admin.getWritableDatabase();

        if(db != null){
            Toast.makeText(MainActivity.this,"Base de datos creada", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this,"ERROR crear base de datos", Toast.LENGTH_LONG).show();

        }
*/


   }



}