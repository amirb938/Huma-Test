package com.amir.huma.view.ui.buyBook

import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.GuidanceStylist
import androidx.leanback.widget.GuidedAction
import com.amir.huma.R
import com.amir.huma.model.local.Book

class BuyBookDialogFragment : GuidedStepSupportFragment() {

    companion object {
        val EXTRA_BOOK = "book"
    }

    lateinit var book: Book

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        book = activity?.intent?.getParcelableExtra<Parcelable>(EXTRA_BOOK) as Book
        return GuidanceStylist.Guidance(
            "از خرید کتاب ${book.title} مطمئن هستید ؟?",
            "${book.description}",
            null,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_logo_book1)
        )
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        actions.add(
            0,
            GuidedAction.Builder(requireContext()).id(0)
                .icon(R.drawable.ic_baseline_shopping_cart_24)
                .title("خرید").build()
        )
        actions.add(
            1,
            GuidedAction.Builder(requireContext()).id(1).icon(R.drawable.ic_baseline_exit_to_app_24)
                .title("بازگشت").build()
        )
    }

    override fun onGuidedActionClicked(action: GuidedAction?) {
        when (action?.id) {
            0L -> Toast.makeText(requireContext(), "خرید کلیک شد", Toast.LENGTH_LONG)
                .show()
            1L -> {
                Toast.makeText(requireContext(), "بازگشت کلیک شد", Toast.LENGTH_LONG).show()
                finishGuidedStepSupportFragments()
            }
        }
    }
}