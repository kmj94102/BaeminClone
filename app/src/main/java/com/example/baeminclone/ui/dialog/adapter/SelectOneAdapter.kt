package com.example.baeminclone.ui.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baeminclone.databinding.CellSelectOneBinding
import com.example.baeminclone.ui.dialog.data.SelectOne

class SelectOneAdapter(
    private val itemClickListener : (Int) -> Unit,
) : ListAdapter<SelectOne, SelectOneAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(val binding : CellSelectOneBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            binding.item = currentList[adapterPosition]

            root.setOnClickListener {
                itemClickListener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(CellSelectOneBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SelectOne>() {
            override fun areItemsTheSame(
                oldItem: SelectOne,
                newItem: SelectOne
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: SelectOne,
                newItem: SelectOne
            ): Boolean = oldItem.text == newItem.text

        }
    }
}