package com.example.inventario1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.example.inventario1.adaptadores.ListaProductosAdapter;
import com.example.inventario1.db.DbProductos;
import com.example.inventario1.entidades.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class InventarioActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView listaProductos;
    ArrayList<Productos> listaArrayProductos;
    SearchView txtBuscar;
    FloatingActionButton fabNuevo;
    ListaProductosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        listaProductos = findViewById(R.id.listaProductos);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));
        txtBuscar = findViewById(R.id.txtBuscar);
        fabNuevo = findViewById(R.id.fabNuevo);

        DbProductos dbProductos = new DbProductos(InventarioActivity.this);

        listaArrayProductos = new ArrayList<>();

        //Le enviamos todos los resultados de la consulta
        adapter = new ListaProductosAdapter(dbProductos.mostrarProductos());
        //Traemos a listacontactos y le enviamos el adaptador
        listaProductos.setAdapter(adapter);

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevoRegistro();
            }
        });

        txtBuscar.setOnQueryTextListener(this);

    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, AltaProductoActivity.class);
        startActivity(intent);
    }

    //Metodos para buscar en tiempo real
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrado(newText);
        return false;
    }
}