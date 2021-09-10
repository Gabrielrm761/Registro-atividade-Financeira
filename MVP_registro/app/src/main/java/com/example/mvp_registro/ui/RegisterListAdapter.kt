package com.example.mvp_registro.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvp_registro.R
import com.example.mvp_registro.databinding.ItemRegisterBinding
import com.example.mvp_registro.model.Register

class RegisterListAdapter : ListAdapter<Register, RegisterListAdapter.RegisterViewHolder>(DiffCallback()) {

    var listenerEdit : (Register) -> Unit = {}
    var listenerDelete : (Register) -> Unit = {}

    inner class RegisterViewHolder(private val binding: ItemRegisterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Register) {
            binding.tvTitle.text = item.description
            binding.tvDate.text = item.date
            binding.tvValue.text = "R$ ${item.value}"
            binding.ivMore.setOnClickListener {
                showPopup(item)
            }
        }

        private fun showPopup(item: Register) {
            val ivMore = binding.ivMore
            val popupMenu = PopupMenu(ivMore.context, ivMore)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener{
                when (it.itemId) {
                    R.id.action_edit -> listenerEdit(item)
                    R.id.action_delete -> listenerDelete(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRegisterBinding.inflate(inflater, parent, false)
        return RegisterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RegisterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallback : DiffUtil.ItemCallback<Register>() {
    override fun areItemsTheSame(oldItem: Register, newItem: Register) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Register, newItem: Register) = oldItem.id == newItem.id
}