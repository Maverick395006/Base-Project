package com.maverick.baseproject.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleOrGone")
fun setVisibleOrGone(view: View, condition:Boolean){
    view.visibility = if (condition) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleOrInvisible")
fun setVisibleOrInvisible(view: View, condition:Boolean){
    view.visibility = if (condition) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("goneWhenEmpty")
fun goneWhenEmpty(textView: TextView, goneWhenEmpty:Boolean){
    textView.visibility = if (textView.text.isNullOrEmpty() && goneWhenEmpty) View.GONE else View.VISIBLE
}