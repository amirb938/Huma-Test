package com.amir.huma.presenter.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.amir.huma.R
import com.amir.huma.model.local.SettingItem

class SettingItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_setting_custom, parent, false).apply {
                isFocusable = true
                isFocusableInTouchMode = true
            }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val view = viewHolder?.view as View
        val o = item as SettingItem
        (view.findViewById(R.id.txt_title_setting) as TextView).text = o.name
        (view.findViewById(R.id.img_setting) as ImageView).setImageResource(o.imageResource)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}