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


class HomeFragment : Fragment() {
    private lateinit var homeRepoAdapter: HomeRepoAdapter
    private var args: String? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // args = requireArguments().getString("key")
        initAdapter()
    }

    private fun initAdapter() {
        val repoList = listOf(
            Repo(
                imageId = R.drawable.ic_launcher_foreground,
                title = "first",
                desc = "first desc"
            ),
            Repo(
                imageId = R.drawable.ic_launcher_foreground,
                title = "second",
                desc = "second desc"
            ),
            Repo(
                imageId = R.drawable.ic_launcher_foreground,
                title = "third",
                desc = "third desc"
            ),
        )
        homeRepoAdapter = HomeRepoAdapter()
        binding.rvHomeRepo.adapter = homeRepoAdapter
        homeRepoAdapter.updateData(newList = repoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        @JvmStatic
        fun newInstance(args: String) = HomeFragment().apply {
            arguments = bundleOf("key" to args)
        }
    }

}