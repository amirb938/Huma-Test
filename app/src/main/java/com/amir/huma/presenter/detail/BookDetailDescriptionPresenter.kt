package com.amir.huma.presenter.detail

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.amir.huma.model.local.Book

class BookDetailDescriptionPresenter : AbstractDetailsDescriptionPresenter() {
    override fun onBindDescription(vh: ViewHolder, item: Any?) {
        val book = item as Book
        if (book != null) {
            vh.title.text = book.title
            vh.subtitle.text = "توضیحات کامل کتاب ${book.title}"
            vh.body.text = book.description

        }

    }
}