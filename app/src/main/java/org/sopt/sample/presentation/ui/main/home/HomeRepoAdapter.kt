package org.sopt.sample.presentation.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.databinding.ItemHomeRepoBinding
import org.sopt.sample.domain.model.home.Repo

class HomeRepoAdapter() : RecyclerView.Adapter<HomeRepoAdapter.ViewHolder>() {
    private var repoList = listOf<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHomeRepoBinding = ItemHomeRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    fun updateData(newList: List<Repo>) {
        repoList = newList.toList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemHomeRepoBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repo) {
            binding.repoItem = repo
        }
    }


    companion object {

    }
}