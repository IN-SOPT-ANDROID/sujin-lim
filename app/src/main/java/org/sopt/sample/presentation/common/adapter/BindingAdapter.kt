package org.sopt.sample.presentation.common.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("formatStr", "targetStr")
fun bindFormatText(view: TextView, formatStr: String, targetStr: String) {
    view.text = formatStr.format(targetStr)
}