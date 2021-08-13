package com.example.picpay.ui.activity;

import static com.example.picpay.ui.activity.ConstantsActivities.CHAVE_CONTATO;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.picpay.R;
import com.example.picpay.model.Contato;
import com.example.picpay.ui.view.ContatoView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContatoActivity extends AppCompatActivity {

    private FloatingActionButton fabAdicionarContato;
    private ListView listViewContatos;
    private final ContatoView view = new ContatoView(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarTela();
        adicionarComportamentos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_contatos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.activity_contatos_menu_remover){
            view.exibirMensagemConfirmacao(item);
        }

        return super.onContextItemSelected(item);
    }

    private void inicializarTela(){
        String TITLE = "Contatos";
        setTitle(TITLE);
        setContentView(R.layout.activity_contatos);
        instaciarComponentes();
        listViewContatos.setAdapter(view.getAdapter());
        registerForContextMenu(listViewContatos);
    }

    private void adicionarComportamentos(){
        fabAdicionarContato.setOnClickListener(view -> startActivity(new Intent(this, FormularioContato.class)));

        listViewContatos.setOnItemClickListener((adapterView, view, position, id) -> {
            Contato contato = (Contato) adapterView.getItemAtPosition(position);
            Intent intent = new Intent(this, FormularioContato.class);
            intent.putExtra(CHAVE_CONTATO, contato);
            startActivity(intent);
        });

    }

    private void instaciarComponentes() {
        fabAdicionarContato = findViewById(R.id.activity_contatos_adicionar_contato);
        listViewContatos = findViewById(R.id.activity_contatos_listview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.preencherListaContatos();
    }



}
