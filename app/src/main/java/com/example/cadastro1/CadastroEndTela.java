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

public class CadastroEndTela extends AppCompatActivity implements View.OnClickListener {

    CadastroEnd cadastroEnd, altCadastroEnd;
    long retorno;
    EditText logradouro;
    EditText num;
    EditText bairro;
    EditText cidade;
    Spinner uf;
    EditText cep;
    Button salvarEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastroend_tela);

        Intent i = getIntent();
        altCadastroEnd = (CadastroEnd) i.getSerializableExtra("Registro Enviado");
        cadastroEnd = new CadastroEnd();


        logradouro = findViewById(R.id.edtLog);
        num = findViewById(R.id.edtNum);
        bairro = findViewById(R.id.edtBairro);
        cidade = findViewById(R.id.edtCid);
        cep = findViewById(R.id.edtCep);

        uf = findViewById(R.id.spnUF);
        spinner();

        salvarEnd = findViewById(R.id.btnSalvarEnd);
        salvarEnd.setOnClickListener(this);
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

        cadastroEnd.setLogradouro(logradouro.getText().toString());
        cadastroEnd.setNumero(Integer.parseInt(num.getText().toString().trim()));
        cadastroEnd.setBairro(bairro.getText().toString());
        cadastroEnd.setCidade(cidade.getText().toString());
        cadastroEnd.setEstado(uf.getSelectedItem().toString());
        cadastroEnd.setCep(Integer.parseInt(cep.getText().toString().trim()));
        retorno = CadastroDAO.getInstance(this).salvarEnd(cadastroEnd);

        if (retorno != -1){
            alert("Endereço cadastrado");
        }else{
            alert("Erro ao realizar cadastro");
        }
        finish();
        Intent i = new Intent(this, TelaInicial.class);
        startActivity(i);

    }
    private void alert (String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public void spinner(){

        ArrayList<CadastroEnd> lista_uf = CadastroDAO.getInstance(this).spnUF();
        List<String> str = new ArrayList<String>();
        for (CadastroEnd c : lista_uf) {
            str.add(c.getEstado());
        }
        //ArrayAdapter adpt = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);
        //uf.setAdapter(adpt);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.lista_uf, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uf.setAdapter(adapter);

    }
}
