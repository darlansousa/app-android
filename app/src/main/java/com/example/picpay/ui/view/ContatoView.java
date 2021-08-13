package com.example.picpay.ui.view;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;

import androidx.appcompat.app.AlertDialog;

import com.example.picpay.dao.ContatoDao;
import com.example.picpay.model.Contato;
import com.example.picpay.ui.activity.adapter.ListaContatoAdapter;

public class ContatoView {

    private final ListaContatoAdapter adapter;
    private final ContatoDao dao;
    private final Context context;

    public ContatoView(Context context) {
        this.context = context;
        this.adapter = new ListaContatoAdapter(context);
        this.dao = new ContatoDao();
    }

    public void preencherListaContatos() {
        adapter.refreshFrom(dao.findAll());
    }

    public void remover(Contato contato){
        dao.delete(contato);
        adapter.remove(contato);
    }

    public ListaContatoAdapter getAdapter(){
        return adapter;
    }

    public void exibirMensagemConfirmacao(MenuItem item){
        new AlertDialog.Builder(context)
                .setTitle("Remvendo um contato")
                .setMessage("Deseja realmente remover este contato?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Contato contato = adapter.getItem(menuInfo.position);
                    remover(contato);
                }).show();
    }

}
