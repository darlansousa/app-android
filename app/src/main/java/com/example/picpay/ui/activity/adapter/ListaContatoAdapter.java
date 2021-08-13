package com.example.picpay.ui.activity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.picpay.R;
import com.example.picpay.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ListaContatoAdapter extends BaseAdapter {

    private final List<Contato> contatos = new ArrayList<>();
    private final Context context;

    public ListaContatoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Contato getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View viewInflada = LayoutInflater
                .from(context)
                .inflate(R.layout.activity_contatos_contato, viewGroup, false);

        Contato item = getItem(position);

        TextView textNome = viewInflada.findViewById(R.id.activity_contatos_contato_nome);
        TextView textTelefone = viewInflada.findViewById(R.id.activity_contatos_contato_telefone);

        textNome.setText(item.getNome());
        textTelefone.setText(item.getTelefone());

        return viewInflada;
    }

    public void refreshFrom(List<Contato> contatos){
        clear();
        addAll(contatos);
        notifyDataSetChanged();
    }

    private void clear(){
        this.contatos.clear();
    }

    private void addAll(List<Contato> contatos){
        this.contatos.addAll(contatos);
    }

    public void remove(Contato contato) {
        this.contatos.remove(contato);
        notifyDataSetChanged();
    }
}
