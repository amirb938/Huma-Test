package com.amir.huma.view.ui.detailBook

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.amir.huma.R

class BookDetailActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.book_detail_fragment, BookDetailFragment())
                .commitNow()
        }
    }
}