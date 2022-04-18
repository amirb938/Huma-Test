package com.amir.huma.view.ui.error

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.ErrorSupportFragment
import com.amir.huma.R

class ErrorViewFragment : ErrorSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "خطایی رخ داده است !!"
        imageDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_error_24)
        message = "آیتم مورد نظر یافت نشد"
        setDefaultBackground(false)
        buttonText = "بازگشت"
        buttonClickListener = View.OnClickListener {
            activity?.finish()
        }
    }
}