package com.example.aplicativoteste.PacientesDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aplicativoteste.BancoDeDados.Conexao;
import com.example.aplicativoteste.MedicosDados.Medico;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public PacienteDAO(Context context)
    {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Paciente paciente)
    {
        ContentValues values = new ContentValues();
        values.put("nome", paciente.getNome());
        values.put("idade", paciente.getIdade());
        values.put("especialidade", paciente.getEspecialidade());
        values.put("horarioConsulta", paciente.getHorarioConsulta());
        values.put("sintomas", paciente.getSintomas());

        return banco.insert("Paciente", null, values);
    }

    public List<Paciente> obterTodosPacientes()
    {
        List<Paciente> Pacientes = new ArrayList<>();
        Cursor cursor = banco.query("paciente", new String[]{"id","nome", "idade", "especialidade", "horarioConsulta", "sintomas"},
                null, null, null, null, null);

        while(cursor.moveToNext())
        {
            Paciente p = new Paciente();
            p.setId(cursor.getInt(0));
            p.setNome(cursor.getString(1));
            p.setIdade(cursor.getInt(2));
            p.setEspecialidade(cursor.getString(3));
            p.setHorarioConsulta(cursor.getInt(4));
            p.setSintomas(cursor.getString(5));

            Pacientes.add(p);

        }

        return Pacientes;
    }

    public void excluir(Paciente p)
    {

        banco.delete("paciente", "id = ?",  new String[]{p.getId()+""} );
    }

    public void atualizar(Paciente p)
    {

        ContentValues values = new ContentValues();
        values.put("nome", p.getNome());
        values.put("idade", p.getIdade());
        values.put("especialidade", p.getEspecialidade());
        values.put("horarioConsulta", p.getHorarioConsulta());
        values.put("sintomas", p.getSintomas());

        banco.update("paciente",values, "id = ?", new String[] { p.getId()+"" } );

    }



}
