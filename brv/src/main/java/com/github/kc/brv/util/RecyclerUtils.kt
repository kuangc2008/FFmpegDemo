/*
 * Copyright (C) 2018 Drake, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.kc.brv.util

import android.widget.RemoteViews
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.github.kc.brv.BindingAdapter
import com.github.kc.brv.DefaultDecoration
import com.github.kc.brv.annotation.DividerOrientation
import com.github.kc.brv.layoutmanager.HoverLinearLayoutManager
import java.lang.NullPointerException

fun RecyclerView.linear(
    @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL,
    reverseLayout: Boolean = false,
    scrollEnabled: Boolean = true,
    stackFromEnd: Boolean = false
) : RecyclerView {
    layoutManager = HoverLinearLayoutManager(context, orientation, reverseLayout).apply {
        this.scrollEnabled = scrollEnabled
        this.stackFromEnd = stackFromEnd
    }
    return this
}


fun RecyclerView.divider(
    @DrawableRes drawable: Int,
    orientation: DividerOrientation = DividerOrientation.HORIZONTAL
): RecyclerView {
    return divider {
        setDrawable(drawable)
        this.orientation = orientation
    }
}


fun  RecyclerView.divider (
    block : DefaultDecoration.() -> Unit
): RecyclerView {
    val itemDecoration = DefaultDecoration(context).apply(block)
    addItemDecoration(itemDecoration)
    return this
}

fun RecyclerView.setup(action : BindingAdapter.(RecyclerView) -> Unit) : BindingAdapter{
    val adapter = BindingAdapter()
    adapter.action(this)
    this.adapter = adapter
    return adapter
}

val RecyclerView.bindingAdapter
    get() = adapter as BindingAdapter ?: throw NullPointerException("no binder")

var RecyclerView.models
    get() = bindingAdapter.models
    set(value) {
        bindingAdapter.models = value
    }