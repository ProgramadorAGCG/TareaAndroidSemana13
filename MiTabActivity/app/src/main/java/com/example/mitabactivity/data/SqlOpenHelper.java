package com.example.mitabactivity.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlOpenHelper extends SQLiteOpenHelper {

    public SqlOpenHelper(Context contexto){
        super(contexto,"basededatos.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario TEXT," +
                "clave TEXT);");
        db.execSQL("insert into usuario values(null,'admin',123)");
        db.execSQL("insert into usuario values(null,'sa',456)");
        db.execSQL("CREATE TABLE movimientos(" +
                "idmovimiento INTEGER PRIMARY KEY AUTOINCREMENT," +
                "descripcion TEXT," +
                "monto float," +
                "movimiento int)");
        db.execSQL("insert into movimientos values(null,'deposito',100,1)");
        db.execSQL("insert into movimientos values(null,'retiro',10,-1)");
        db.execSQL("insert into movimientos values(null,'deposito',40,1)");
        db.execSQL("insert into movimientos values(null,'retiro',10,-1)");
        db.execSQL("insert into movimientos values(null,'deposito',100,1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
