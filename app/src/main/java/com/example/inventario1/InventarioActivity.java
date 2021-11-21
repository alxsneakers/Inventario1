package com.example.inventario1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inventario1.adaptadores.ListaProductosAdapter;
import com.example.inventario1.db.DbProductos;
import com.example.inventario1.entidades.Productos;

import java.util.ArrayList;

public class InventarioActivity extends AppCompatActivity {

    RecyclerView listaProductos;
    ArrayList<Productos> listaArrayProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        listaProductos = findViewById(R.id.listaProductos);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DbProductos dbProductos = new DbProductos(InventarioActivity.this);

        listaArrayProductos = new ArrayList<>();

        //Le enviamos todos los resultados de la consulta
        ListaProductosAdapter adapter = new ListaProductosAdapter(dbProductos.mostrarProductos());
        //Traemos a listacontactos y le enviamos el adaptador
        listaProductos.setAdapter(adapter);
    }
}