package org.sopt.sample.presentation.common.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.sopt.sample.R

@BindingAdapter("formatStr", "targetStr")
fun bindFormatText(view: TextView, formatStr: String, targetStr: String) {
    view.text = formatStr.format(targetStr)
}


@BindingAdapter("bindImage")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        Glide.with(this)
            .load(imgUrl)
            .error(R.color.gray_200)
            .placeholder(R.color.gray_200)
            .centerCrop()
            .into(this)
    }
}