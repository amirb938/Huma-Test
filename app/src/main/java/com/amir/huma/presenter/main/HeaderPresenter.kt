package com.amir.huma.presenter.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowHeaderPresenter
import com.amir.huma.R
import com.amir.huma.model.local.HeaderItemModel

class HeaderPresenter : RowHeaderPresenter() {
    override fun onCreateViewHolder(viewGroup: ViewGroup): ViewHolder {
        val inflater = viewGroup.context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.header_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, o: Any) {
        val iconHeaderItem: HeaderItemModel = (o as ListRow).headerItem as HeaderItemModel
        val rootView = viewHolder.view
        val label = rootView.findViewById<View>(R.id.header_label) as TextView
        label.text = iconHeaderItem.name
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {

    }

}