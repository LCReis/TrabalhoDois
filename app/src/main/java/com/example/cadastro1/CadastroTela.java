package com.example.cadastro1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class CadastroTela extends AppCompatActivity implements View.OnClickListener {

    Cadastro cadastro, alterCadastro;
    long retorno;
    EditText nome;
    EditText cpf;
    EditText ddd;
    EditText telefone;
    EditText email;
    EditText senha;
    Button salvar;
    Spinner enderecos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Intent i = getIntent();
        alterCadastro = (Cadastro) i.getSerializableExtra("Registro Inserido");
        cadastro = new Cadastro();

        nome = findViewById(R.id.edtNomeComp);
        cpf = findViewById(R.id.edtCPF);
        ddd = findViewById(R.id.edtDDD);
        telefone = findViewById(R.id.edtTel);
        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha1);
        salvar = findViewById(R.id.btnSalvar);

        enderecos = findViewById(R.id.spnEnd);
        spinner();


        if(alterCadastro != null){
			salvar.setText("Alterar");
            nome.setText(alterCadastro.getNome());
            cpf.setText(alterCadastro.getCpf());
            ddd.setText(alterCadastro.getDdd()+ " ");
            telefone.setText(alterCadastro.getTelefone()+ " ");
            email.setText(alterCadastro.getEmail());
            senha.setText(alterCadastro.getSenha());
            

        }else{
            salvar.setText("Salvar");
        }
        salvar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulogin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menufechar: {
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        cadastro.setCpf(cpf.getText().toString().trim());
        cadastro.setNome(nome.getText().toString().trim());
        cadastro.setDdd(Integer.parseInt(ddd.getText().toString().trim()));
        cadastro.setTelefone(Integer.parseInt(telefone.getText().toString().trim()));
        cadastro.setEmail(email.getText().toString().trim());
        cadastro.setSenha(senha.getText().toString().trim());

        if(salvar.getText().toString().equals("Salvar")) {
            retorno = CadastroDAO.getInstance(this).salvar(cadastro);

            if (retorno != -1) {
                alert("Cadastro realizado com sucesso");
            } else {
                alert("Erro ao cadastrar");
            }
            finish();
            Intent i = new Intent(this, TelaInicial.class);
            startActivity(i);

        }else{
            retorno = CadastroDAO.getInstance(this).alterar(cadastro);

            if (retorno != -1) {
                alert("Cadastro alterado com sucesso");
            } else {
                alert("Erro ao cadastrar");
            }
            finish();
        }
    }
    private void alert (String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void spinner(){

        ArrayList<CadastroEnd> end = CadastroDAO.getInstance(this).spnEnd();
        List<String> str = new ArrayList<String>();
        for (CadastroEnd c : end) {
            str.add(c.getLogradouro());
        }

        ArrayAdapter adpt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);
        enderecos.setAdapter(adpt);

    }
}
