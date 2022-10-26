package com.example.istruck

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.istruck.Adapters.CargaAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class TelaEmpresaCadastroCarga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_empresa_cadastro_carga)
        supportActionBar!!.hide()

        var edit_Peso = findViewById<EditText>(R.id.edit_Peso)
        var edit_Material = findViewById<EditText>(R.id.edit_Material)
        var edit_EndOrigem = findViewById<EditText>(R.id.edit_Origem)
        var edit_EndDestinatario = findViewById<EditText>(R.id.edit_Destino)
        var edit_Preco = findViewById<EditText>(R.id.edit_Preco)
        var btn_Limpar = findViewById<Button>(R.id.bt_Limpar)
        var btn_CadastrarCarga = findViewById<Button>(R.id.bt_Cadastrar_Carga)
        var listview_Cargas = findViewById<ListView>(R.id.List_Cargas)



        btn_Limpar.setOnClickListener(View.OnClickListener {
            edit_Peso.text.clear()
            edit_Material.text.clear()
            edit_EndOrigem.text.clear()
            edit_EndDestinatario.text.clear()
            edit_Preco.text.clear()
        })

        btn_CadastrarCarga.setOnClickListener(View.OnClickListener {

            val db = FirebaseFirestore.getInstance()
            val usuarioID = FirebaseAuth.getInstance().currentUser?.uid


            val carga: HashMap<String, String> = HashMap()
            carga["peso"] = edit_Peso.text.toString()
            carga["material"] = edit_Material.text.toString()
            carga["endOrigem"] = edit_EndOrigem.text.toString()
            carga["endDestinatario"] = edit_EndDestinatario.text.toString()
            carga["preco"] = edit_Preco.text.toString()


            db.collection("Empresa").document(usuarioID.toString())
                .update("cargas", FieldValue.arrayUnion(carga))

                .addOnSuccessListener {
                    Toast.makeText(this,"Carga Cadastrada com Sucesso.", Toast.LENGTH_SHORT).show()
                    CarregarCargas(listview_Cargas)
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Ocorreu um Erro, Tente Novamente.", Toast.LENGTH_SHORT).show()
                }
        })
    }
    fun CarregarCargas(listview_Cargas: ListView){
        val db = FirebaseFirestore.getInstance()
        val usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
        val documentReference: DocumentReference = db.collection("Empresa").document(usuarioID)
        val listaNomeMaterial: ArrayList<String> = arrayListOf()
        var arrayAdapter:ArrayAdapter<String>
        var cargaAdapter: CargaAdapter
        var numeroEmp = ""

        documentReference.addSnapshotListener { documentSnapshot, error ->
            if (documentSnapshot != null) {

                val listaCargas = documentSnapshot.get("cargas") as ArrayList<Any>
                numeroEmp = documentSnapshot.getString("numero").toString()
                for(carga in listaCargas) {
                    listaNomeMaterial.add((carga as HashMap<String, String>).get("material").toString())
                }
                arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaNomeMaterial)
                cargaAdapter = CargaAdapter(this, listaCargas, numeroEmp)
                listview_Cargas.adapter = cargaAdapter

            }
        }
    }
}