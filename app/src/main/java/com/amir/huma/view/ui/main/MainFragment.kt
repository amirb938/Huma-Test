package com.amir.huma.view.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.amir.huma.R
import com.amir.huma.model.BookData
import com.amir.huma.model.local.Book
import com.amir.huma.model.local.HeaderItemModel
import com.amir.huma.model.local.SettingItem
import com.amir.huma.presenter.main.BookViewPresenter
import com.amir.huma.presenter.main.HeaderPresenter
import com.amir.huma.presenter.main.SettingItemPresenter
import com.amir.huma.utils.MyBackgroundManager
import com.amir.huma.model.SettingIds
import com.amir.huma.view.ui.detailBook.BookDetailActivity
import com.amir.huma.view.ui.detailBook.BookDetailFragment.Companion.EXTRA_BOOK
import com.amir.huma.view.ui.error.ErrorActivity
import java.util.*


class MainFragment : BrowseSupportFragment() {
    private val TAG = "MainFragment"
    var typeFace: Typeface? = null
    lateinit var ll_browse_title: View
    var before_id = 0L

    private val myBackgroundManager: MyBackgroundManager by lazy {
        MyBackgroundManager(requireActivity()).apply {
            clearBackground()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUIElements()
        loadRows()
        initView()
        lisener()
    }

    private fun initView() {
        ll_browse_title = requireView().findViewById(R.id.ll_browse_title)
        var img_logo1: ImageView = ll_browse_title.findViewById(R.id.img_logo1)
        var img_logo2: ImageView = ll_browse_title.findViewById(R.id.img_logo2)
        var txt_title: TextView = ll_browse_title.findViewById(R.id.txt_title)
        img_logo1.setImageDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.ic_logo_book1
            )
        )
        img_logo2.setImageDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.ic_logo_book2
            )
        )
        txt_title.text = getString(R.string.title_browse)
//        ll_browse_title.visibility = View.GONE
//        (ll_browse_title.findViewById(R.id.txt_title) as TextView).typeface = getTypeFace(requireActivity())

    }

    private fun lisener() {
        onItemViewSelectedListener =
            OnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
                if (item is Book) {
                    if (before_id > row.id || row.id == 0L) {
                        ll_browse_title.visibility = View.VISIBLE
                    } else {
                        ll_browse_title.visibility = View.GONE
                    }
                    before_id = row.id
                    Log.d(TAG, "onItemSelected: ${row.id}")

                    myBackgroundManager.updateBackground(item.imageUrl!!)
                } else {
                    myBackgroundManager.clearBackground()
                }
            }

        onItemViewClickedListener =
            OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->

                if (item is Book) {
                    var intent = Intent(activity, BookDetailActivity::class.java).apply {
                        putExtra(EXTRA_BOOK, item)
                    }
                    startActivity(intent)
                } else if (item is SettingItem) {
                    if (item.id == SettingIds.EXIT_ID) {

                    } else {
                        showError()
                    }
                } else {
                    showError()
                }
            }

    }

    private fun showError() {
        startActivity(Intent(activity, ErrorActivity::class.java))
    }


    private fun setupUIElements() {
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireActivity(), R.color.colorSecondary)

        setHeaderPresenterSelector(object : PresenterSelector() {
            override fun getPresenter(item: Any?): Presenter {
                return HeaderPresenter()
            }
        })

    }


    private fun loadRows() {
        val list = BookData.LIST

        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val bookViewPresenter = BookViewPresenter()
        for (i in 0 until BookData.BOOK_CATEGORY.size) {
            if (i != 0) {
                Collections.shuffle(list)
            }

            val listRowAdapter = ArrayObjectAdapter(bookViewPresenter)
            for (j in 0 until list.size) {
                listRowAdapter.add(list[j])
            }
            var header = HeaderItemModel(i.toLong(), BookData.BOOK_CATEGORY[i])

            rowsAdapter.add(ListRow(header, listRowAdapter))

        }
        var setting_header = HeaderItemModel((BookData.BOOK_CATEGORY.size + 1).toLong(), "تنظیمات")
        val itemListRowSettingAdapter = ArrayObjectAdapter(SettingItemPresenter()).apply {
            add(SettingItem(SettingIds.SETTING_ID, "تنظیمات", R.drawable.ic_baseline_settings_24))
            add(SettingItem(SettingIds.PROFILE_ID, "پروفایل", R.drawable.ic_baseline_person_24))
            add(SettingItem(SettingIds.EXIT_ID, "خروج", R.drawable.ic_baseline_person_24))
        }
        rowsAdapter.add(ListRow(setting_header, itemListRowSettingAdapter))
        adapter = rowsAdapter
    }

    fun getTypeFace(context: Context): Typeface {
        if (typeFace == null) {
            typeFace = ResourcesCompat.getFont(context, R.font.iranyekan_light_fanum)
        }
        return typeFace!!
    }

}
