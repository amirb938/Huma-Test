package com.amir.huma.model.local

import androidx.leanback.widget.HeaderItem

class HeaderItemModel : HeaderItem {
    /**
     * Hold an icon resource id
     */

    // Title + Icon
    // No icon, label only
    @JvmOverloads
    constructor(id: Long, name: String?) : super(id, name) {
    }

    // Title label
    constructor(name: String?) : super(name) {}

    companion object {
        private val TAG = HeaderItemModel::class.java.simpleName
        const val ICON_NONE = -1
    }
}