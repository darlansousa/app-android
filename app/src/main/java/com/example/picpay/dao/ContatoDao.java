package com.example.picpay.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.picpay.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDao {

    private static final List<Contato> contatos = new ArrayList<>();


    public List<Contato> findAll(){
        return new ArrayList<>(contatos);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void save(Contato contato) {
        if(contato.getId() == null || contato.getId().equals(0)){
            contato.setId(contatos.stream()
                    .mapToInt(Contato::getId)
                    .max().orElse(0) + 1);
            contatos.add(contato);
        }else{
            Contato contatoSalvo = findById(contato.getId());
            contatoSalvo.setNome(contato.getNome());
            contatoSalvo.setTelefone(contato.getTelefone());
            contatoSalvo.setEmail(contato.getEmail());
        }


    }

    public Contato findById(Integer id) {
        for(Contato contato : contatos){
            if(contato.getId().equals(id))
                return contato;
        }
        return null;
    }

    public void delete(Contato contato) {
       Contato contatoSalvo = findById(contato.getId());
       if(contatoSalvo != null)
           contatos.remove(contatoSalvo);
    }
}
