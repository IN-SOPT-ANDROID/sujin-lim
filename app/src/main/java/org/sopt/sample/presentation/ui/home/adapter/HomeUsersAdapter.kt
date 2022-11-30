package org.sopt.sample.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemHomeUserBinding
import org.sopt.sample.presentation.model.home.User

class HomeUsersAdapter(
    private val onItemClick: (User) -> Unit
) : ListAdapter<User, HomeUsersAdapter.ViewHolder>(diffCallback) {
    private lateinit var inflater: LayoutInflater

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        val binding: ItemHomeUserBinding = ItemHomeUserBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(user = getItem(position))
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder(private val binding: ItemHomeUserBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.apply {
                userItem = user
                clItemUserLayout.setOnClickListener {
                    onItemClick.invoke(user)
                }
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}