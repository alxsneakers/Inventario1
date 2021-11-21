package com.example.inventario1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.inventario1.db.DbProductos;
import com.example.inventario1.entidades.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerProductoActivity extends AppCompatActivity {
    //Vamos a recibir el id que nos esta pasando la lista, llamaremos al metodo para traer la informacion del registro


    //FALTA HACER PRECIO
    EditText editTxtSKU, editTxtItem, editTxtFecha, editTxtPrecio;
    Button btnGuardar;
    FloatingActionButton fabEditar, fabEliminar;

    Productos producto;
    //Id que vamos a usar en todas las funciones
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
        fabEliminar = findViewById(R.id.fabEliminar);


        btnGuardar = findViewById(R.id.btnGuardar);

        //Recibimos la variable id como un extra

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
        DbProductos dbProductos = new DbProductos(VerProductoActivity.this);
        producto = dbProductos.verProducto(id);

        //Asignar los valores
        if(producto != null){
            editTxtSKU.setText(producto.getSKU());
            editTxtItem.setText(producto.getItem());
            editTxtFecha.setText(producto.getFecha());
            //editTxtPrecio.setText(producto.getPrecio());

            btnGuardar.setVisibility(View.INVISIBLE);

            editTxtSKU.setInputType(InputType.TYPE_NULL);
            editTxtItem.setInputType(InputType.TYPE_NULL);
            editTxtFecha.setInputType(InputType.TYPE_NULL);

        }

        //Llamamo al activity editar
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerProductoActivity.this, EditarProductoActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        //Eliminar Registro
        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerProductoActivity.this);
                builder.setMessage("¿Desea Eliminar este contacto?.")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                //AL hacer click en Si llamamos al metodo de eliminar en la base de datos.
                                if(dbProductos.eliminarProducto(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }
                        }).show();
            }
        });

    }

    private void lista(){
        Intent intent = new Intent(this, InventarioActivity.class);
        startActivity(intent);
    }
}