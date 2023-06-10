package com.test.bostatask.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.bostatask.common.extensions.loadImageFromUrl
import com.test.bostatask.data.models.PhotosModel
import com.test.bostatask.databinding.PhotoItemBinding

class PhotosAdapter (private var photosList: ArrayList<PhotosModel.PhotosModelItem>,
                     private val itemClickListener: (photoTitle:String, photoUrl:String) -> Unit) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    class ViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.photoItem.loadImageFromUrl(photosList[position].url)
        holder.binding.photoItem.setOnClickListener {
            photosList[position].title.let { photoTitle ->
                photosList[position].url.let { photoUrl ->
                    itemClickListener.invoke(photoTitle, photoUrl)
                }
            }
        }
    }
    override fun getItemCount(): Int {
        return photosList.size
    }

    fun filterList(filteredList: ArrayList<PhotosModel.PhotosModelItem>) {
        this.photosList = filteredList
        notifyDataSetChanged()
    }
}