package com.amir.huma

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import java.util.*

class MainFragment : BrowseSupportFragment() {
    private val TAG = "MainFragment"
    private lateinit var mBackgroundManager: BackgroundManager
    private var mDefaultBackground: Drawable? = null
    private lateinit var mMetrics: DisplayMetrics
    var typeFace: Typeface? = null
    lateinit var ll_browse_title: View
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareBackgroundManager()
        setupUIElements()
        loadRows()
        initView()
        lisener()
    }

    private fun initView() {
        ll_browse_title = requireView().findViewById(R.id.ll_browse_title)
        var img_logo1: ImageView = ll_browse_title.findViewById(R.id.img_logo1)
        var img_logo2: ImageView = ll_browse_title.findViewById(R.id.img_logo2)
        img_logo1.setImageDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.ic_logo1
            )
        )
        img_logo2.setImageDrawable(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.ic_logo2
            )
        )

//        ll_browse_title.visibility = View.GONE
//        (ll_browse_title.findViewById(R.id.txt_title) as TextView).typeface = getTypeFace(requireActivity())

    }

    private fun lisener() {
        onItemViewSelectedListener = ItemViewSelectedListener()
    }


    private fun prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(activity?.window)
        mDefaultBackground = ContextCompat.getDrawable(requireActivity(), R.drawable.view_back)
        mMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(mMetrics)
    }

    private fun setupUIElements() {

        title = getString(R.string.browse_title)
        // over title
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        // set fastLane (or headers) background color
        brandColor = ContextCompat.getColor(requireActivity(), R.color.fastlane_background)

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
            val header = HeaderItem(i.toLong(), BookData.BOOK_CATEGORY[i])

            rowsAdapter.add(ListRow(header, listRowAdapter))
        }
        adapter = rowsAdapter

    }

    fun getTypeFace(context: Context): Typeface {
        if (typeFace == null) {
            typeFace = ResourcesCompat.getFont(context, R.font.iranyekan_light_fanum)
        }
        return typeFace!!
    }


    var before_id = 0L

    private inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?, item: Any?,
            rowViewHolder: RowPresenter.ViewHolder, row: Row
        ) {
            if (item is Book) {
                if (before_id > row.id || row.id == 0L) {
                    ll_browse_title.visibility = View.VISIBLE
                } else {
                    ll_browse_title.visibility = View.GONE
                }
                before_id = row.id
                Log.d(TAG, "onItemSelected: ${row.id}")
            }
        }
    }

}