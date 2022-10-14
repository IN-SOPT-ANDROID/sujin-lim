package org.sopt.sample.presentation.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ConcatAdapter
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.domain.model.home.Repo
import org.sopt.sample.presentation.common.base.BindingFragment
import timber.log.Timber


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homeRepoAdapter: HomeRepoAdapter
    private lateinit var homeHeaderAdapter: HomeHeaderAdapter
    private var args: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // args = requireArguments().getString("key")
        initView()
        initAdapter()
    }

    private fun initView() {

    }

    private fun initAdapter() {
        homeRepoAdapter = HomeRepoAdapter(onItemClick = { selectRepo(repo = it) })
        homeRepoAdapter.submitList(repoList)

        homeHeaderAdapter = HomeHeaderAdapter()
        homeHeaderAdapter.updateTitle(title = "홈")

        binding.rvHomeRepo.adapter = ConcatAdapter(homeHeaderAdapter, homeRepoAdapter)

    }

    private fun selectRepo(repo: Repo) {

    }

    companion object {

        private val repoList = listOf(
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "1 title",
                desc = "1 desc"
            ),
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "2 title",
                desc = "2 desc"
            ),
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "3 title",
                desc = "3 desc"
            ),
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "4 title",
                desc = "4 desc"
            ),
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "5 title",
                desc = "5 desc"
            ),
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "6 title",
                desc = "6 desc"
            ),
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "7 title",
                desc = "7 desc"
            ),
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "8 title",
                desc = "8 desc"
            ),
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "9 title",
                desc = "9 desc"
            ),
        )

        @JvmStatic
        fun newInstance(args: String) = HomeFragment().apply {
            arguments = bundleOf("key" to args)
        }
    }

}