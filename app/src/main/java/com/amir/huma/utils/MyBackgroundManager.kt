package com.amir.huma.utils

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import com.amir.huma.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class MyBackgroundManager(private val activity: Activity) {
    var backgroundManager: BackgroundManager = BackgroundManager.getInstance(activity).apply {
        attach(activity.window)
    }
    var defaultDrawable: Drawable = ContextCompat.getDrawable(activity, R.drawable.ic_logo_book1)!!
    fun updateBackground(imageUrl: String) {
        Glide.with(activity)
            .load(imageUrl)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    backgroundManager.drawable = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }


            })
    }


    fun clearBackground() {
        backgroundManager.clearDrawable()
        backgroundManager.drawable = defaultDrawable
    }


}