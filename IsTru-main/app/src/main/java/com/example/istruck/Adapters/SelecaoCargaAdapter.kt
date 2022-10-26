package com.example.istruck.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.istruck.R
import java.util.HashMap

class SelecaoCargaAdapter(private val context: Context,
                          private val dataSource: ArrayList<Any>, private val numero:String) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView = inflater.inflate(R.layout.list_selecao_item_carga, parent, false)
        val text_peso = rowView.findViewById<TextView>(R.id.text_Peso)
        val text_Material = rowView.findViewById<TextView>(R.id.text_material)
        val text_endOrigem = rowView.findViewById<TextView>(R.id.text_endOrigem)
        val text_endDestinatario = rowView.findViewById<TextView>(R.id.text_endDestinatario)
        val text_preco = rowView.findViewById<TextView>(R.id.text_preco)
        val text_numero = rowView.findViewById<TextView>(R.id.text_Selec_numero)

        text_peso.text = (dataSource[position] as HashMap<String, String>).get("peso").toString()
        text_Material.text = (dataSource[position] as HashMap<String, String>).get("material").toString()
        text_endOrigem.text = (dataSource[position] as HashMap<String, String>).get("endOrigem").toString()
        text_endDestinatario.text = (dataSource[position] as HashMap<String, String>).get("endDestinatario").toString()
        text_preco.text = (dataSource[position] as HashMap<String, String>).get("preco").toString()
        text_numero.text = (numero)
        return rowView
    }

}