package com.example.inventario1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.inventario1.MainActivity;
import com.example.inventario1.entidades.Productos;

import java.sql.SQLData;
import java.util.ArrayList;

//clase para llevar los registros a la tabla
public class DbProductos extends AdminSQLiteOpenHelper{

    Context context;

    public DbProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    //Metodo para recibir los parametros para la tabla
    //public long insertarProducto(String sku, String item, String talla,  String fecha, double precio, String genero){
    public long insertarProducto(String sku, String item, String fecha){
        long id = 0;
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
            SQLiteDatabase db = admin.getWritableDatabase();

            //Insertar registro con ContentValues
            ContentValues values = new ContentValues();
            values.put("sku", sku);
            values.put("item", item);
            //values.put("talla", talla);
            values.put("fecha", fecha);
            //values.put("precio", precio);
            //values.put("genero", genero);

            //Insertar registro a la tabla
            id = db.insert(TABLE_PRODUCTOS , null,values);

        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<Productos> mostrarProductos(){
        //Creamos la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        //ArrayList para almacenar los productos
        ArrayList<Productos> listaProductos = new ArrayList<>();
        Productos producto = null;
        Cursor cursorProductos = null;

        //Realizamos Consulta a la tabla de productos,
        //Devolvera tipo cursor

        //Select
        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS, null);

        //Validador para Mover el primer resultado de la consulta al cursor
        if(cursorProductos.moveToFirst()){
            //do-while para recorrer toda las filas de la tabla
            do{
                producto = new Productos();
                producto.setID(cursorProductos.getInt(0));
                producto.setSKU(cursorProductos.getString(1));
                producto.setItem(cursorProductos.getString(2));
                producto.setTalla(cursorProductos.getString(3));
                producto.setFecha(cursorProductos.getString(4));
                producto.setPrecio(cursorProductos.getDouble(5));
                
                listaProductos.add(producto);

            }while(cursorProductos.moveToNext());
        }
        cursorProductos.close();
        return listaProductos;

    }

}
