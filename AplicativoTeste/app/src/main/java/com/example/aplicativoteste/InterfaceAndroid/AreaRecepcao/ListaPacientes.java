package com.example.aplicativoteste.InterfaceAndroid.AreaRecepcao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.aplicativoteste.InterfaceAndroid.AreasMedicos.AreaCadastroExcluirAtualizar;
import com.example.aplicativoteste.InterfaceAndroid.AreasMedicos.ListaMedicos;
import com.example.aplicativoteste.InterfaceAndroid.Menu;
import com.example.aplicativoteste.PacientesDados.Paciente;
import com.example.aplicativoteste.PacientesDados.PacienteDAO;
import com.example.aplicativoteste.R;

import java.util.ArrayList;
import java.util.List;

public class ListaPacientes extends AppCompatActivity {


    private ListView listView;
    private PacienteDAO dao;
    private List<Paciente> pacientes;
    private List<Paciente> pacientesFiltrados = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_pacientes); //corrigir


        listView = findViewById(R.id.listListaPacientes);
        dao = new PacienteDAO(this);
        pacientes =  dao.obterTodosPacientes();
        pacientesFiltrados.addAll(pacientes);
        ArrayAdapter<Paciente> adaptador = new ArrayAdapter<Paciente>(this, android.R.layout.simple_list_item_1, pacientesFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListaPacientes.this, RecepcaoAtualizarExcluir.class);
                intent.putExtra("paciente", pacientesFiltrados.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.exemplo_menu, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraPaciente(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void procuraPaciente(String nome)
    {
        pacientesFiltrados.clear();
        for(Paciente a: pacientes)
        {
            if(a.getNome().toLowerCase().contains(nome.toLowerCase()) )
            {
                pacientesFiltrados.add(a);
            }
        }
        listView.invalidateViews();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);

    }




    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Paciente pacienteExcluir = pacientesFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("ATENÇÃO").setMessage("Realmente Deseja Excluir o Médico?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        pacientesFiltrados.remove(pacienteExcluir);
                        pacientes.remove(pacienteExcluir);
                        dao.excluir(pacienteExcluir);
                        listView.invalidateViews();

                    }
                }).create();
        dialog.show();

    }

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Paciente pacienteAtualizar = pacientesFiltrados.get(menuInfo.position);

        Intent it = new Intent(this, RecepcaoAtualizarExcluir.class);
        it.putExtra("paciente", pacienteAtualizar);
        startActivity(it);

    }


    public void cadastrar(MenuItem item)
    {
        Intent it = new Intent(this, Recepcao.class);
        startActivity(it);

    }

    public void VoltarMenu(View v)
    {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();

        pacientes = dao.obterTodosPacientes();
        pacientesFiltrados.clear();
        pacientesFiltrados.addAll(pacientes);
        listView.invalidateViews();
    }
}