package com.example.aplicativoteste.InterfaceAndroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;

import com.example.aplicativoteste.InterfaceAndroid.AreaRecepcao.ListaPacientes;
import com.example.aplicativoteste.InterfaceAndroid.AreaRecepcao.Recepcao;
import com.example.aplicativoteste.InterfaceAndroid.AreasMedicos.AreaCadastroMedico;
import com.example.aplicativoteste.R;

public class Menu extends AppCompatActivity {

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_geral, menu);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void TelaCadastrarMedico(View v)
    {
        Intent i = new Intent(this, AreaCadastroMedico.class);
        startActivity(i);
    }

    public void TelaRecepcao(View v)
    {
        Intent i = new Intent(this, Recepcao.class);
        startActivity(i);
    }



    public void telaAreaMedica(View v)
    {

        Intent i = new Intent(this, ListaPacientes.class);

        startActivity(i);
    }

}