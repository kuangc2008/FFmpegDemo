package com.example.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.RecyclerView

class ARecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    var requestL : Boolean = true

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        requestL = state == SCROLL_STATE_IDLE
    }

    override fun requestLayout() {
        if (requestL) {
            super.requestLayout()
            Log.i("kcc4", "recyclerView  request")
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        Log.i("kcc4","recyceView onLayout")
    }


}