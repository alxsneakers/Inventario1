package com.example.inventario1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.inventario1.db.DbProductos;

import java.util.Calendar;

public class AltaProductoActivity extends AppCompatActivity {

    EditText editTxtSKU, editTxtItem, editTxtFecha, editTxtPrecio;
    Spinner spnrTallas;
    Button btnGuardar;
    boolean correcto = false;

    //FALTA GENERO CHECKBOX PRECIO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_producto);


        //Los elementos id los asignamos a las variables
        editTxtSKU = findViewById(R.id.editTxtSKU);
        editTxtItem = findViewById(R.id.editTxtItem);
        editTxtFecha = findViewById(R.id.editTxtFecha);
        //Desactivar teclado
        editTxtFecha.setInputType(InputType.TYPE_NULL);

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
                DbProductos dbProductos = new DbProductos(AltaProductoActivity.this);

                //Falta talla precio genero


                //Verificacion para campos vacios en el alta
                if(!editTxtSKU.getText().toString().equals("") && !editTxtItem.getText().toString().equals("")){
                    long id = dbProductos.insertarProducto(editTxtSKU.getText().toString(), editTxtItem.getText().toString(), editTxtFecha.getText().toString());

                    if(id>0){
                        Toast.makeText(AltaProductoActivity.this,"REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else{
                        Toast.makeText(AltaProductoActivity.this,"ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();

                    }
                } else{
                    Toast.makeText(AltaProductoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
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


    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(AltaProductoActivity.this, R.style.DialogTheme ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + month + "/" + year  ;
                editTxtFecha.setText(fecha);
            }
        },anio,mes,dia);
        dpd.show();

    }


}