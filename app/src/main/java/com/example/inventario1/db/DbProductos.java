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
    //Metodo para insertar los parametros en la tabla
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

    //Funcion para mostrar los productos en la activity Inventario
    public ArrayList<Productos> mostrarProductos(){
        //llamamos a la base de datos
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
        //Cerramos la base de datos
        cursorProductos.close();
        return listaProductos;

    }

    //Funcion para ver el producto con detalles

    public Productos verProducto(int id){
        //Creamos la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();

        Productos producto = null;
        Cursor cursorProductos = null;

        //Realizamos Consulta a la tabla de productos,
        //Devolvera tipo cursor

        //Select para buscar un solo producto
        cursorProductos = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " WHERE id = " + id + " LIMIT 1", null);

        //Validador para Mover el primer resultado de la consulta al cursor
        if(cursorProductos.moveToFirst()){
            producto = new Productos();
            producto.setID(cursorProductos.getInt(0));
            producto.setSKU(cursorProductos.getString(1));
            producto.setItem(cursorProductos.getString(2));
            producto.setTalla(cursorProductos.getString(3));
            producto.setFecha(cursorProductos.getString(4));
            producto.setPrecio(cursorProductos.getDouble(5));

        }
        cursorProductos.close();
        return producto;

    }

    //Funcion para editar el producto
    public boolean editarProducto(int id, String sku, String item, String fecha){
        boolean correcto = false;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();
        try {
            //Insertar registro a la tabla
            db.execSQL( "UPDATE " + TABLE_PRODUCTOS + " SET SKU = '" + sku + "', ITEM = '" + item + "', FECHA = '" + fecha + "' WHERE ID = '" + id + "' " );

            //ORIGINAL - FALTA PRECIO GENERO TALLA
            //db.execSQL( "UPDATE" + TABLE_PRODUCTOS + " SET SKU = '" + sku + "', ITEM = '" + item + "', TALLA = '" + talla + "', FECHA = '" + fecha + "', PRECIO = '" + precio + "', GENERO = '" + genero + "' WHERE ID = '" + id + "' " );

            correcto = true;

        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    //Funcion para Eliminr el producto
    public boolean eliminarProducto(int id){
        boolean correcto = false;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context);
        SQLiteDatabase db = admin.getWritableDatabase();
        try {
            //Insertar registro a la tabla
            db.execSQL( "DELETE FROM " + TABLE_PRODUCTOS + " WHERE ID = '" + id + "' " );

            correcto = true;

        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

}
