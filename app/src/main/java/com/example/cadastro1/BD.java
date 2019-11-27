package com.example.cadastro1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {

    private static final String BANCO = "Conexao.db";
    private static final int VERSION = 1;
    private static final String TABELA_U = "users";
    private static final String TABELA_A = "address";

    public BD(Context context) {

        super(context, BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCad =
                "CREATE  TABLE " + TABELA_U + "("+
                        " cpf  VARCHAR(24) not null primary key, " +
                        " nome VARCHAR(50),"+
                        " ddd INTEGER ," +
                        " telefone INTEGER ," +
                        " email VARCHAR(50)," +
                        " senha VARCHAR(50));";

        String sqlEnd =
                "CREATE  TABLE " + TABELA_A + "("+
                        " logradouro VARCHAR(50)," +
                        " numero INTEGER ," +
                        " cep INTEGER ," +
                        " bairro VARCHAR(50)," +
                        " cidade VARCHAR(50)," +
                        " uf VARCHAR(2));";

        db.execSQL(sqlCad);
        db.execSQL(sqlEnd);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlCad = "DROP TABLE IF EXISTS " + TABELA_U;
        String sqlEnd = "DROP TABLE IF EXISTS " + TABELA_A;

        db.execSQL(sqlCad);
        db.execSQL(sqlEnd);
            onCreate(db);

    }
}
