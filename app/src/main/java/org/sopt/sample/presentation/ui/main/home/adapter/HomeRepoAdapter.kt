package org.sopt.sample.presentation.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemHomeRepoBinding
import org.sopt.sample.domain.model.home.RepoInfo

class HomeRepoAdapter(
    private val onItemClick: (RepoInfo) -> Unit
) : ListAdapter<RepoInfo, HomeRepoAdapter.ViewHolder>(diffCallback) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomeRepoBinding = ItemHomeRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(repo = it)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder(private val binding: ItemHomeRepoBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: RepoInfo) {
            binding.apply {
                repoItem = repo
                clItemNewsLayout.setOnClickListener {
                    onItemClick.invoke(repo)
                }
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RepoInfo>(){
            override fun areItemsTheSame(oldItem: RepoInfo, newItem: RepoInfo): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: RepoInfo, newItem: RepoInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}