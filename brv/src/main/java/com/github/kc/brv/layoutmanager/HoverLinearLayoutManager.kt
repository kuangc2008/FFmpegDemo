package com.github.kc.brv.layoutmanager

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kc.brv.BindingAdapter


class HoverLinearLayoutManager @JvmOverloads constructor(
    context : Context,
    orientation : Int = RecyclerView.VERTICAL,
    reverseLayout : Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout){

    var scrollEnabled = false

    private val mHeaderPositions = arrayListOf<Int>(0)
    private val mHeaderPositionsObserver: RecyclerView.AdapterDataObserver = HeaderPositionsAdapterDataObserver()

    // Sticky header's ViewHolder and dirty state.
    private val mHover: View? = null

    // Attach count, to ensure the sticky header is only attached and detached when expected.
    private var hoverAttachCount = 0

    private var mAdapter: BindingAdapter? = null


    override fun onAttachedToWindow(view: RecyclerView) {
        super.onAttachedToWindow(view)
        setAdapter(view.adapter)
        Log.i("kcc", "onAttachedToWindow", Exception())
    }

    override fun onAdapterChanged(
        oldAdapter: RecyclerView.Adapter<*>?,
        newAdapter: RecyclerView.Adapter<*>?
    ) {
        super.onAdapterChanged(oldAdapter, newAdapter)
        setAdapter(newAdapter)


    }

    private fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        mAdapter?.unregisterAdapterDataObserver(mHeaderPositionsObserver)

        if (adapter is BindingAdapter) {
            mAdapter = adapter as BindingAdapter
            mAdapter?.registerAdapterDataObserver(mHeaderPositionsObserver)
            mHeaderPositionsObserver.onChanged()
        } else {
            mAdapter = null
            mHeaderPositions.clear()
        }
    }


    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        Log.i("kcc", "scroll  1")

        detachHover()
        val scrolled = super.scrollVerticallyBy(dy, recycler, state)
        attachHover()
        if (scrolled != 0) {
            updateHover(recycler!!, false)
        }
        return scrolled
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        Log.i("kcc", "scroll  2")
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {

        Log.i("kcc", "onLayoutChildren", Exception())

        detachHover()
        super.onLayoutChildren(recycler, state)
        attachHover()

        if (!state.isPreLayout) {
            updateHover(recycler, true)
        }
    }

    private fun detachHover() {
        if (--hoverAttachCount == 0 && mHover != null) {
            detachView(mHover)
        }
    }

    private fun attachHover() {
        if (++hoverAttachCount == 1 && mHover != null) {
            attachView(mHover)
        }
    }

    private fun updateHover(recycler : RecyclerView.Recycler, layout : Boolean) {
        val headerCount = mHeaderPositions.size
        val childCount = childCount
    }


    private class HeaderPositionsAdapterDataObserver : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {

            Log.i("kcc", "onChanged", Exception())

            // There's no hint at what changed, so go through the adapter.
//            mHeaderPositions.clear()
//            val itemCount: Int = mAdapter.getItemCount()
//            for (i in 0 until itemCount) {
//                if (mAdapter.isHover(i)) {
//                    mHeaderPositions.add(i)
//                }
//            }
//
//            // Remove hover header immediately if the entry it represents has been removed. A layout will follow.
//            if (mHover != null && !mHeaderPositions.contains(mHoverPosition)) {
//                scrapHover(null)
//            }
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {

            Log.i("kcc", "onItemRangeInserted", Exception())

            // Shift headers below down.
//            val headerCount: Int = mHeaderPositions.size
//            if (headerCount > 0) {
//                var i: Int = findHeaderIndexOrNext(positionStart)
//                while (i != -1 && i < headerCount) {
//                    mHeaderPositions.set(i, mHeaderPositions.get(i) + itemCount)
//                    i++
//                }
//            }
//
//            // Add new headers.
//            for (i in positionStart until positionStart + itemCount) {
//                if (mAdapter.isHover(i)) {
//                    val headerIndex: Int = findHeaderIndexOrNext(i)
//                    if (headerIndex != -1) {
//                        mHeaderPositions.add(headerIndex, i)
//                    } else {
//                        mHeaderPositions.add(i)
//                    }
//                }
//            }
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {

            Log.i("kcc", "onItemRangeRemoved", Exception())

//            var headerCount: Int = mHeaderPositions.size
//            if (headerCount > 0) {
//                // Remove headers.
//                for (i in positionStart + itemCount - 1 downTo positionStart) {
//                    val index: Int = findHeaderIndex(i)
//                    if (index != -1) {
//                        mHeaderPositions.removeAt(index)
//                        headerCount--
//                    }
//                }
//
//                // Remove hover header immediately if the entry it represents has been removed. A layout will follow.
//                if (mHover != null && !mHeaderPositions.contains(mHoverPosition)) {
//                    scrapHover(null)
//                }
//
//                // Shift headers below up.
//                var i: Int = findHeaderIndexOrNext(positionStart + itemCount)
//                while (i != -1 && i < headerCount) {
//                    mHeaderPositions.set(i, mHeaderPositions.get(i) - itemCount)
//                    i++
//                }
//            }
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {

            Log.i("kcc", "onItemRangeMoved", Exception())

            // Shift moved headers by toPosition - fromPosition.
            // Shift headers in-between by itemCount (reverse if downwards).
//            val headerCount: Int = mHeaderPositions.size
//            if (headerCount > 0) {
//                val topPosition = Math.min(fromPosition, toPosition)
//                var i: Int = findHeaderIndexOrNext(topPosition)
//                while (i != -1 && i < headerCount) {
//                    val headerPos: Int = mHeaderPositions.get(i)
//                    var newHeaderPos = headerPos
//                    if (headerPos >= fromPosition && headerPos < fromPosition + itemCount) {
//                        newHeaderPos += toPosition - fromPosition
//                    } else if (fromPosition < toPosition && headerPos >= fromPosition + itemCount && headerPos <= toPosition) {
//                        newHeaderPos -= itemCount
//                    } else if (fromPosition > toPosition && headerPos >= toPosition && headerPos <= fromPosition) {
//                        newHeaderPos += itemCount
//                    } else {
//                        break
//                    }
//                    if (newHeaderPos != headerPos) {
//                        mHeaderPositions.set(i, newHeaderPos)
//                        sortHeaderAtIndex(i)
//                    } else {
//                        break
//                    }
//                    i++
//                }
//            }
        }

        private fun sortHeaderAtIndex(index: Int) {

            Log.i("kcc", "sortHeaderAtIndex", Exception())
//            val headerPos: Int = mHeaderPositions.removeAt(index)
//            val headerIndex: Int = findHeaderIndexOrNext(headerPos)
//            if (headerIndex != -1) {
//                mHeaderPositions.add(headerIndex, headerPos)
//            } else {
//                mHeaderPositions.add(headerPos)
//            }
        }
    }
}