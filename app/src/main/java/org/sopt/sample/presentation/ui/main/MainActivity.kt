package org.sopt.sample.presentation.ui.main

import android.os.Bundle
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
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, HomeFragment())
            .commitAllowingStateLoss()

        binding.bnvMain.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcv_main, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_gallery -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcv_main, GalleryFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_search -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcv_main, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

}