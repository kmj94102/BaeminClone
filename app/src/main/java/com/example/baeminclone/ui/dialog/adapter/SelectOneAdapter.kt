package com.example.baeminclone.ui.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baeminclone.R
import com.example.baeminclone.databinding.CellSelectOneBinding
import com.example.baeminclone.ui.dialog.data.SelectOne
import com.example.baeminclone.util.setTextColorRes

class SelectOneAdapter(
    private val listener : (Int) -> Unit
) : ListAdapter<SelectOne, SelectOneAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding : CellSelectOneBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val item = currentList[adapterPosition]
            txtContent.text = item.text

            if (item.isSelect) {
                viewSelect.isVisible = true
                txtContent.setTextColorRes(R.color.select_brown)
            }

            root.setOnClickListener {
                listener(adapterPosition)
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