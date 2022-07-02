package com.example.mitabactivity.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mitabactivity.data.SqlOpenHelper;
import com.example.mitabactivity.entidad.Movimientos;

import java.util.ArrayList;

public class MovimientoDaoImpl {

    SqlOpenHelper admin;

    public MovimientoDaoImpl(Context contexto) {
        admin=new SqlOpenHelper(contexto);
    }

    //método que retorne la lista de movimientos
    public ArrayList<Movimientos> listaMovimientos(){
        ArrayList<Movimientos> data=new ArrayList<Movimientos>();
        Movimientos bean=null;
        SQLiteDatabase base=admin.getReadableDatabase();

        Cursor cursor=base.rawQuery("select * from movimientos",null);
        while (cursor.moveToNext()){
            bean=new Movimientos();
            bean.setIdmovimiento(cursor.getInt(0));
            bean.setDescripcion(cursor.getString(1));
            bean.setMonto(cursor.getDouble(2));
            bean.setMovimiento(cursor.getInt(3));
            data.add(bean);
        }


        return  data;

    }
    public ArrayList<Movimientos> listaSpinner(){
        ArrayList<Movimientos> data=new ArrayList<Movimientos>();
        Movimientos bean=null;
        SQLiteDatabase base=admin.getReadableDatabase();

        Cursor cursor=base.rawQuery("select descripcion from movimientos",null);
        while (cursor.moveToNext()){
            bean=new Movimientos();
            bean.setDescripcion(cursor.getString(0));

            data.add(bean);
        }


        return  data;

    }
    public int registrarMovimiento(Movimientos bean){
        int salida=-1;
        SQLiteDatabase base=admin.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put("descripcion",bean.getDescripcion());
        contentValues.put("monto",bean.getMonto());
        contentValues.put("movimiento",bean.getMovimiento());
        salida=(int)base.insert("movimientos","idmovimiento",contentValues);

        return salida;
    }
    public int actualizarMovimiento(Movimientos bean){


        int salida=-1;
        //abrir acceso a la bd en modo escritura
        SQLiteDatabase base=admin.getWritableDatabase();

        //Crear objeto de la clase ContentValues, dicha clase trabajaa con claves y valores
        ContentValues contentValues=new ContentValues();

//ojo son los campos lo que colocas en put

        contentValues.put("idmovimiento",bean.getIdmovimiento());
        contentValues.put("descripcion",bean.getDescripcion());
        contentValues.put("monto",bean.getMonto());
        contentValues.put("movimiento",bean.getMovimiento());
        //ojo con campos
        salida=(int)base.update("movimientos",contentValues,"idmovimiento="+bean.getIdmovimiento(),null);

        return salida;
    }
    public int eliminarrMovimiento(int codigo){
        int salida=-1;
        SQLiteDatabase base=admin.getWritableDatabase();
        salida=(int)base.delete("movimientos","idmovimiento="+codigo,null);

        return salida;
    }

}
