package com.example.aplicativoteste.InterfaceAndroid.AreaRecepcao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicativoteste.MedicosDados.Medico;
import com.example.aplicativoteste.MedicosDados.MedicoDAO;
import com.example.aplicativoteste.PacientesDados.Paciente;
import com.example.aplicativoteste.PacientesDados.PacienteDAO;
import com.example.aplicativoteste.R;

public class Recepcao extends AppCompatActivity {

    private EditText nome;
    private EditText especialidade;
    private  EditText idade;
    private EditText horarioConsulta;
    private EditText sintomas;
    private PacienteDAO dao;
    private Paciente paciente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepcao);

        nome = findViewById(R.id.etNomePaciente);
        idade = findViewById(R.id.etIdadePaciente);
        especialidade = findViewById(R.id.etEspecialidadePaciente);
        horarioConsulta = findViewById(R.id.etHorarioConsultaPaciente);
        sintomas = findViewById(R.id.etSintomasPaciente);

        dao = new PacienteDAO(this);

        Intent it = getIntent();

        if(it.hasExtra("paciente"))
        {
            paciente = (Paciente) it.getSerializableExtra("paciente");
            nome.setText(paciente.getNome());
            nome.setText(paciente.getIdade()+"");
            especialidade.setText(paciente.getEspecialidade());
            horarioConsulta.setText((paciente.getHorarioConsulta()+""));
            sintomas.setText(paciente.getSintomas());
        }
    }

    public void salvarPaciente(View view) {

        if(nome.getText().toString().isEmpty() || idade.getText().toString().isEmpty()|| especialidade.getText().toString().isEmpty() || horarioConsulta.getText().toString().isEmpty() || sintomas.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if(paciente == null)
            {

                String nomePaciente = nome.getText().toString();
                int idadeA = Integer.parseInt(idade.getText().toString());
                String especialidadePaciente = especialidade.getText().toString();
                int horarioConsultaPaciente = Integer.parseInt(horarioConsulta.getText().toString());
                String sintomasPaciente = sintomas.getText().toString();

                MedicoDAO medicoDAO = new MedicoDAO(this);
                Medico medico = medicoDAO.buscarPorEspecialidade(especialidadePaciente);

                if (medico != null && horarioConsultaPaciente >= medico.getHorarioEntrada() && horarioConsultaPaciente <= medico.getHorarioSaida()) {
                    if (paciente == null) {
                        paciente = new Paciente();
                    }

                    paciente.setNome(nomePaciente);
                    paciente.setEspecialidade(especialidadePaciente);
                    paciente.setIdade(idadeA);
                    paciente.setHorarioConsulta(horarioConsultaPaciente);
                    paciente.setSintomas(sintomasPaciente);

                    long id = dao.inserir(paciente);

                    Toast.makeText(this, "Paciente Cadastrado com ID: " + id, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Não há medicos ou horarios disponiveis no momento.", Toast.LENGTH_LONG).show();
                }

            }else{

                paciente.setNome(nome.getText().toString());
                paciente.setIdade(Integer.parseInt(idade.getText()+""));
                paciente.setEspecialidade(especialidade.getText().toString());
                paciente.setHorarioConsulta(Integer.parseInt(horarioConsulta.getText()+""));
                paciente.setSintomas(sintomas.getText().toString());


                dao.atualizar(paciente);
                Toast.makeText(this, "O CADASTRO DO PACIENTE FOI ATUALIZADO", Toast.LENGTH_LONG).show();
            }
        }

    }






    public void TelaListaPacientes(View v)
    {

        Intent i = new Intent(this, ListaPacientes.class);
        startActivity(i);
    }



}