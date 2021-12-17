package com.dwisuseno.githubusernavigationdanapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dwisuseno.githubusernavigationdanapi.databinding.ItemFollowerBinding
import com.squareup.picasso.Picasso

class ListFollowerAdapter(private val listFollower: ArrayList<Follower>) : RecyclerView.Adapter<ListFollowerAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemFollowerBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo,user_name) = listFollower[position]
        Picasso.get().load(photo.toString()).into(holder.binding.avatars2)
        holder.binding.tvItmUsername.text = user_name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFollower[holder.adapterPosition])
        }


    }
    override fun getItemCount() = listFollower.size

    class ListViewHolder(var binding: ItemFollowerBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Follower)
    }
}