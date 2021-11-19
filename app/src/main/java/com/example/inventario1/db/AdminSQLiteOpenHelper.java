package com.example.inventario1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    //Version de db que vamos a usar
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "inventario.db";
    public static final String TABLE_PRODUCTOS = "tabla_productos";


    //Constructor
    public AdminSQLiteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    //Metodo para crear la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "  + TABLE_PRODUCTOS +  "(   ID INTEGER PRIMARY KEY AUTOINCREMENT, SKU  VARCHAR(15) NOT NULL,   ITEM  VARCHAR(255) NOT NULL,   TALLA  VARCHAR(6) ,   FECHA  VARCHAR(6), PRECIO DOUBLE, GENERO VARCHAR(6))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists " + TABLE_PRODUCTOS +"");

        onCreate(db);


    }
}
