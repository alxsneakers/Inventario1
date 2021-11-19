package com.example.inventario1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void btnEntrada(View view) {
        Intent i = new Intent(this, AltaProducto.class );
        startActivity(i);
    }

    public void btnInventario(View view) {
        Intent i = new Intent(this, Inventario.class );
        startActivity(i);
    }
}