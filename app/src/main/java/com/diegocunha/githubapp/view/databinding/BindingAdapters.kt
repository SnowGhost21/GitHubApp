package com.diegocunha.githubapp.view.databinding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.diegocunha.githubapp.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        Glide
            .with(view.context)
            .load(it)
            .into(view)
    }
}


@BindingAdapter("visibleOrGone")
fun visibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("description")
fun description(view: TextView, description: String?) {
    view.text = if(!description.isNullOrEmpty()) description else view.context.getString(R.string.description_not_available)
}