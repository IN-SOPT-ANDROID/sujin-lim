package org.sopt.sample.presentation.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.domain.model.home.Repo
import org.sopt.sample.presentation.common.base.BindingFragment


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homeRepoAdapter: HomeRepoAdapter
    private var args: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // args = requireArguments().getString("key")
        initAdapter()
    }

    private fun initAdapter() {
        val repoList = listOf(
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "first",
                desc = "first desc"
            ),
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "second",
                desc = "second desc"
            ),
            Repo(
                imageId = R.drawable.ic_logo_github,
                title = "third",
                desc = "third desc"
            ),
        )
        homeRepoAdapter = HomeRepoAdapter()
        binding.rvHomeRepo.adapter = homeRepoAdapter
        homeRepoAdapter.updateData(newList = repoList)
    }

    companion object {

        @JvmStatic
        fun newInstance(args: String) = HomeFragment().apply {
            arguments = bundleOf("key" to args)
        }
    }

}