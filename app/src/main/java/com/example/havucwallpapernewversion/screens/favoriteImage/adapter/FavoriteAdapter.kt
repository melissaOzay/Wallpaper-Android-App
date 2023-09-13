package com.example.havucwallpapernewversion.screens.favoriteImage.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.havucwallpapernewversion.R
import com.example.havucwallpapernewversion.features.images.domain.model.Image
import com.example.havucwallpapernewversion.screens.images.adapter.ImageDiffCallBack
import com.squareup.picasso.Picasso

class FavoriteAdapter :
    RecyclerView.Adapter<FavoriteAdapter.CompanyViewHolder>() {

    private var oldFavoriList = arrayListOf<Image>()

    fun setListData(items: List<Image>) {
        val diffUtil = FavoriteImageDiffCallBack(oldFavoriList,items)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldFavoriList= items as ArrayList<Image>
        diffResult.dispatchUpdatesTo(this)
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo = view.findViewById<AppCompatImageView>(R.id.iv_photo)

        fun bindItems(item: Image) {
            Picasso.get().load(item.imagePullPath).into(photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_favorite, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(oldFavoriList[position])

    }

    override fun getItemCount(): Int {
        return oldFavoriList.count()
    }


}