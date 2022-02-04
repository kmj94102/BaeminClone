package com.example.baeminclone.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baeminclone.databinding.CellAddressBinding

class AddressSearchAdapter(val itemClickListener : (String) -> Unit) : ListAdapter<String, AddressSearchAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(val binding : CellAddressBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            txtAddress.text = currentList[adapterPosition]
            viewLine.isVisible = currentList.size - 1 > adapterPosition

            root.setOnClickListener {
                itemClickListener(txtAddress.text.toString())
            }
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