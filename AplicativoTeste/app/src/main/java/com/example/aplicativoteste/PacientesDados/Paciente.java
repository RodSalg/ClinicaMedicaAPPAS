package com.example.aplicativoteste.PacientesDados;

import java.io.Serializable;

public class Paciente implements Serializable {

    private int id;

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    private int idade;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(int horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    private String especialidade;
    private int horarioConsulta;
    private String sintomas;



    @Override
    public String toString()
    {
        return nome;
    }

}
