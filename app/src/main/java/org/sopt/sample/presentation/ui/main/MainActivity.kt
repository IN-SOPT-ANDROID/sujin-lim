package org.sopt.sample.presentation.ui.main

import android.os.Bundle
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.common.binding.BindingActivity
import org.sopt.sample.presentation.common.extension.replace
import org.sopt.sample.presentation.ui.gallery.GalleryFragment
import org.sopt.sample.presentation.ui.home.HomeFragment
import org.sopt.sample.presentation.ui.search.SearchFragment
import timber.log.Timber

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        changeFragment(R.id.menu_home)

        binding.bnvMain.run {
            setOnItemSelectedListener {
                changeFragment(it.itemId)
                true
            }
        }

        binding.bnvMain.setOnItemReselectedListener { item ->
            when(item.itemId) {
                R.id.menu_home -> {
                    binding.fcvMain.getFragment<HomeFragment>().scrollToTop()
                }
            }
        }
    }

    private fun changeFragment(menuItemId: Int) = when (menuItemId) {
        R.id.menu_home -> replace<HomeFragment>(R.id.fcv_main, HomeFragment::class.java.simpleName)
        R.id.menu_gallery -> replace<GalleryFragment>(R.id.fcv_main)
        R.id.menu_search -> replace<SearchFragment>(R.id.fcv_main)
        else -> Timber.e(IllegalArgumentException(getString(R.string.error_not_found_menu_item)))
    }

}