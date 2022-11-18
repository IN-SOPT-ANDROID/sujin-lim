package org.sopt.sample.presentation.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.ConcatAdapter
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.domain.model.home.RepoInfo
import org.sopt.sample.presentation.common.base.BindingFragment
import org.sopt.sample.presentation.ui.main.home.adapter.HomeHeaderAdapter
import org.sopt.sample.presentation.ui.main.home.adapter.HomeRepoAdapter
import org.sopt.sample.presentation.ui.main.home.adapter.decorator.HomeRepoAdapterDecorator


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homeRepoAdapter: HomeRepoAdapter
    private lateinit var homeHeaderAdapter: HomeHeaderAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        homeRepoAdapter = HomeRepoAdapter(:: selectRepo)
        homeRepoAdapter.submitList(repoList)

        homeHeaderAdapter = HomeHeaderAdapter()
        homeHeaderAdapter.updateTitle(title = getString(R.string.home_header_title))

        binding.rvHomeRepo.apply {
            adapter = ConcatAdapter(homeHeaderAdapter, homeRepoAdapter)
            addItemDecoration(HomeRepoAdapterDecorator())
        }
    }

    private fun selectRepo(repo: RepoInfo) {
        // TODO : select event 처리
    }

    fun scrollToTop() {
        binding.rvHomeRepo.smoothScrollToPosition(0)
    }

    companion object {

        private val repoList = listOf(
            RepoInfo(
                imageId = R.drawable.ic_logo_github,
                title = "1 title",
                desc = "1 desc"
            ),
            RepoInfo(
                imageId = R.drawable.ic_logo_github,
                title = "2 title",
                desc = "2 desc"
            ),
            RepoInfo(
                imageId = R.drawable.ic_logo_github,
                title = "3 title",
                desc = "3 desc"
            ),
            RepoInfo(
                imageId = R.drawable.ic_logo_github,
                title = "4 title",
                desc = "4 desc"
            ),
            RepoInfo(
                imageId = R.drawable.ic_logo_github,
                title = "5 title",
                desc = "5 desc"
            ),
            RepoInfo(
                imageId = R.drawable.ic_logo_github,
                title = "6 title",
                desc = "6 desc"
            ),
            RepoInfo(
                imageId = R.drawable.ic_logo_github,
                title = "7 title",
                desc = "7 desc"
            ),
            RepoInfo(
                imageId = R.drawable.ic_logo_github,
                title = "8 title",
                desc = "8 desc"
            ),
            RepoInfo(
                imageId = R.drawable.ic_logo_github,
                title = "9 title",
                desc = "9 desc"
            ),
            RepoInfo(
                imageId = R.drawable.ic_logo_github,
                title = "10 title",
                desc = "10 desc"
            ),
        )

        @JvmStatic
        fun newInstance(args: String) = HomeFragment().apply {
            arguments = bundleOf("key" to args)
        }
    }

}