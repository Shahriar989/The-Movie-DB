package com.umar.a12_the_movie_db.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.shahriar.a12_the_movie_db.R

class RcvItem : ConstraintLayout {

    private lateinit var mLayout: ConstraintLayout
    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var desc: TextView

    private var titleText = ""
    private var descText = ""
    private var imageId = 0

    constructor(context: Context) : super(context) {

        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        getResFromXml(context, attrs)
        initView(context)
    }

    private fun initView(context: Context) {

        layoutParams = LayoutParams(
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        )

        LayoutInflater
            .from(context)
            .inflate(R.layout.custom_item, this, true)

        mLayout = findViewById(R.id.mLayout)
        image = findViewById(R.id.image)
        title = findViewById(R.id.title)
        desc = findViewById(R.id.desc)

        if (titleText.isNotEmpty()) {
            title.text = titleText
        }

        if (descText.isNotEmpty()) {
            desc.text = descText
        }

        if (imageId != 0) {
            image.setImageResource(imageId)
        }
    }

    private fun getResFromXml(context: Context, attrs: AttributeSet) {

        val data = context.obtainStyledAttributes(attrs, R.styleable.RcvItem)

        titleText = data.getString(R.styleable.RcvItem_tText).toString()
        descText = data.getString(R.styleable.RcvItem_dText).toString()

        imageId = data.getInteger(R.styleable.RcvItem_src, 0)

        data.recycle()
    }
}
