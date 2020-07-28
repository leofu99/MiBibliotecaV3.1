package com.example.sesionroom.model.local

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.mibibliotecav2.R
import com.example.mibibliotecav2.model.remote.LibrosRemote
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_grid_movie.view.*


class LibrosRVAdapter(
    var librosList: ArrayList<LibrosRemote>,
    val onItemClickListener: OnItemClickListener?


): RecyclerView.Adapter<LibrosRVAdapter.LibrosViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LibrosViewHolder {

        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_grid_movie, parent,false)
        return LibrosViewHolder(itemView, onItemClickListener)

    }

    override fun getItemCount(): Int = librosList.size

    override fun onBindViewHolder(
        holder: LibrosViewHolder,
        position: Int)
    {
        val libro : LibrosRemote = librosList[position]
        holder.bindDeudor(libro)
        holder.buttonViewOption.setOnClickListener{



        }


    }
   inner class LibrosViewHolder(
        itemView:View,
        var onItemClickListener: OnItemClickListener?
    ):RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        var buttonViewOption= itemView.findViewById<View>(R.id.textViewOptions) as TextView

        private lateinit var libro: LibrosRemote

        fun bindDeudor(libro: LibrosRemote){
            this.libro =libro
            itemView.tv_titulo.text = libro.titulo
            itemView.tv_autor.text = libro.autor
            itemView.tv_genero.text = libro.genero
            itemView.tv_pag.text =libro.npag
            itemView.setOnClickListener(this)


            if(!libro.portada.isNullOrEmpty())
                Picasso.get().load(libro.portada).into(itemView.iv_foto)

        }

        override fun onClick(v: View?) {
            onItemClickListener?.onItemClick(libro)
        }
    }


    interface OnItemClickListener {


        fun onItemClick(libro:LibrosRemote)
       
    }


}

