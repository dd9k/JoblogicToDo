package com.example.joblogictodo.ui.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.joblogictodo.R

object BindingAdapters {
    @BindingAdapter("app:statusLoading")
    @JvmStatic fun statusLoading(textView: TextView, status: String?) {
        textView.text = status ?: ""
        if (textView.text == textView.context.getText(R.string.success_message)) {
            textView.visibility = View.GONE
        } else {
            textView.visibility = View.VISIBLE
        }
    }
}
