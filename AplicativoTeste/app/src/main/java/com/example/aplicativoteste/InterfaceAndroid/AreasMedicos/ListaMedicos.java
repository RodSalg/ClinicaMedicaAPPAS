package com.example.aplicativoteste.InterfaceAndroid.AreasMedicos;

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

import com.example.aplicativoteste.InterfaceAndroid.Menu;
import com.example.aplicativoteste.MedicosDados.Medico;
import com.example.aplicativoteste.MedicosDados.MedicoDAO;
import com.example.aplicativoteste.R;

import java.util.ArrayList;
import java.util.List;

public class ListaMedicos extends AppCompatActivity {


    private ListView listView;
    private MedicoDAO dao;
    private List<Medico> medicos;
    private List<Medico> medicosFiltrados = new ArrayList<>();



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
                procuraMedico(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void procuraMedico(String nome)
    {
        medicosFiltrados.clear();
        for(Medico a: medicos)
        {
            if(a.getNome().toLowerCase().contains(nome.toLowerCase()) )
            {
                medicosFiltrados.add(a);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);

        listView = findViewById(R.id.listListaMedicos);
        dao = new MedicoDAO(this);
        medicos = dao.obterTodos();
        medicosFiltrados.addAll(medicos);


        ArrayAdapter<Medico> adaptador = new ArrayAdapter<Medico>(this, android.R.layout.simple_list_item_1, medicosFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListaMedicos.this, AreaCadastroExcluirAtualizar.class);
                intent.putExtra("medico", medicosFiltrados.get(i));
                startActivity(intent);
            }
        });
    }



    public void excluir(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Medico medicoExcluir = medicosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("ATENÇÃO").setMessage("Realmente Deseja Excluir o Médico?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        medicosFiltrados.remove(medicoExcluir);
                        medicos.remove(medicoExcluir);
                        dao.excluir(medicoExcluir);
                        listView.invalidateViews();

                    }
                }).create();
        dialog.show();

    }

    public void atualizar(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Medico medicoAtualizar = medicosFiltrados.get(menuInfo.position);

        Intent it = new Intent(this, AreaCadastroExcluirAtualizar.class);
        it.putExtra("medico", medicoAtualizar);
        startActivity(it);

    }


    public void TelaCadastrarMedico(View v)
    {
        Intent i = new Intent(this, AreaCadastroMedico.class);
        startActivity(i);
    }

    public void cadastrar(MenuItem item)
    {
        Intent it = new Intent(this, AreaCadastroMedico.class);
        startActivity(it);

    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();

        medicos = dao.obterTodos();
        medicosFiltrados.clear();
        medicosFiltrados.addAll(medicos);
        listView.invalidateViews();
    }

    public void telaMenu(View v)
    {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }
}