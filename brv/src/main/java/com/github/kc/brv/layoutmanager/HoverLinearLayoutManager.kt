package com.github.kc.brv.layoutmanager

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kc.brv.BindingAdapter
import com.github.kc.brv.listener.OnHoverAttachListener
import com.kc.k_util.LogUtils


class HoverLinearLayoutManager @JvmOverloads constructor(
    context : Context,
    orientation : Int = RecyclerView.VERTICAL,
    reverseLayout : Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout){

    var scrollEnabled = false

    private val mHeaderPositions = arrayListOf<Int>(0)
    private val mHeaderPositionsObserver: RecyclerView.AdapterDataObserver = HeaderPositionsAdapterDataObserver()

    // Sticky header's ViewHolder and dirty state.
    private var mHover: View? = null

    private var mTranslationX = 0f
    private var mTranslationY = 0f


    private var mPendingScrollPosition = RecyclerView.NO_POSITION
    private var mPendingScrollOffset = 0


    // Attach count, to ensure the sticky header is only attached and detached when expected.
    private var hoverAttachCount = 0
    private var mHoverPosition = RecyclerView.NO_POSITION

    private var mAdapter: BindingAdapter? = null


    override fun onAttachedToWindow(view: RecyclerView) {
        super.onAttachedToWindow(view)
        LogUtils.logMethod()
        setAdapter(view.adapter)

    }

    override fun onAdapterChanged(
        oldAdapter: RecyclerView.Adapter<*>?,
        newAdapter: RecyclerView.Adapter<*>?
    ) {
        super.onAdapterChanged(oldAdapter, newAdapter)
        LogUtils.logMethod()
        setAdapter(newAdapter)


    }

    private fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        LogUtils.logMethod()
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
        LogUtils.logMethod()
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
        LogUtils.logMethod()
        Log.i("kcc", "scroll  2")
        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {

        Log.i("kcc", "onLayoutChildren", Exception())
        LogUtils.logMethod()
        detachHover()
        super.onLayoutChildren(recycler, state)
        attachHover()

        if (!state.isPreLayout) {
            updateHover(recycler, true)
        }
    }

    private fun detachHover() {
        LogUtils.logMethod()

        if (--hoverAttachCount == 0 && mHover != null) {
            detachView(mHover!!)
        }
        Log.i("kcc3", "hoverAttachCount $hoverAttachCount")
    }

    private fun attachHover() {
        LogUtils.logMethod()

        if (++hoverAttachCount == 1 && mHover != null) {
            attachView(mHover!!)
        }
        Log.i("kcc3", "attachHover hoverAttachCount $hoverAttachCount")
    }

    private fun isViewValidAnchor(view: View, params: RecyclerView.LayoutParams): Boolean {
        LogUtils.logMethod()
        return if (!params.isItemRemoved && !params.isViewInvalid) {
            if (orientation == VERTICAL) {
                if (reverseLayout) {
                    view.top + view.translationY <= height + mTranslationY
                } else {
                    view.bottom - view.translationY >= mTranslationY
                }
            } else {
                if (reverseLayout) {
                    view.left + view.translationX <= width + mTranslationX
                } else {
                    view.right - view.translationX >= mTranslationX
                }
            }
        } else {
            false
        }
    }

    /**
     * Finds the header index of `position` or the one before it in `mHeaderPositions`.
     */
    private fun findHeaderIndexOrBefore(position: Int): Int {
        LogUtils.logMethod()
        var low = 0
        var high = mHeaderPositions.size - 1
        while (low <= high) {
            val middle = (low + high) / 2
            if (mHeaderPositions[middle] > position) {
                high = middle - 1
            } else if (middle < mHeaderPositions.size - 1 && mHeaderPositions[middle + 1] <= position) {
                low = middle + 1
            } else {
                return middle
            }
        }
        return -1
    }

    /**
     * Finds the header index of `position` or the one next to it in `mHeaderPositions`.
     */
    private fun findHeaderIndexOrNext(position: Int): Int {
        LogUtils.logMethod()
        var low = 0
        var high = mHeaderPositions.size - 1
        while (low <= high) {
            val middle = (low + high) / 2
            if (middle > 0 && mHeaderPositions[middle - 1] >= position) {
                high = middle - 1
            } else if (mHeaderPositions[middle] < position) {
                low = middle + 1
            } else {
                return middle
            }
        }
        return -1
    }

    private fun isViewOnBoundary(view: View): Boolean {
        LogUtils.logMethod()
        return if (orientation == VERTICAL) {
            if (reverseLayout) {
                view.bottom - view.translationY > height + mTranslationY
            } else {
                view.top + view.translationY < mTranslationY
            }
        } else {
            if (reverseLayout) {
                view.right - view.translationX > width + mTranslationX
            } else {
                view.left + view.translationX < mTranslationX
            }
        }
    }

    private fun createHover(recycler: RecyclerView.Recycler, position: Int) {
        LogUtils.logMethod()
        val hoverHeader = recycler.getViewForPosition(position)

        // Setup hover header if the adapter requires it.
        val onHoverAttachListener: OnHoverAttachListener? = mAdapter!!.onHoverAttachListener
        if (onHoverAttachListener != null) {
            onHoverAttachListener.attachHover(hoverHeader)
        }

        // Add hover header as a child view, to be detached / reattached whenever LinearLayoutManager#fill() is called,
        // which happens on layout and scroll (see overrides).
        addView(hoverHeader)
        Log.i("kcc3", "*** addView")
        measureAndLayout(hoverHeader)

        // Ignore hover header, as it's fully managed by this LayoutManager.
        ignoreView(hoverHeader)
        mHover = hoverHeader
        mHoverPosition = position
        hoverAttachCount = 1
    }



    private fun measureAndLayout(hoverHeader: View) {
        LogUtils.logMethod()
        measureChildWithMargins(hoverHeader, 0, 0)
        if (orientation == VERTICAL) {
            hoverHeader.layout(paddingLeft, 0, width - paddingRight, hoverHeader.measuredHeight)

            Log.i("kcc3", "*** hoverHeader.measuredHeight ${hoverHeader.measuredHeight}")

        } else {
            hoverHeader.layout(0, paddingTop, hoverHeader.measuredWidth, height - paddingBottom)
        }
    }

    private fun updateHover(recycler : RecyclerView.Recycler, layout : Boolean) {
        LogUtils.logMethod()
        val headerCount = mHeaderPositions.size
        val childCount = childCount


        if (headerCount > 0 && childCount > 0) {
            // Find first valid child.
            var anchorView: View? = null
            var anchorIndex = -1
            var anchorPos = -1
            for (i in 0 until childCount) {
                val child = getChildAt(i) ?: continue
                val params = child.layoutParams as RecyclerView.LayoutParams
                if (isViewValidAnchor(child, params)) {
                    anchorView = child
                    anchorIndex = i
                    anchorPos = params.viewAdapterPosition
                    break
                }
            }
            if (anchorView != null && anchorPos != -1) {
                val headerIndex: Int = findHeaderIndexOrBefore(anchorPos)
                val headerPos = if (headerIndex != -1) mHeaderPositions[headerIndex] else -1
                val nextHeaderPos =
                    if (headerCount > headerIndex + 1) mHeaderPositions[headerIndex + 1] else -1
                // Show hover header if:
                // - There's one to show;
                // - It's on the edge or it's not the anchor view;
                // - Isn't followed by another hover header;
                if (headerPos != -1 && (headerPos != anchorPos || isViewOnBoundary(anchorView))
                    && nextHeaderPos != headerPos + 1
                ) {
                    // Ensure existing hover header, if any, is of correct type.
                    if (mHover != null && mAdapter != null
                        && getItemViewType(mHover!!) != mAdapter!!.getItemViewType(headerPos)
                    ) {
                        // A hover header was shown before but is not of the correct type. Scrap it.
                        scrapHover(recycler)
                    }

                    // Ensure hover header is created, if absent, or bound, if being laid out or the position changed.
                    if (mHover == null) {
                        createHover(recycler, headerPos)
                    }
                    if (layout || getPosition(mHover!!) != headerPos) {
                        // 绑定数据
                        bindHover(recycler, headerPos)
                    }

                    // Draw the hover header using translation values which depend on orientation, direction and
                    // position of the next header view.
                    var nextHeaderView: View? = null
                    if (nextHeaderPos != -1) {
                        // 2 拿到下一个item
                        nextHeaderView = getChildAt(anchorIndex + (nextHeaderPos - anchorPos))
                        // The header view itself is added to the RecyclerView. Discard it if it comes up.
                        if (nextHeaderView === mHover) {
                            nextHeaderView = null
                        }
                    }
                    mHover!!.setTranslationX(getX(mHover!!, nextHeaderView))

                    Log.i("kcc3", "getY:" + getY(mHover!!, nextHeaderView))

                    // 1 滑动到中间态的时候，用于位置的移动
                    mHover!!.setTranslationY(getY(mHover!!, nextHeaderView))
                    return
                }
            }
        }

        if (mHover != null) {
            scrapHover(recycler)
        }
    }

    private fun bindHover(recycler: RecyclerView.Recycler, position: Int) {
        LogUtils.logMethod()
        // Bind the hover header.
        recycler.bindViewToPosition(mHover!!, position)

        Log.i("kcc3", "*** bindHover:")

        mHoverPosition = position
        measureAndLayout(mHover!!)

        // If we have a pending scroll wait until the end of layout and scroll again.
        if (mPendingScrollPosition != RecyclerView.NO_POSITION) {
            val vto = mHover!!.viewTreeObserver
            vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    vto.removeOnGlobalLayoutListener(this)
                    if (mPendingScrollPosition != RecyclerView.NO_POSITION) {


                        scrollToPositionWithOffset(mPendingScrollPosition, mPendingScrollOffset)
                        setPendingScroll(RecyclerView.NO_POSITION, INVALID_OFFSET)
                    }
                }
            })
        }
    }

    private fun setPendingScroll(position: Int, offset: Int) {
        LogUtils.logMethod()
        mPendingScrollPosition = position
        mPendingScrollOffset = offset
    }

    private fun getX(headerView: View, nextHeaderView: View?): Float {
        LogUtils.logMethod()
        return if (orientation != VERTICAL) {
            var x = mTranslationX
            if (reverseLayout) {
                x += (width - headerView.width).toFloat()
            }
            if (nextHeaderView != null) {
                if (reverseLayout) {
                    var rightMargin = 0
                    if (nextHeaderView.layoutParams is ViewGroup.MarginLayoutParams) {
                        rightMargin =
                            (nextHeaderView.layoutParams as ViewGroup.MarginLayoutParams).rightMargin
                    }
                    x = Math.max((nextHeaderView.right + rightMargin).toFloat(), x)
                } else {
                    var leftMargin = 0
                    if (nextHeaderView.layoutParams is ViewGroup.MarginLayoutParams) {
                        leftMargin =
                            (nextHeaderView.layoutParams as ViewGroup.MarginLayoutParams).leftMargin
                    }
                    x = Math.min((nextHeaderView.left - leftMargin - headerView.width).toFloat(), x)
                }
            }
            x
        } else {
            mTranslationX
        }
    }

    private fun getY(headerView: View, nextHeaderView: View?): Float {
        LogUtils.logMethod()
        return if (orientation == VERTICAL) {
            var y = mTranslationY
            if (reverseLayout) {
                y += (height - headerView.height).toFloat()
            }
            if (nextHeaderView != null) {
                if (reverseLayout) {
                    var bottomMargin = 0
                    if (nextHeaderView.layoutParams is ViewGroup.MarginLayoutParams) {
                        bottomMargin =
                            (nextHeaderView.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
                    }

                    y = Math.max((nextHeaderView.bottom + bottomMargin).toFloat(), y)
                } else {
                    var topMargin = 0
                    if (nextHeaderView.layoutParams is ViewGroup.MarginLayoutParams) {
                        topMargin =
                            (nextHeaderView.layoutParams as ViewGroup.MarginLayoutParams).topMargin
                    }
                    //  拿到nextHeaderView的top
                    Log.i("kcc3", "nextHeaderView.top: ${nextHeaderView.top}  height ${headerView.height} ")
                    y = Math.min((nextHeaderView.top - topMargin - headerView.height).toFloat(), y)
                }
            }
            y
        } else {
            mTranslationY
        }
    }


    /**
     * Finds the header index of `position` in `mHeaderPositions`.
     */
    private fun findHeaderIndex(position: Int): Int {
        LogUtils.logMethod()
        var low = 0
        var high = mHeaderPositions.size - 1
        while (low <= high) {
            val middle = (low + high) / 2
            if (mHeaderPositions[middle] > position) {
                high = middle - 1
            } else if (mHeaderPositions[middle] < position) {
                low = middle + 1
            } else {
                return middle
            }
        }
        return -1
    }


    /**
     * Returns [.mHover] to the [RecyclerView]'s [RecyclerView.RecycledViewPool], assigning it
     * to `null`.
     *
     * @param recycler If passed, the hover header will be returned to the recycled view pool.
     */
    private fun scrapHover(recycler: RecyclerView.Recycler?) {
        LogUtils.logMethod()
        val hoverHeader = mHover!!
        mHover = null
        mHoverPosition = RecyclerView.NO_POSITION

        // Revert translation values.
        hoverHeader.translationX = 0f
        hoverHeader.translationY = 0f

        // Teardown holder if the adapter requires it.
        mAdapter?.run {
            onHoverAttachListener?.run {
                detachHover(hoverHeader)
            }
        }
        // Stop ignoring hover header so that it can be recycled.
        stopIgnoringView(hoverHeader)

        // Remove and recycle hover header.
        removeView(hoverHeader)

        Log.i("kcc3", "** removeView")

        recycler?.recycleView(hoverHeader)
    }

    private inner class HeaderPositionsAdapterDataObserver : RecyclerView.AdapterDataObserver() {

        override fun onChanged() {
            LogUtils.logMethod()
            Log.i("kcc", "onChanged", Exception())

            // There's no hint at what changed, so go through the adapter.
            mHeaderPositions.clear()
            mAdapter?.run {
                val itemCount = itemCount
                for (i in 0 until itemCount) {
                    if (isHover(i)) {
                        mHeaderPositions.add(i)
                    }
                }
            }

            // Remove hover header immediately if the entry it represents has been removed. A layout will follow.
            if (mHover != null && !mHeaderPositions.contains(mHoverPosition)) {
                scrapHover(null)
            }
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {

            Log.i("kcc", "onItemRangeInserted", Exception())
            LogUtils.logMethod()
            // Shift headers below down.
            val headerCount: Int = mHeaderPositions.size
            if (headerCount > 0) {
                var i: Int = findHeaderIndexOrNext(positionStart)
                while (i != -1 && i < headerCount) {
                    mHeaderPositions.set(i, mHeaderPositions.get(i) + itemCount)
                    i++
                }
            }

            // Add new headers.
            for (i in positionStart until positionStart + itemCount) {
                mAdapter?.run {
                    if (isHover(i)) {
                        val headerIndex: Int = findHeaderIndexOrNext(i)
                        if (headerIndex != -1) {
                            mHeaderPositions.add(headerIndex, i)
                        } else {
                            mHeaderPositions.add(i)
                        }
                    }
                }
            }
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            LogUtils.logMethod()
            Log.i("kcc", "onItemRangeRemoved", Exception())

            var headerCount: Int = mHeaderPositions.size
            if (headerCount > 0) {
                // Remove headers.
                for (i in positionStart + itemCount - 1 downTo positionStart) {
                    val index: Int = findHeaderIndex(i)
                    if (index != -1) {
                        mHeaderPositions.removeAt(index)
                        headerCount--
                    }
                }

                // Remove hover header immediately if the entry it represents has been removed. A layout will follow.
                if (mHover != null && !mHeaderPositions.contains(mHoverPosition)) {
                    scrapHover(null)
                }

                // Shift headers below up.
                var i: Int = findHeaderIndexOrNext(positionStart + itemCount)
                while (i != -1 && i < headerCount) {
                    mHeaderPositions.set(i, mHeaderPositions.get(i) - itemCount)
                    i++
                }
            }
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            LogUtils.logMethod()
            Log.i("kcc", "onItemRangeMoved", Exception())

            // Shift moved headers by toPosition - fromPosition.
            // Shift headers in-between by itemCount (reverse if downwards).
            val headerCount: Int = mHeaderPositions.size
            if (headerCount > 0) {
                val topPosition = Math.min(fromPosition, toPosition)
                var i: Int = findHeaderIndexOrNext(topPosition)
                while (i != -1 && i < headerCount) {
                    val headerPos: Int = mHeaderPositions.get(i)
                    var newHeaderPos = headerPos
                    if (headerPos >= fromPosition && headerPos < fromPosition + itemCount) {
                        newHeaderPos += toPosition - fromPosition
                    } else if (fromPosition < toPosition && headerPos >= fromPosition + itemCount && headerPos <= toPosition) {
                        newHeaderPos -= itemCount
                    } else if (fromPosition > toPosition && headerPos >= toPosition && headerPos <= fromPosition) {
                        newHeaderPos += itemCount
                    } else {
                        break
                    }
                    if (newHeaderPos != headerPos) {
                        mHeaderPositions.set(i, newHeaderPos)
                        sortHeaderAtIndex(i)
                    } else {
                        break
                    }
                    i++
                }
            }
        }

        private fun sortHeaderAtIndex(index: Int) {
            LogUtils.logMethod()
            Log.i("kcc", "sortHeaderAtIndex", Exception())
            val headerPos: Int = mHeaderPositions.removeAt(index)
            val headerIndex: Int = findHeaderIndexOrNext(headerPos)
            if (headerIndex != -1) {
                mHeaderPositions.add(headerIndex, headerPos)
            } else {
                mHeaderPositions.add(headerPos)
            }
        }
    }
}