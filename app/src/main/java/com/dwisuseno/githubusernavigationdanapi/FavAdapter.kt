package com.dwisuseno.githubusernavigationdanapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwisuseno.githubusernavigationdanapi.databinding.ItemFavBinding
import com.squareup.picasso.Picasso
import entity.Fav

class FavAdapter: RecyclerView.Adapter<FavAdapter.FavViewHolder>() {

    private lateinit var onItemClickCallback: ListUserAdapter.OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    var listFav = ArrayList<User>()
        set(listFav) {
            if (listFav.size > 0) {
                this.listFav.clear()
            }
            this.listFav.addAll(listFav)
            notifyDataSetChanged()
        }

    fun addItem(fav: User) {
        this.listFav.add(fav)
        notifyItemInserted(this.listFav.size - 1)
    }
    fun updateItem(position: Int, fav: User) {
        this.listFav[position] = fav
        notifyItemChanged(position, fav)
    }

    fun removeItem(position: Int) {
        this.listFav.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listFav.size)
    }

    fun setOnItemClickCallback(onItemClickCallback: ListUserAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fav, parent, false)
        return FavViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(listFav[position])
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listFav[position]) }
    }
    override fun getItemCount(): Int = this.listFav.size

    inner class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favUser: User) {
            val binding = ItemFavBinding.bind(itemView)
            with(itemView) {
                binding.tvItmUsername.text = favUser.username
                Glide.with(itemView.context)
                    .load(favUser.avatars.toString())
                    .into(binding.tvAvatar)
            }
        }
    }
}