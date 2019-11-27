package com.example.cadastro1;

import androidx.annotation.NonNull;

public class CadastroEnd {

    String logradouro;
    int numero;
    String bairro;
    String cidade;
    String estado;
    int cep;

    public CadastroEnd() {
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    @NonNull
    @Override
    public String toString() {
        String text = "Logradouro: " + logradouro + "\n" + "N°: " + numero + "\n" + "Bairro: " + bairro + "\n"
                + "Cidade: " + cidade + "\n" + "UF: " + estado + "\n" + "Cep: " + cep + "\n";
        return text ;
    }
}
