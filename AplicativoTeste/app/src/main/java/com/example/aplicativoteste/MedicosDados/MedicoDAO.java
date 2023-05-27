package com.example.aplicativoteste.MedicosDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aplicativoteste.BancoDeDados.Conexao;

import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public MedicoDAO(Context context)
    {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Medico medico)
    {
        ContentValues values = new ContentValues();
        values.put("nome", medico.getNome());
        values.put("especialidade", medico.getEspecialidade());
        values.put("horarioEntrada", medico.getHorarioEntrada());
        values.put("horarioSaida", medico.getHorarioSaida());

        return banco.insert("Medico", null, values);
    }

    public List<Medico> obterTodos()
    {
        List<Medico> medicos = new ArrayList<>();
        Cursor cursor = banco.query("medico", new String[]{"id","nome", "especialidade", "horarioEntrada", "horarioSaida"},
                null, null, null, null, null);

        while(cursor.moveToNext())
        {
            Medico m = new Medico();
            m.setId(cursor.getInt(0));
            m.setNome(cursor.getString(1));
            m.setEspecialidade(cursor.getString(2));
            m.setHorarioEntrada(cursor.getInt(3));
            m.setHorarioSaida(cursor.getInt(4));

            medicos.add(m);

        }

        return medicos;
    }

    public void excluir(Medico m)
    {

        banco.delete("medico", "id = ?",  new String[]{m.getId()+""} );
    }

    public void atualizar(Medico medico)
    {

        ContentValues values = new ContentValues();
        values.put("nome", medico.getNome());
        values.put("especialidade", medico.getEspecialidade());
        values.put("horarioEntrada", medico.getHorarioEntrada());
        values.put("horarioSaida", medico.getHorarioSaida());

        banco.update("medico",values, "id = ?", new String[] { medico.getId()+"" } );

    }

    public List<Medico> buscarPorEspecialidadeListaMedicos(String especialidade) {
        List<Medico> medicos = new ArrayList<>();
        Cursor cursor = banco.query("medico", new String[]{"id","nome", "especialidade", "horarioEntrada", "horarioSaida"},
                "especialidade = ?", new String[]{especialidade}, null, null, null);

        while(cursor.moveToNext()) {
            Medico m = new Medico();
            m.setId(cursor.getInt(0));
            m.setNome(cursor.getString(1));
            m.setEspecialidade(cursor.getString(2));
            m.setHorarioEntrada(cursor.getInt(3));
            m.setHorarioSaida(cursor.getInt(4));

            medicos.add(m);
        }

        return medicos;
    }

    public boolean buscarPorEspecialidadeTF(String especialidade) {
        Cursor cursor = banco.rawQuery("SELECT * FROM Medico WHERE especialidade = ?", new String[] {especialidade});

        boolean existe = cursor.moveToFirst();
        cursor.close();

        return existe;
    }

    public  Medico buscarPorEspecialidade(String especialidade) {
        Medico medicoEncontrado = null;
        Cursor cursor = banco.query("medico", new String[]{"id", "nome", "especialidade", "horarioEntrada", "horarioSaida"},
                "especialidade = ?", new String[]{especialidade}, null, null, null);
        if (cursor.moveToNext()) {
            medicoEncontrado = new Medico();
            medicoEncontrado.setId(cursor.getInt(0));
            medicoEncontrado.setNome(cursor.getString(1));
            medicoEncontrado.setEspecialidade(cursor.getString(2));
            medicoEncontrado.setHorarioEntrada(cursor.getInt(3));
            medicoEncontrado.setHorarioSaida(cursor.getInt(4));
        }
        cursor.close();
        return medicoEncontrado;
    }







}
