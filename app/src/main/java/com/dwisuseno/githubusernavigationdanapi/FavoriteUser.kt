package com.dwisuseno.githubusernavigationdanapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwisuseno.githubusernavigationdanapi.databinding.ActivityFavoriteUserBinding
import com.google.android.material.snackbar.Snackbar
import db.FavHelper
import entity.Fav
import helper.MappingHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.FieldPosition

class FavoriteUser : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var adapter: FavAdapter

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result -> null }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_favorite_user)

        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = FavAdapter()
        adapter.notifyDataSetChanged()


        supportActionBar?.show()

        supportActionBar?.title = "Favorite User"

        binding.rvFav.layoutManager = LinearLayoutManager(this)
        binding.rvFav.setHasFixedSize(true)


        binding.rvFav.adapter = adapter

        if (savedInstanceState == null) {
            loadFavAsync()

        } else {
            val list = savedInstanceState.getParcelableArrayList<User>(EXTRA_STATE)
            if (list != null) {
                adapter.listFav = list
            }
        }

        adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val intent = Intent(this@FavoriteUser, DetailUser::class.java)
                intent.putExtra(DetailUser.EXTRA_PERSON, data)
                startActivity(intent)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listFav)
    }

    private fun loadFavAsync() {
        lifecycleScope.launch {
            binding.progressbar.visibility = View.VISIBLE
            val favHelper = FavHelper.getInstance(applicationContext)
            favHelper.open()

            val deferredFav = async(Dispatchers.IO) {
                val cursor = favHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            binding.progressbar.visibility = View.INVISIBLE
            val favs = deferredFav.await()
            adapter.notifyDataSetChanged()
            if (favs.size > 0) {
                adapter.listFav = favs
            } else {
                adapter.listFav = ArrayList()
                showSnackbarMessage("Tidak ada data saat ini")
            }
            favHelper.close()
        }
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding.rvFav, message, Snackbar.LENGTH_SHORT).show()
    }
}