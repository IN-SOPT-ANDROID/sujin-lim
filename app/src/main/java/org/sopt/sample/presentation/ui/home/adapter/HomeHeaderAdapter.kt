package org.sopt.sample.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.LayoutHeaderBinding

class HomeHeaderAdapter() : RecyclerView.Adapter<HomeHeaderAdapter.ViewHolder>() {
    private lateinit var inflater: LayoutInflater
    private var headerTitle = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        val binding: LayoutHeaderBinding = LayoutHeaderBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(title = headerTitle)
    }

    override fun getItemCount(): Int = 1

    fun updateTitle(title: String) {
        headerTitle = title
    }

    inner class ViewHolder(private val binding: LayoutHeaderBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.headerTitle = title
        }
    }
}