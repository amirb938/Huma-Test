package com.amir.huma.presenter

import android.content.Context
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.amir.huma.R
import com.amir.huma.model.local.Book
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class BookViewPresenter : Presenter() {

    companion object {
        private val IMAGE_CARD_WIDTH = 300
        private val IMAGE_CARD_HEIGHT = 200
    }

    var typeFace: Typeface? = null
    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        val view = ImageCardView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            setMainImageDimensions(IMAGE_CARD_WIDTH, IMAGE_CARD_HEIGHT)
            badgeImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_shopping_cart_24)
            cardType = BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA
            infoVisibility = BaseCardView.CARD_REGION_VISIBLE_ACTIVATED
        }

        val title: TextView = view.findViewById(R.id.title_text)
        val content: TextView = view.findViewById(R.id.content_text)
        title.typeface = getTypeFace(parent.context)
        content.typeface = getTypeFace(parent.context)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        (item as? Book)?.let { item ->
            (viewHolder.view as? ImageCardView)?.apply {
                titleText = item.title
                contentText = item.description
                Glide.with(context).load(item.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .apply(RequestOptions().centerCrop())
                    .into(this.mainImageView)
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        val imageCardView = viewHolder.view as ImageCardView
        imageCardView.badgeImage = null
        imageCardView.mainImage = null
    }




    fun getTypeFace(context: Context): Typeface {
        if (typeFace == null) {
            typeFace = ResourcesCompat.getFont(context, R.font.iranyekan_extrabold_fanum)
        }
        return typeFace!!
    }

}