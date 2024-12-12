package com.example.desafioUM;

import org.springframework.data.annotation.Id;

public class Score {

    @Id
    private Integer id;
    private int vitorias,derrotas,empates;

    public Score(int vitorias, int derrotas, int empates) {
        super();
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public void setId(int i) {
    }
}
