package com.example.baeminclone.ui.address.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baeminclone.database.entity.AddressEntity
import com.example.baeminclone.databinding.CellLocationBinding
import com.example.baeminclone.ui.address.getAddressTypeDrawable

class AddressRegisteredAdapter(private val selectListener : (Long)-> Unit) : ListAdapter<AddressEntity, AddressRegisteredAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(val binding : CellLocationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val entity = currentList[adapterPosition]
            imgStartCard.setBackgroundResource(getAddressTypeDrawable(entity.type))
            txtAlias.text = entity.alias
            txtAddress.text = entity.address
            viewSelect.isVisible = entity.status

            binding.root.setOnClickListener {
                selectListener(entity.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(CellLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AddressEntity>() {
            override fun areItemsTheSame(oldItem: AddressEntity, newItem: AddressEntity): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: AddressEntity,
                newItem: AddressEntity
            ): Boolean = oldItem.id == newItem.id

        }
    }
}
