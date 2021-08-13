package com.example.picpay.ui.activity;

import static com.example.picpay.ui.activity.ConstantsActivities.CHAVE_CONTATO;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.picpay.R;
import com.example.picpay.dao.ContatoDao;
import com.example.picpay.model.Contato;

public class FormularioContato extends AppCompatActivity {

    private final ContatoDao contatoDao = new ContatoDao();
    private Contato contato;

    private EditText nameText;
    private EditText phoneText;
    private EditText emailText;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarTela();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_contato_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.activity_formulario_contato_menu_salvar){
            salvarContato();
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializarTela(){
        setContentView(R.layout.activity_formulario_contato);
        instaciarComponentesFormulario();

        if(getIntent().hasExtra(CHAVE_CONTATO)){
            String TITLE_EDITAR_CONTATO = "Editar Contato";
            setTitle(TITLE_EDITAR_CONTATO);
            contato = (Contato) getIntent().getSerializableExtra(CHAVE_CONTATO);
            preencharFormularioComDados(contato);
        }else{
            String TITLE_NOVO_CONTATO = "Novo Contato";
            setTitle(TITLE_NOVO_CONTATO);
            contato = new Contato();
        }
    }

    private void instaciarComponentesFormulario() {
        nameText = findViewById(R.id.activity_formulario_contato_nome);
        phoneText = findViewById(R.id.activity_formulario_contato_telefone);
        emailText = findViewById(R.id.activity_formulario_contato_email);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void salvarContato(){
        atualizarContatoAPartirDoFormulario();
        contatoDao.save(contato);
        finish();
    }

    private void atualizarContatoAPartirDoFormulario(){
        final String name = nameText.getText().toString();
        final String phone = phoneText.getText().toString();
        final String email = emailText.getText().toString();
        contato.setNome(name);
        contato.setTelefone(phone);
        contato.setEmail(email);
    }

    private void preencharFormularioComDados(Contato contato){
        nameText.setText(contato.getNome());
        phoneText.setText(contato.getTelefone());
        emailText.setText(contato.getEmail());
    }
}