package com.example.istruck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FormCadastro extends AppCompatActivity {

    private EditText edit_senha, edit_email, edit_nome;
    private Button bt_proximo;
    private Toast Toast_Nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();

        bt_proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit_nome.getText().toString().isEmpty() || edit_senha.getText().toString().isEmpty() || edit_email.getText().toString().isEmpty()) {
                    Toast toast = Toast_Nome.makeText(getApplicationContext(), "Preencha Todos os Campos!", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (edit_nome.getText().toString().isEmpty()) {
                    Toast toast = Toast_Nome.makeText(getApplicationContext(), "Digite um Nome!", Toast.LENGTH_SHORT);
                    toast.show();

                } else if (edit_senha.getText().toString().isEmpty() || edit_senha.length() < 6) {
                    Toast toast = Toast_Nome.makeText(getApplicationContext(), "Digite uma senha com 6 caracteres!", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (edit_email.getText().toString().isEmpty()) {
                    Toast toast = Toast_Nome.makeText(getApplicationContext(), "Digite um Email Valido!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Intent intent = new Intent(FormCadastro.this, TelaCdastrosCPF.class);
                    intent.putExtra("nome", edit_nome.getText().toString());
                    intent.putExtra("email", edit_email.getText().toString());
                    intent.putExtra("senha", edit_senha.getText().toString());

                    startActivity(intent);
                }

            }
        });
    }


    private void IniciarComponentes(){
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_proximo = findViewById(R.id.bt_prox);
    }
}


