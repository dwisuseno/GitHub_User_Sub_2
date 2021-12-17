package com.dwisuseno.githubusernavigationdanapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwisuseno.githubusernavigationdanapi.databinding.ItemFollowingBinding
import com.squareup.picasso.Picasso

class ListFollowingAdapter(private val listFollowing: ArrayList<Following>) : RecyclerView.Adapter<ListFollowingAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemFollowingBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo,user_name) = listFollowing[position]
//        Picasso.get().load(photo.toString()).into(holder.binding.avatars3)
        Glide.with(holder.itemView.context)
            .load(photo) // URL Gambar
            .circleCrop() // Mengubah image menjadi lingkaran
            .into(holder.binding.avatars3) // imageView mana yang akan diterapkan
        holder.binding.tvItemUsername.text = user_name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFollowing[holder.adapterPosition])
        }


    }
    override fun getItemCount() = listFollowing.size

    class ListViewHolder(var binding: ItemFollowingBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Following)
    }
}