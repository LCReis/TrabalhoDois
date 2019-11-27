package com.example.cadastro1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;


import java.lang.reflect.Array;
import java.util.ArrayList;


public class CadastroDAO {

    private static final String TABELA_U = "users";
    private static final String TABELA_A = "address";

    public SQLiteDatabase db;

    private static CadastroDAO instancia = new CadastroDAO();


    public static CadastroDAO getInstance(Context contexto) {
        if((instancia.db == null) || (!instancia.db.isOpen())) {
            instancia.db = new BD(contexto).getWritableDatabase();
        }
        return instancia;
    }


    public long excluir(Cadastro c) {

        long retornoDB;
        String[] args = new String[]{c.getCpf()};
        retornoDB = db.delete(TABELA_U, "cpf=?", args);
        return retornoDB;
    }

   public ArrayList<Cadastro> listar() {
        String[] coluns = {"cpf", "nome", "ddd", "telefone", "email", "senha"};
        Cursor c = db.query(TABELA_U, coluns, null, null, null, null, null, null);
        ArrayList<Cadastro> ListaCadastro = new ArrayList<Cadastro>();
        while (c.moveToNext()){
            Cadastro cad = new Cadastro();

            cad.setCpf(c.getString(0));
            cad.setNome(c.getString(1));
            cad.setDdd(c.getInt(2));
            cad.setTelefone(c.getInt(3));
            cad.setEmail(c.getString(4));
            cad.setSenha(c.getString(5));

            ListaCadastro.add(cad);

        }
        return ListaCadastro;
    }

    public Cadastro validarLogin(String email, String senha) {

        String[] selectionArgs = new String[]{email, senha};
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email=? AND senha=?", selectionArgs);
        Cadastro acesso = null;
        while (cursor.moveToNext()) {
            acesso = new Cadastro();
            acesso.setNome(cursor.getString(cursor.getColumnIndex("email")));
            acesso.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        }cursor.close();
        return acesso;
    }



    public long salvar(Cadastro cad) {
        ContentValues values = new ContentValues();
        long returnBD_U;

            values.put("cpf",cad.getCpf());
            values.put("nome", cad.getNome());
            values.put("ddd", cad.getDdd());
            values.put("telefone", cad.getTelefone());
            values.put("email", cad.getEmail());
            values.put("senha", cad.getSenha());

        returnBD_U = db.insert(TABELA_U, null, values);
        return returnBD_U;
    }

    public long alterar(Cadastro cad) {
        ContentValues values = new ContentValues();
        long returnBD_U;

        values.put("cpf",cad.getCpf());
        values.put("nome", cad.getNome());
        values.put("ddd", cad.getDdd());
        values.put("telefone", cad.getTelefone());
        values.put("email", cad.getEmail());
        values.put("senha", cad.getSenha());

        String[] args = new String[]{cad.getCpf()};
        returnBD_U = db.update(TABELA_U, values, "cpf=?", args);
        return returnBD_U;
    }

    public long salvarEnd(CadastroEnd cadEnd) {
        ContentValues values = new ContentValues();
        long returnBD_A;

        values.put("logradouro", cadEnd.getLogradouro());
        values.put("numero", cadEnd.getNumero());
        values.put("bairro", cadEnd.getBairro());
        values.put("cidade", cadEnd.getCidade());
        values.put("uf", cadEnd.getEstado());
        values.put("cep", cadEnd.getCep());


        returnBD_A = db.insert(TABELA_A, null, values);
        return returnBD_A;
    }

    public ArrayList<CadastroEnd> listarEnd() {
        String[] coluns = {"logradouro", "numero", "bairro", "cidade", "uf", "cep"};
        Cursor c = db.query(TABELA_A, coluns, null, null, null, null, null, null);
        ArrayList<CadastroEnd> listaCadastroEnd = new ArrayList<CadastroEnd>();
        while (c.moveToNext()){
            CadastroEnd cadEnd = new CadastroEnd();

            cadEnd.setLogradouro(c.getString(0));
            cadEnd.setNumero(Integer.parseInt(c.getString(1)));
            cadEnd.setBairro(c.getString(2));
            cadEnd.setCidade(c.getString(3));
            cadEnd.setEstado(c.getString(4));
            cadEnd.setCep(Integer.parseInt(c.getString(5)));

            listaCadastroEnd.add(cadEnd);
        }
        return listaCadastroEnd;
    }

    public ArrayList<CadastroEnd> spnEnd() {
        String[] coluns =  {"logradouro", "numero", "bairro", "cidade", "uf", "cep"};
        Cursor c = db.query(TABELA_A, coluns, null, null, null, null, null, null);
        ArrayList<CadastroEnd> listaCadastroEnd = new ArrayList<CadastroEnd>();
        while (c.moveToNext()){
            CadastroEnd cadEnd = new CadastroEnd();

            cadEnd.setLogradouro(c.getString(0));
            cadEnd.setNumero(Integer.parseInt(c.getString(1)));
            cadEnd.setBairro(c.getString(2));
            cadEnd.setCidade(c.getString(3));
            cadEnd.setEstado(c.getString(4));
            cadEnd.setCep(Integer.parseInt(c.getString(5)));

            listaCadastroEnd.add(cadEnd);
        }
        return listaCadastroEnd;
    }

    public ArrayList<CadastroEnd> spnUF() {
        String[] coluns = { "uf"};
        Cursor c = db.query(TABELA_A, coluns, null, null, null, null, null, null);
        ArrayList<CadastroEnd> listaCadastroEnd = new ArrayList<CadastroEnd>();
        while (c.moveToNext()){
            CadastroEnd cadEnd = new CadastroEnd();

            cadEnd.setEstado(c.getString(0));
            listaCadastroEnd.add(cadEnd);

        }
        return listaCadastroEnd;
    }

}
