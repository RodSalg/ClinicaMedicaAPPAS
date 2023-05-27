/*package com.example.aplicativoteste.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoP extends SQLiteOpenHelper {
    public static  String name = "banco.clinicaMedica";
    private static final int version = 1;

    public ConexaoP(Context context) {

        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table paciente (" +
                "id integer primary key autoincrement, " +
                "nome varchar(50) not null unique," +
                "especialidade varchar(60) , " +
                "horarioConsulta int not null," +
                "sintomas varchar(200) not null)");
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
*/