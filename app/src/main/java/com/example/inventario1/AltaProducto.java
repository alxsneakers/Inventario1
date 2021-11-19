package com.example.inventario1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inventario1.db.DbProductos;

public class AltaProducto extends AppCompatActivity {

    EditText editTxtSKU, editTxtItem, editTxtFecha, editTxtPrecio;
    Spinner spnrTallas;
    Button btnGuardar;
    //FALTA GENERO CHECKBOX

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_producto);


        //Los elementos id los asignamos a las variables
        editTxtSKU = findViewById(R.id.editTxtSKU);
        editTxtItem = findViewById(R.id.editTxtItem);
        editTxtFecha = findViewById(R.id.editTxtFecha);
        editTxtPrecio = findViewById(R.id.editTxtPrecio);

        spnrTallas = findViewById(R.id.spnrTallas);

        btnGuardar = findViewById(R.id.btnGuardar);

/*
        // Crear un ArrayAdapter utilizando el array de cadenas y un diseño de spinner por defecto
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.strTallas, android.R.layout.simple_spinner_item);
        // Especifique el diseño que se utilizará cuando aparezca la lista de opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplicar el adaptador al spinner
        spnrTallas.setAdapter(adapter);

*/
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LLamamos al metodo para insertar un registro
                DbProductos dbProductos = new DbProductos(AltaProducto.this);

                //Falta talla precio genero
                long id = dbProductos.insertarProducto(editTxtSKU.getText().toString(), editTxtItem.getText().toString(), editTxtFecha.getText().toString());

                if(id>0){
                    Toast.makeText(AltaProducto.this,"REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                } else{
                    Toast.makeText(AltaProducto.this,"ERROR ALREGISTRO GUARDADO", Toast.LENGTH_LONG).show();

                }

            }
        });


    }


    private void limpiar(){
        editTxtSKU.setText("");
        editTxtItem.setText("");
        editTxtFecha.setText("");
        editTxtPrecio.setText("");

    }


}