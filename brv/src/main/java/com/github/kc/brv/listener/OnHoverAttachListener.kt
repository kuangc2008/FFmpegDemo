package com.github.kc.brv.listener

import android.view.View

interface OnHoverAttachListener {

    fun attachHover(v : View)

    fun detachHover(v : View)
}