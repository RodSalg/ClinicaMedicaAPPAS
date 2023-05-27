package com.example.aplicativoteste.InterfaceAndroid.AreasMedicos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicativoteste.MedicosDados.Medico;
import com.example.aplicativoteste.MedicosDados.MedicoDAO;
import com.example.aplicativoteste.R;

public class AreaCadastroExcluirAtualizar extends AppCompatActivity {
    private EditText nome;
    private EditText especialidade;
    private EditText horarioEntrada;
    private EditText horarioSaida;
    private MedicoDAO dao;
    private Medico medico = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_cadastro_excluir_atualizar);
        nome = findViewById(R.id.etMedico);
        especialidade = findViewById(R.id.etEspecialista);
        horarioEntrada = findViewById(R.id.etHorarioEntrada);
        horarioSaida = findViewById(R.id.etHorarioSaida);

        dao = new MedicoDAO(this);

        Intent it = getIntent();

        if(it.hasExtra("medico"))
        {
            medico = (Medico) it.getSerializableExtra("medico");
            nome.setText(medico.getNome());
            especialidade.setText(medico.getEspecialidade());
            horarioEntrada.setText((medico.getHorarioEntrada()+""));
            horarioSaida.setText((medico.getHorarioSaida()+""));
        }

    }

    public void salvar(View view) {
        if(nome.getText().toString().isEmpty() || especialidade.getText().toString().isEmpty() || horarioEntrada.getText().toString().isEmpty() || horarioSaida.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            int entrada = Integer.parseInt(horarioEntrada.getText().toString());
            int saida = Integer.parseInt(horarioSaida.getText().toString());

            if (entrada < 8 || entrada >= 16) {
                Toast.makeText(this, "Horário de entrada inválido. Insira um horário maior ou igual a 8 e menor que 16.", Toast.LENGTH_LONG).show();
                return;
            }

            if (saida <= entrada || saida >= 17) {
                Toast.makeText(this, "Horário de saída inválido. Insira um horário maior que o horário de entrada e menor que 17.", Toast.LENGTH_LONG).show();
                return;
            }

            if (medico == null) {
                medico = new Medico();
                medico.setNome(nome.getText().toString());
                medico.setEspecialidade(especialidade.getText().toString());
                medico.setHorarioEntrada(entrada);
                medico.setHorarioSaida(saida);

                long id = dao.inserir(medico);

                Toast.makeText(this, "Medico inserido com ID: "+ id, Toast.LENGTH_LONG).show();
            } else {
                medico.setNome(nome.getText().toString());
                medico.setEspecialidade(especialidade.getText().toString());
                medico.setHorarioEntrada(entrada);
                medico.setHorarioSaida(saida);
                dao.atualizar(medico);
                Toast.makeText(this, "O CADASTRO DO MÉDICO FOI ATUALIZADO", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void excluirMedico(View view)
    {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o médico?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //medicosFiltrados.remove(medicoExcluir);
                        //medicos.remove(medicoExcluir);
                        dao.excluir(medico);
                        Toast.makeText(AreaCadastroExcluirAtualizar.this, "Médico excluído com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("Não", null)
                .create();
        dialog.show();
    }


    public void telaListaMedicos(View v)
    {
        Intent i = new Intent(this, com.example.aplicativoteste.InterfaceAndroid.AreasMedicos.ListaMedicos.class);
        startActivity(i);


    }






}









