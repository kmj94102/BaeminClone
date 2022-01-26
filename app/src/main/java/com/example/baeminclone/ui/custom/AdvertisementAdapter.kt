package com.example.baeminclone.ui.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baeminclone.databinding.CellAdvertisementBinding

class AdvertisementAdapter : ListAdapter<String, AdvertisementAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(val binding : CellAdvertisementBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            Glide
                .with(binding.imageView.context)
                .load(currentList[position])
                .centerCrop()
                .into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(CellAdvertisementBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        }
    }

}