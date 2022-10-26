package com.example.istruck;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TelaPrincipalEmp extends AppCompatActivity {

    private TextView nomeEmpresa, emailEmpresa, cnpjEmpresa;
    private Button bt_deslogar, bt_continuar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;
    private String tipoCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal_emp);

        getSupportActionBar().hide();
        IniciarComponente();


        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaPrincipalEmp.this, FormLogin.class);
                startActivity(intent);
                finish();
            }
        });

        bt_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tipoCliente.equals("Empresa")) {
                    Intent intent = new Intent(TelaPrincipalEmp.this, TelaEmpresaCadastroCarga.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(TelaPrincipalEmp.this, TelaCaminhoneiroCarga.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Consulta Informações Empresa
        DocumentReference documentReferenceEmp = db.collection("Empresa").document(usuarioID);
        documentReferenceEmp.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if(documentSnapshot != null && documentSnapshot.getString("nome") != null){
                    nomeEmpresa.setText(documentSnapshot.getString("nome"));
                    emailEmpresa.setText(email);
                    cnpjEmpresa.setText(documentSnapshot.getString("cnpj"));
                    tipoCliente = "Empresa";
                }

            }
        });

        //Consulta Informações Caminhoneiro
        DocumentReference documentReferenceCam = db.collection("Caminhoneiro").document(usuarioID);
        documentReferenceCam.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if(documentSnapshot != null && documentSnapshot.getString("nome") != null){
                    nomeEmpresa.setText(documentSnapshot.getString("nome"));
                    emailEmpresa.setText(email);
                    cnpjEmpresa.setText(documentSnapshot.getString("cpf"));
                    tipoCliente = "Caminhoneiro";
                }

            }
        });
    }

    private void IniciarComponente(){
        nomeEmpresa = findViewById(R.id.textNomeEmpresa);
        emailEmpresa = findViewById(R.id.textEmailEmpresa);
        cnpjEmpresa = findViewById(R.id.textCnpjEmpresa);
        bt_deslogar = findViewById(R.id.bt_deslogar);
        bt_continuar = findViewById(R.id.bt_Continuar);
    }
}