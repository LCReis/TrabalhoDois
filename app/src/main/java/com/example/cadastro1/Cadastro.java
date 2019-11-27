package com.example.cadastro1;

import androidx.annotation.NonNull;
import java.io.Serializable;


public class Cadastro implements Serializable {

    String nome;
    String cpf;
    int ddd;
    int telefone;
    String email;
    String senha;

    public Cadastro() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @NonNull
    @Override
    public String toString() {
        String text = "Nome: " + nome + "\n" + "E-mail: " + email + "\n" + "Telefone: " + "(" + ddd + ")" + "" + telefone + "\n";
        return text ;
    }
}
