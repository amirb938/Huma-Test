package com.amir.huma

import java.io.Serializable

data class Book(
    var title: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
) : Serializable {

    override fun toString(): String {
        return "Book{" +
                ", title='" + title + '\'' +
                ", cardImageUrl='" + imageUrl + '\'' +
                '}'
    }

}