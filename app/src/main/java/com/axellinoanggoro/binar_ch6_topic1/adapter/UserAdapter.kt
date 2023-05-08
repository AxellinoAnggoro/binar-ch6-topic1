package com.axellinoanggoro.binar_ch6_topic1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_ch6_topic1.databinding.UserItemBinding
import com.axellinoanggoro.binar_ch6_topic1.model.GetUserResponseItem

class UserAdapter(var listUser : List<GetUserResponseItem>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(var binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.userName.text = listUser[position].name
        holder.binding.userUsername.text = listUser[position].username
        holder.binding.userAddress.text = listUser[position].address
//        holder.binding.userAge.text = listUser[position].age
        holder.binding.userPassword.text = listUser[position].password

    }
    override fun getItemCount(): Int {
        return listUser.size
    }
}