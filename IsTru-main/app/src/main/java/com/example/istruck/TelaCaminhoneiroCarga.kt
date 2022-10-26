package com.example.istruck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.istruck.Adapters.CargaAdapter
import com.example.istruck.Adapters.EmpresaAdapter
import com.example.istruck.Adapters.SelecaoCargaAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import java.util.HashMap

class TelaCaminhoneiroCarga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_caminhoneiro_carga)
        supportActionBar!!.hide()

        var listview_Cargas = findViewById<ListView>(R.id.List_Cargas)

        CarregarCargas(listview_Cargas)
    }
    fun CarregarCargas(listview_Cargas: ListView){
        val db = FirebaseFirestore.getInstance()
        val collectionReference: CollectionReference = db.collection("Empresa")
        var empresaAdapter: EmpresaAdapter
        var listaNomeEmpresa: ArrayList<Any> = ArrayList<Any>()
        var listaNumeroEmpresa: ArrayList<Any> = ArrayList<Any>()
        var listaCargas: ArrayList<Any> = ArrayList<Any>()

        collectionReference.addSnapshotListener { collectionSnapshot, error ->
            if (collectionSnapshot != null) {

                for(empresa in collectionSnapshot.documents){
                    listaNomeEmpresa.add((empresa as DocumentSnapshot).get("nome").toString())
                    listaNumeroEmpresa.add((empresa as DocumentSnapshot).get("numero").toString())
                    listaCargas.add((empresa as DocumentSnapshot).get("cargas") as ArrayList<Any>)
                }
                empresaAdapter = EmpresaAdapter(this, listaNomeEmpresa, listaCargas, listaNumeroEmpresa)
                listview_Cargas.adapter = empresaAdapter

            }
        }
    }
}