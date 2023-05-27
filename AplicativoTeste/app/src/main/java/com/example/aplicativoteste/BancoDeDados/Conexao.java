package com.example.aplicativoteste.BancoDeDados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {
    public static  String name = "banco.clinicaMedica";
    private static final int version = 1;

    public Conexao(Context context) {

        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("CRIEI A TABELA MEDICA \n\n\n\n\n");
        db.execSQL("create table medico (" +
                "id integer primary key autoincrement, " +
                "nome varchar(50) not null unique," +
                "especialidade varchar(60) not null, " +
                "horarioEntrada int not null," +
                "horarioSaida int not null)");


        System.out.println("CRIEI A TABELA PACIENTE \n\n\n\n\n");

        db.execSQL("create table paciente (" +
                "id integer primary key autoincrement, " +
                "nome varchar(50) not null unique," +
                "idade int not null, " +
                "especialidade varchar(60) , " +
                "horarioConsulta int not null," +
                "sintomas varchar(200) not null)");

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
