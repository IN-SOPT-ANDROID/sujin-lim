package org.sopt.sample.presentation.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.common.base.BindingActivity
import org.sopt.sample.presentation.ui.main.gallery.GalleryFragment
import org.sopt.sample.presentation.ui.main.home.HomeFragment
import org.sopt.sample.presentation.ui.main.search.SearchFragment

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        setFragment(HomeFragment())

        binding.bnvMain.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_home -> {
                    setFragment(fragment = HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_gallery -> {
                    setFragment(fragment = GalleryFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_search -> {
                    setFragment(fragment = SearchFragment())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        binding.bnvMain.setOnItemReselectedListener { item ->
            when(item.itemId) {
                R.id.menu_home -> {
                    binding.fcvMain.getFragment<HomeFragment>().scrollToTop()
                }
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commitAllowingStateLoss()
    }

}