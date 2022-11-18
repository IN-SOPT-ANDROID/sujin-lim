package org.sopt.sample.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.ConcatAdapter
import org.sopt.sample.R
import org.sopt.sample.data.remote.api.ApiPool
import org.sopt.sample.data.remote.model.response.home.ResponseUserDTO
import org.sopt.sample.databinding.FragmentHomeBinding
import org.sopt.sample.domain.mapper.Mapper
import org.sopt.sample.domain.model.home.User
import org.sopt.sample.presentation.common.binding.BindingFragment
import org.sopt.sample.presentation.ui.home.adapter.HomeHeaderAdapter
import org.sopt.sample.presentation.ui.home.adapter.HomeUsersAdapter
import org.sopt.sample.presentation.ui.home.adapter.decorator.HomeRepoAdapterDecorator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var homeUsersAdapter: HomeUsersAdapter
    private lateinit var homeHeaderAdapter: HomeHeaderAdapter
    private val users = mutableListOf<User>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUsers()
        initRecyclerView()
    }

    // TODO : repository, viewModel로 리팩토링
    private fun getUsers() {
        ApiPool.homeApi.getUsers(page = 1).enqueue(object : Callback<ResponseUserDTO> {
            override fun onResponse(
                call: Call<ResponseUserDTO>,
                response: Response<ResponseUserDTO>
            ) {
                val tempUsers = response.body()?.let { Mapper.getUsersMapper(it.data) }
                Timber.tag("HomeFragment").e(response.message())
            }

            override fun onFailure(call: Call<ResponseUserDTO>, t: Throwable) {
                Timber.tag("HomeFragment").e(t)
            }

        })
    }

    private fun initRecyclerView() {
        homeUsersAdapter = HomeUsersAdapter(:: selectUser)
        homeUsersAdapter.submitList(users)

        homeHeaderAdapter = HomeHeaderAdapter()
        homeHeaderAdapter.updateTitle(title = getString(R.string.home_header_title))

        binding.rvHomeRepo.apply {
            adapter = ConcatAdapter(homeHeaderAdapter, homeUsersAdapter)
            addItemDecoration(HomeRepoAdapterDecorator())
        }
    }

    private fun selectUser(user: User) {
        // TODO : select event 처리
    }

    fun scrollToTop() {
        binding.rvHomeRepo.smoothScrollToPosition(0)
    }

    companion object {
        @JvmStatic
        fun newInstance(args: String) = HomeFragment().apply {
            arguments = bundleOf("key" to args)
        }
    }

}