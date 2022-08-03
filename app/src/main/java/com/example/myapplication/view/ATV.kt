package com.example.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView

class ATV @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TextView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.i("kcc4", "tv  onMeasre")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.i("kcc4","tv onLayout")
    }
}