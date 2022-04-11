package com.amir.huma

object BookData {
    val BOOK_CATEGORY = arrayOf(
        "فلسفه و روانشناسی",
        "تکنولوژی و مهندسی",
        "تاریخ و جغرافیا",
        "هنر و سرگرمی"
    )

    val LIST: List<Book> by lazy {
        setupBooks()
    }

    private fun setupBooks(): List<Book> {
        val title = arrayOf(
            "اثر مرکب",
            "انسان در جستجوی معنا",
            "مغازه جادویی",
            "ایکیگای",
            "پاستیل های بنفش",
            "بی حد و مرز",
            "تخت خوابت را مرتب کن",
            "قانون 5 ثانیه",
            "هنر ظریف بیخیالی",
        )

        val description =
            "خلاصه کتاب اثر مرکب «انتخاب های شما تنها زمانی معنی دار است که آنها را به دلخواه به رؤیاهای خود متصل کنید. انتخاب های شایسته و انگیزشی، همان هایی هستند که شما به عنوان هدف خود و هسته اصلی زندگی خود در بالاترین ارزش های خود تعین می کنید. شما باید چیزی را بخواهید و می دانید که چرا شما آن را می خواهید یا به راحتی می توانید آن از دست بدهید.» «اولین گام در جهت تغییر، آگاهی است. اگر می خواهید از جایی که هستید به جایی که می خواهید بروید، باید با درک انتخاب هایی که شما را از مقصد مورد نظر خود دور می کنند، شروع کنید.»"
        val imageUrl = arrayOf(
            "https://dkstatics-public.digikala.com/digikala-products/df9fb4ee079cfe6144d356e36f88393b1d4274ec_1620120417.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80",
            "https://dkstatics-public.digikala.com/digikala-products/6e02981600c0622a746d730f632fcbd934fe844b_1634497731.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80",
            "https://dkstatics-public.digikala.com/digikala-products/dcfcc7dfd6356662aa8860802fba166ce6ed10d2_1646154053.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80",
            "https://dkstatics-public.digikala.com/digikala-products/4928630e648a07354813184db929638afcf839d1_1629502663.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80",
            "https://dkstatics-public.digikala.com/digikala-products/575702.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80",
            "https://dkstatics-public.digikala.com/digikala-products/ec65451d303dcbc2b3ab4a3a2c9ac3276a979ad5_1604134441.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80",
            "https://dkstatics-public.digikala.com/digikala-products/311494.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80",
            "https://dkstatics-public.digikala.com/digikala-products/522a1af8efe51cd33f93b0d3a78849c1558da219_1636670422.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80",
            "https://dkstatics-public.digikala.com/digikala-products/2083764b409d361ec36e68c09f9f1926b3e3a0af_1622844435.jpg?x-oss-process=image/resize,m_lfit,h_300,w_300/quality,q_80"
        )

        val list = title.indices.map {
            buildBookInfo(
                title[it],
                description,
                imageUrl[it]
            )
        }

        return list
    }

    private fun buildBookInfo(
        title: String,
        description: String,
        imageUrl: String,
    ): Book {
        val book = Book()
        book.title = title
        book.description = description
        book.imageUrl = imageUrl
        return book
    }
}