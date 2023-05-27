package com.example.aplicativoteste.InterfaceAndroid;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.aplicativoteste.BancoDeDados.Conexao;
//import com.example.aplicativoteste.BancoDeDados.ConexaoP;
import com.example.aplicativoteste.InterfaceAndroid.AreaRecepcao.Recepcao;
import com.example.aplicativoteste.MedicosDados.Medico;
import com.example.aplicativoteste.MedicosDados.MedicoDAO;
import com.example.aplicativoteste.PacientesDados.Paciente;
import com.example.aplicativoteste.PacientesDados.PacienteDAO;
import com.example.aplicativoteste.R;

public class MainActivity extends AppCompatActivity {
    private Conexao conexao;
    //private ConexaoP conexaoP;
    private SQLiteDatabase banco;

    private MedicoDAO dao;
    private Medico medico = null;

    private PacienteDAO daoP;
    private Paciente paciente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * a primeira vez que eu criar o app já vou criar os dois banco de dados essenciais para que nao haja erros*/
        conexao = new Conexao(this);
        banco = conexao.getWritableDatabase();



        /*conexaoP = new ConexaoP(this);
        banco = conexaoP.getWritableDatabase();
        insereAut();*/
    }

    public void telaMenu(View v)
    {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }









}