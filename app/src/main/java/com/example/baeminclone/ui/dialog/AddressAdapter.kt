package com.example.baeminclone.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baeminclone.databinding.CellAddressBinding

class AddressAdapter : ListAdapter<String, AddressAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(val binding : CellAddressBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(){
            binding.txtAddress.text = currentList[adapterPosition]
            binding.viewLine.isVisible = currentList.size > adapterPosition
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(CellAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }



    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        }
    }

}