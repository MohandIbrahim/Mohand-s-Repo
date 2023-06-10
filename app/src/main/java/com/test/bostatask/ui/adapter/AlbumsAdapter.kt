package com.test.bostatask.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.bostatask.data.models.AlbumsModel
import com.test.bostatask.databinding.ItemLayoutBinding

class AlbumsAdapter(private var albumsList: ArrayList<AlbumsModel.AlbumsModelItem>,
                    private val itemClickListener: (albumId: Long) -> Unit) : RecyclerView.Adapter<AlbumsAdapter.ViewHolder>()
{

    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.listItem.text = albumsList[position].title
        holder.binding.listItem.setOnClickListener {
            albumsList[position].id.let { albumId ->
                itemClickListener.invoke(albumId)
            }
        }
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

}