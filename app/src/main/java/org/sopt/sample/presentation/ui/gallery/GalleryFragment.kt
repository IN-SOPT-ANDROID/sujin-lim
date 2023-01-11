package org.sopt.sample.presentation.ui.gallery

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import coil.load
import org.sopt.sample.R
import org.sopt.sample.databinding.FragmentGalleryBinding
import org.sopt.sample.presentation.common.binding.BindingFragment

class GalleryFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {
    private lateinit var imageLauncher: ActivityResultLauncher<String>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLauncher()
        initListener()
    }

    private fun initLauncher() {
        imageLauncher = registerForActivityResult(GetContent()) { uri: Uri? ->
            // Handle the returned Uri
            binding.ivGallerySample.load(uri)
        }

        permissionLauncher = registerForActivityResult(RequestPermission()) { isPermitted: Boolean ->
            if (isPermitted) selectImage()
        }
    }
    private fun initListener() {
        binding.tvGalleryBtn.setOnClickListener {
            requestPermission()
            // selectImage()
        }
    }

    private fun selectImage() {
        imageLauncher.launch("image/*")
    }

    private fun requestPermission() {
        permissionLauncher.launch(READ_EXTERNAL_STORAGE)
    }

}