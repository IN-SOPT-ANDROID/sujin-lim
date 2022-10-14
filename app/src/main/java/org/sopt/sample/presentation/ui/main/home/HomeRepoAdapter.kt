package org.sopt.sample.presentation.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemHomeRepoBinding
import org.sopt.sample.domain.model.home.Repo

class HomeRepoAdapter(
    private val onItemClick: (Repo) -> Unit
) : ListAdapter<Repo, HomeRepoAdapter.ViewHolder>(diffCallback) {
    // private var repoList = listOf<Repo>()

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

//    fun updateData(newList: List<Repo>) {
//        repoList = newList.toList()
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(private val binding: ItemHomeRepoBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repo) {
            binding.apply {
                repoItem = repo
                clItemNewsLayout.setOnClickListener {
                    onItemClick.invoke(repo)
                }
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Repo>(){
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }
}