package com.test.bostatask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.bostatask.data.models.UsersModel
import com.test.task.databinding.ItemLayoutBinding

class UsersAdapter(private var usersList : ArrayList<UsersModel.UsersModelItem>,
                   private val itemClickListener : (userID:Long,userName:String,userAddress:String)->Unit) : RecyclerView.Adapter<UsersAdapter.ViewHolder>(){

    class ViewHolder( val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val address = usersList[position].address.city + ", " +
                usersList[position].address.suite + ", " +
                usersList[position].address.street + "\n" +
                usersList[position].address.zipcode
        holder.binding.listItem.text = usersList[position].name
        holder.binding.listItem.setOnClickListener{
            usersList[position].id.let {  userId ->
                usersList[position].name.let { userName ->
                    address.let { userAddress ->
                        itemClickListener.invoke(userId,userName,userAddress)
                    }
                }
            }

        }

    }
    override fun getItemCount(): Int {
        return usersList.size
    }

}