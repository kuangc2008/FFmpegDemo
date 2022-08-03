package com.example.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout

class ALinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.i("kcc4", "llll onMeasre", Exception())
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        Log.i("kcc4", "ll onLayout")
    }
}