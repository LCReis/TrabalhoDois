package com.example.cadastro1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaInicial extends AppCompatActivity {

    ListView listaCadastro;
    ArrayList<Cadastro> listaCad;
    ArrayAdapter<Cadastro> adpCad;
    ListView listaEndereco;
    ArrayList<CadastroEnd> listaEnd;
    ArrayAdapter<CadastroEnd> adpEnd;
    Cadastro cadastro;
    CadastroDAO caDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        listaCadastro = findViewById(R.id.ListViewCad);
        listaEndereco = findViewById(R.id.ListViewEnd);
        registerForContextMenu(listaCadastro);

        loadingListUsers();
        loadingListAdress();

        listaCadastro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cadastro regInsert = (Cadastro) adpCad.getItem(position);

                Intent i = new Intent(TelaInicial.this, CadastroTela.class);
                i.putExtra("Registro Inserido", regInsert);
                startActivity(i);
            }
        });

        listaCadastro.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cadastro = adpCad.getItem(position);
                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutelainicial, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menuCadastrarEnd) {
            Intent i = new Intent(this, CadastroEndTela.class);
            startActivity(i);
        } else if(item.getItemId() == R.id.menuFecharApp) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    private void loadingListUsers() {

        listaCad = CadastroDAO.getInstance(this).listar();


        if (listaCadastro != null){
            adpCad = new ArrayAdapter<Cadastro>(TelaInicial.this, android.R.layout.simple_list_item_1, listaCad);

            listaCadastro.setAdapter(adpCad);
        }

    }

    private void loadingListAdress() {

        listaEnd = CadastroDAO.getInstance(this).listarEnd();

        if (listaEndereco != null){
            adpEnd = new ArrayAdapter<CadastroEnd>(TelaInicial.this, android.R.layout.simple_list_item_1, listaEnd);

            listaEndereco.setAdapter(adpEnd);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadingListUsers();
        loadingListAdress();
    }

    public void excluir(){
        long retornoBD;
        caDAO = new CadastroDAO();
        retornoBD = CadastroDAO.getInstance(this).excluir(cadastro);

        if (retornoBD != -1) {
            alert("Registro excluido com sucesso");
        } else {
            alert("Erro ao cadastrar");
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem delete = menu.add("Deletar Registro");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                excluir();
                loadingListUsers();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    private void alert(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}
