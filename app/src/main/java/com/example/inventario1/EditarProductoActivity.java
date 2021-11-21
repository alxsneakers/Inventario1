package com.example.inventario1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventario1.db.DbProductos;
import com.example.inventario1.entidades.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarProductoActivity extends AppCompatActivity {
    //Vamos a recibir el id que nos esta pasando la lista, llamaremos al metodo para traer la informacion del registro


    //FALTA HACER PRECIO
    EditText editTxtSKU, editTxtItem, editTxtFecha, editTxtPrecio;
    Button btnGuardar;
    boolean correcto = false;
    Productos producto;

    FloatingActionButton fabEditar, fabEliminar;


    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);

        editTxtSKU = findViewById(R.id.editTxtSKU);
        editTxtItem = findViewById(R.id.editTxtItem);
        editTxtFecha = findViewById(R.id.editTxtFecha);
        editTxtPrecio = findViewById(R.id.editTxtPrecio);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);


        btnGuardar = findViewById(R.id.btnGuardar);

        //Recibir la variable id

        if(savedInstanceState == null){
            //Lo recibimos como un extra por que en ListaProductoAdaptar lo enviamos como un extra Linea 79
            Bundle extras = getIntent().getExtras();

            //Validacion por si recibimos extras como un nulo
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            //Diferente maneras de recibir el parametro id
            id = (int) savedInstanceState.getSerializable("ID");
        }
        // LLamamos a nuestra consulta verProducto
        DbProductos dbProductos = new DbProductos(EditarProductoActivity.this);
        producto = dbProductos.verProducto(id);

        //Asignar los valores
        if(producto != null){
            editTxtSKU.setText(producto.getSKU());
            editTxtItem.setText(producto.getItem());
            editTxtFecha.setText(producto.getFecha());
            //editTxtPrecio.setText(producto.getPrecio());

        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CAMPOS OBLIGATORIOS
                if(!editTxtSKU.getText().toString().equals("") && !editTxtItem.getText().toString().equals("")){
                    correcto = dbProductos.editarProducto(id, editTxtSKU.getText().toString(), editTxtItem.getText().toString(),editTxtFecha.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarProductoActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else{
                        Toast.makeText(EditarProductoActivity.this, "ERROR AL MODIFICAR EL REGISTRO", Toast.LENGTH_LONG).show();

                    }
                } else{
                    Toast.makeText(EditarProductoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerProductoActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}