package com.amir.huma.view.ui.detailBook

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.*
import com.amir.huma.R
import com.amir.huma.model.BookData
import com.amir.huma.model.local.Book
import com.amir.huma.presenter.detail.BookDetailDescriptionPresenter
import com.amir.huma.presenter.main.BookViewPresenter
import com.amir.huma.view.ui.buyBook.BuyBookActivity
import com.amir.huma.view.ui.buyBook.BuyBookDialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class BookDetailFragment : DetailsSupportFragment() {

    private val TAG = "BookDetailFragment"

    companion object {
        private const val DETAIL_THUMB_WIDTH = 300
        private const val DETAIL_THUMB_HEIGHT = 400
        const val EXTRA_BOOK = "book"
    }

    private val detailFullOverviewRowPresenter =
        FullWidthDetailsOverviewRowPresenter(BookDetailDescriptionPresenter())


    // deprecate
//    private val detailFullOverviewRowPresenter =
//        DetailsOverviewRowPresenter(BookDetailDescriptionPresenter())

    lateinit var detailOverviewRow: DetailsOverviewRow
    lateinit var book: Book

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        book = activity?.intent?.getParcelableExtra<Parcelable>(EXTRA_BOOK) as Book
        Log.d(TAG, "onActivityCreated: ${book.title}")

        detailOverviewRow = DetailsOverviewRow(book)
        setBookThumbnail(book)
        setBookDetailActions()
        setBookDetailAdapter()

    }


    private fun setBookThumbnail(item: Book) {
        Glide.with(requireView()).asBitmap().load(item.imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    detailOverviewRow.setImageBitmap(activity, resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })

    }

    private fun setBookDetailActions() {
        val actionAdapter = ArrayObjectAdapter().apply {
            add(Action(1, "????????????").apply {
                icon = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_remove_red_eye_24
                )
            })
            add(Action(2, "????????").apply {
                icon = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_shopping_cart_24
                )
            })
            add(Action(3, "????????????").apply {
                icon = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_edit_24
                )
            })

            setOnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
                if (item is Action) {
                    if (item.id == 2L) {
                        var intent = Intent(activity, BuyBookActivity::class.java).apply {
                            putExtra(BuyBookDialogFragment.EXTRA_BOOK, book)
                        }
                        startActivity(intent)
                    }
                }
            }
        }

        detailOverviewRow.actionsAdapter = actionAdapter
    }

    private fun setBookDetailAdapter() {
        val classPresenterSelector = ClassPresenterSelector().apply {
            addClassPresenter(DetailsOverviewRow::class.java, detailFullOverviewRowPresenter)
            addClassPresenter(ListRow::class.java, ListRowPresenter())
        }
        val detailPageAdapter = ArrayObjectAdapter(classPresenterSelector).apply {
            add(detailOverviewRow)
            add(generateRelatedMoviesRow())
        }
        setAdapter(detailPageAdapter)
    }

    private fun generateRelatedMoviesRow(): ListRow {
        val list = BookData.LIST

        val listRowAdapter = ArrayObjectAdapter(BookViewPresenter())
        for (j in 0 until list.size) {
            listRowAdapter.add(list[j])
        }

        return ListRow(HeaderItem(0, "?????????????? ??????????"), listRowAdapter)
    }
}