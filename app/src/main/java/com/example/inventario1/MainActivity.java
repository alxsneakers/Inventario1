package com.example.inventario1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

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


        //Timer para cargar la siguiente actividad
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 3000);



   }



}