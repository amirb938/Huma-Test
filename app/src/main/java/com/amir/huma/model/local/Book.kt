package com.amir.huma.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    var title: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,


) : Parcelable