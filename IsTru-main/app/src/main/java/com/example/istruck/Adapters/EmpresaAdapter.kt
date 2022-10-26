package com.example.istruck.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.istruck.R
import java.util.HashMap

class EmpresaAdapter(private val context: Context,
                     private val dataSource: ArrayList<Any>,
                     private val cargaDataSource: ArrayList<Any>, private val numeroDataSource: ArrayList<Any>) : BaseAdapter() {


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

        val rowView = inflater.inflate(R.layout.list_empresa_carga, parent, false)
        val text_nome_Emp = rowView.findViewById<TextView>(R.id.text_nome_Emp)
//        val text_numero_Emp = rowView.findViewById<TextView>(R.id.text_numero_emp)
        val list_lista_carga = rowView.findViewById<ListView>(R.id.list_lista_carga)
        val selecaoCargaAdapter: SelecaoCargaAdapter

        text_nome_Emp.text = dataSource[position].toString()
//        text_numero_Emp.text = numeroDataSource[position].toString()
        selecaoCargaAdapter = SelecaoCargaAdapter(context, cargaDataSource[position] as ArrayList<Any>, numeroDataSource[position].toString())

        list_lista_carga.adapter = selecaoCargaAdapter

        return rowView
    }

}