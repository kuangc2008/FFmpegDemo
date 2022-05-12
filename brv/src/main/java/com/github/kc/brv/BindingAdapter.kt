package com.github.kc.brv

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.util.NoSuchPropertyException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.kc.brv.animation.AlphaItemAnimation
import com.github.kc.brv.animation.ItemAnimation
import com.github.kc.brv.item.ItemBind
import com.github.kc.brv.item.ItemExpand
import com.github.kc.brv.item.ItemHover
import com.github.kc.brv.item.ItemPosition
import com.github.kc.brv.listener.OnHoverAttachListener
import com.github.kc.brv.listener.throttleClick
import com.github.kc.brv.util.BRV
import java.lang.reflect.Modifier
import kotlin.math.min

open class BindingAdapter : RecyclerView.Adapter<BindingAdapter.BindingViewHolder>() {



    companion object {
        /** 是否启用DataBinding */
        private val dataBindingEnable: Boolean by lazy {
            try {
                Class.forName("androidx.databinding.DataBindingUtil")
                true
            } catch (e: Throwable) {
                false
            }
        }
    }


    /** 当前Adapter被setAdapter才不为null */
    var rv: RecyclerView? = null

    private var lastPosition = -1
    private var isFirst = true

    /** 防抖动点击事件的间隔时间, 单位毫秒 */
    var clickThrottle: Long = BRV.clickThrottle

    /** 头布局的数据模型 */
    var headers: List<Any?> = mutableListOf()
        set(value) {
            field = value.toMutableList()
            notifyDataSetChanged()
        }

    /** 头布局数量 */
    val headerCount: Int get() = headers.size

    fun isHeader(@IntRange(from = 0) position: Int): Boolean =
        (headerCount > 0 && position < headerCount)


    /**
     * 全部脚布局数据集合
     */
    var footers: List<Any?> = mutableListOf()
        set(value) {

            field = value.toMutableList()
            notifyDataSetChanged()

            if (isFirst) {
                lastPosition = -1
                isFirst = false
            } else {
                lastPosition = itemCount - 1
            }
        }

    /**
     * 脚布局数量
     */
    val footerCount: Int get() = footers.size

    fun isFooter(@IntRange(from = 0) position: Int): Boolean =
        (footerCount > 0 && position >= headerCount + modelCount && position < itemCount)


    var _data: List<Any?>? = null
    /** 数据模型集合 */
    var models: List<Any?>?
        get() = _data
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            _data = when (value) {
                is ArrayList -> flat(value)
                is List -> flat(value.toMutableList())
                else -> null
            }
            notifyDataSetChanged()
//            checkedPosition.clear()
//            if (isFirst) {
//                lastPosition = -1
//                isFirst = false
//            } else {
//                lastPosition = itemCount - 1
//            }
        }

    /** 数据模型数量(不包含头布局和脚布局) */
    val modelCount: Int
        get() {
            return if (models == null) 0 else models!!.size
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val vh = if (dataBindingEnable) {
            val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
            if (viewDataBinding == null) {
                BindingViewHolder(parent.getView(viewType))
            } else BindingViewHolder(
                viewDataBinding
            )
        } else {
            BindingViewHolder(parent.getView(viewType))
        }
        onCreate?.invoke(vh, viewType)
        return vh
    }

    override fun getItemViewType(position: Int): Int {

        val model = getModel<Any>(position)
        val modelClass: Class<*> = model.javaClass
        return (typePool[modelClass]?.invoke(model, position)
            ?:
            interfacePool?.run {
                for (interfaceType in this) {
                    if (interfaceType.key.isAssignableFrom(modelClass)) {
                        return@run interfaceType.value.invoke(model, position)
                    }
                }
                null
            }
        ?: throw NoSuchPropertyException("please add item model type : addType<${model.javaClass.name}>(R.layout.item)"))
    }

    fun ViewGroup.getView(@LayoutRes layout: Int): View {
        return LayoutInflater.from(context).inflate(layout, this, false)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.bind(getModel(position))
    }

    override fun getItemCount(): Int = headerCount + modelCount + footerCount

//    /** 自定义ItemTouchHelper即可设置该属性 */
//    var itemTouchHelper: ItemTouchHelper? = ItemTouchHelper(DefaultItemTouchCallback())
//        set(value) {
//            if (value == null) field?.attachToRecyclerView(null) else value.attachToRecyclerView(rv)
//            field = value
//        }


    private var context: Context? = null
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.rv = recyclerView
        if (context == null) {
            context = recyclerView.context
        }
//        itemTouchHelper?.attachToRecyclerView(recyclerView)
    }

    override fun onViewAttachedToWindow(holder: BindingViewHolder) {
        super.onViewAttachedToWindow(holder)
        val layoutPosition = holder.layoutPosition
        if (animationEnabled && lastPosition < layoutPosition) {
            itemAnimation.onItemEnterAnimation(holder.itemView)
            lastPosition = layoutPosition
        }
    }

    fun <M> getModel(@IntRange(from = 0) position: Int): M {
        return when {
            isHeader(position) -> headers[position] as M
            isFooter(position) -> footers[position - headerCount - modelCount] as M
            else -> models!!.let { it[position - headerCount] as M }
        }
    }



    // <editor-fold desc="列表动画">
    private var itemAnimation: ItemAnimation = AlphaItemAnimation()
    /** 是否启用条目动画 */
    var animationEnabled = false




    inline fun <reified M> getModelOrNull(position: Int): M? {
        return when {
            isHeader(position) -> headers[position] as? M
            isFooter(position) -> footers[position - headerCount - modelCount] as? M
            else -> models?.let { it.getOrNull(position - headerCount) as? M }
        }
    }


    // 类型池
    val typePool = mutableMapOf<Class<*>, Any.(Int) -> Int>()
    var interfacePool : MutableMap<Class<*>, Any.(Int) -> Int>? = null

    inline fun <reified M> addType(@LayoutRes layout : Int) {
        if (Modifier.isInterface(M::class.java.modifiers)) {
            M::class.java.addInterfaceType { layout }
        } else {
            typePool[M::class.java] = {  position ->
                Log.i("kcc", "position:: $position")
                layout
            }
        }
    }

    fun Class<*>.addInterfaceType(block : Any.(Int) -> Int) {
        (interfacePool ?: mutableMapOf<Class<*>, Any.(Int) -> Int>().also {
            interfacePool = it
        })[this]  = block
    }


    // <editor-fold desc="生命周期">
    private var onCreate: (BindingViewHolder.(viewType: Int) -> Unit)? = null
    private var onBind: (BindingViewHolder.() -> Unit)? = null
    private var onPayload: (BindingViewHolder.(model: Any) -> Unit)? = null
    private var onClick: (BindingViewHolder.(viewId: Int) -> Unit)? = null
    private var onLongClick: (BindingViewHolder.(viewId: Int) -> Unit)? = null
    private var onChecked: ((position: Int, checked: Boolean, allChecked: Boolean) -> Unit)? = null
    private var onToggle: ((position: Int, toggleModel: Boolean, end: Boolean) -> Unit)? = null


    /**
     * [onBindViewHolder]执行时回调
     */
    fun onBind(block: BindingViewHolder.() -> Unit) {
        onBind = block
    }

    /**
     * [onCreateViewHolder]执行时回调
     */
    fun onCreate(block: BindingViewHolder.(viewType: Int) -> Unit) {
        onCreate = block
    }

    /**
     * 增量更新回调
     * 当你使用[notifyItemChanged(int, Object)]或者[notifyItemRangeChanged(int, Object)]等方法更新列表时才会触发, 并且形参payload要求不能为null
     *
     * @param block 形参model即为[notifyItemChanged]中的形参payload
     */
    fun onPayload(block: BindingViewHolder.(model: Any) -> Unit) {
        onPayload = block
    }

    // </editor-fold>

    fun onClick(@IdRes vararg id: Int, block: BindingViewHolder.(viewId: Int) -> Unit) {
        for (i in id) {
            clickListeners[i] = Pair(block, false)
        }
        onClick = block
    }



    private val clickListeners =  HashMap<Int, Pair< (BindingViewHolder.(Int) -> Unit)?, Boolean>>()
    fun @receiver:androidx.annotation.IdRes Int.onClick(listener : BindingViewHolder.(viewId : Int) -> Unit) {
        clickListeners[this] = Pair(listener, false)
    }

    fun @receiver:IdRes Int.onFastClick(listener: BindingViewHolder.(viewId: Int) -> Unit) {
        clickListeners[this] = Pair(listener, true)
    }


    //<editor-fold desc="分组">

    private var previousExpandPosition = -1
    private var onExpand: (BindingViewHolder.(Boolean) -> Unit)? = null

    /** 分组展开和折叠是否启用动画 */
    var expandAnimationEnabled = true

    /** 只允许一个条目展开(展开当前条目就会折叠上个条目) */
    var singleExpandMode = false

    /** 监听展开分组 */
    fun onExpand(block: BindingViewHolder.(Boolean) -> Unit) {
        this.onExpand = block
    }

    /**
     * 判断两个位置的item是否属于同一分组下, 要求这两个位置的item都展开才有效
     * 如果其中一个item都属于根节点则返回-1, 这种情况不算属于同一分组下
     */
    fun isSameGroup(
        @IntRange(from = 0) position: Int,
        @IntRange(from = 0) otherPosition: Int,
    ): Boolean {
        val aModel = models?.getOrNull(otherPosition) ?: return false
        val bModel = models?.getOrNull(otherPosition) ?: return false
        for (index in min(position, otherPosition) - 1 downTo 0) {
            val item = models?.getOrNull(index) ?: break
            if (item is ItemExpand && item.itemSublist?.contains(aModel) == true
                && item.itemSublist?.contains(bModel) == true
            ) {
                return true
            }
        }
        return false
    }

    /**
     * 展开
     * @param position 指定position的条目折叠
     * @param scrollTop 展开同时当前条目滑动到顶部
     * @param depth 递归展开子项的深度, 如等于-1则代表展开所有子项, 0表示仅展开当前
     * @return 展开后增加的条目数量
     */
    fun expand(
        @IntRange(from = 0) position: Int,
        scrollTop: Boolean = false,
        @IntRange(from = -1) depth: Int = 0,
    ): Int {
        val holder = rv?.findViewHolderForLayoutPosition(position) as? BindingViewHolder ?: rv?.run {
            val holder = createViewHolder(this, getItemViewType(position))
            bindViewHolder(holder, position)
            holder
        } ?: return 0
        return holder.expand(scrollTop, depth)
    }

    /**
     * 折叠
     * @param position 指定position的条目折叠
     * @param depth 递归展开子项的深度, 如等于-1则代表展开所有子项, 0表示仅展开当前
     * @return 折叠后消失的条目数量
     */
    fun collapse(@IntRange(from = 0) position: Int, @IntRange(from = -1) depth: Int = 0): Int {
        val holder = rv?.findViewHolderForLayoutPosition(position) as? BindingViewHolder ?: rv?.run {
            val holder = createViewHolder(this, getItemViewType(position))
            bindViewHolder(holder, position)
            holder
        } ?: return 0
        return holder.collapse(depth)
    }

    /**
     * 展开或折叠
     * @param scrollTop 展开同时当前条目滑动到顶部
     * @param depth 递归展开子项的深度, 如等于-1则代表展开所有子项, 0表示仅展开当前
     * @return 展开或折叠后变动的条目数量
     */
    fun expandOrCollapse(
        @IntRange(from = 0) position: Int,
        scrollTop: Boolean = false,
        @IntRange(from = -1) depth: Int = 0,
    ): Int {
        val holder = rv?.findViewHolderForLayoutPosition(position) as? BindingViewHolder ?: rv?.run {
            val holder = createViewHolder(this, getItemViewType(position))
            bindViewHolder(holder, position)
            holder
        } ?: return 0
        return holder.expandOrCollapse(scrollTop, depth)
    }

    //</editor-fold>


    inner class BindingViewHolder : RecyclerView.ViewHolder {
        lateinit var _data: Any private set
        private var viewDataBinding: ViewDataBinding? = null

        val adapter: BindingAdapter = this@BindingAdapter

        val modelPosition get() = layoutPosition - headerCount

        constructor(itemView: View) : super(itemView)

        constructor(viewDataBinding: ViewDataBinding) : super(viewDataBinding.root) {
            this.viewDataBinding = viewDataBinding
        }

        fun <V : View> findView(@IdRes id: Int): V = itemView.findViewById(id)


        init {
            for (clickListener in clickListeners) {
                val view = itemView.findViewById<View>(clickListener.key) ?: continue
                if (clickListener.value.second) {
                    view.setOnClickListener {
                        (clickListener.value.first ?: onClick)?.invoke(this, it.id)
                    }
                } else {
                    view.throttleClick(clickThrottle) {
                        (clickListener.value.first ?: onClick)?.invoke(this@BindingViewHolder, id)
                    }
                }
            }
//            for (longClickListener in longClickListeners) {
//                val view = itemView.findViewById<View>(longClickListener.key) ?: continue
//                view.setOnLongClickListener {
//                    (longClickListener.value ?: onLongClick)?.invoke(this, it.id)
//                    true
//                }
//            }
        }



        internal fun bind(model: Any) {
            this._data = model

//            onBindViewHolders.forEach {
//                it.onBindViewHolder(rv!!, adapter, this, adapterPosition)
//            }

            if (model is ItemPosition) {
                model.itemPosition = modelPosition
            }

            if (model is ItemBind) {
                model.onBind(this)
            }

            onBind?.invoke(this@BindingViewHolder)
//            try {
//                viewDataBinding?.setVariable(modelId, model)
//            } catch (e: Exception) {
//                val message =
//                    "${e.message} at file(${context.resources.getResourceEntryName(itemViewType)}.xml:0)"
//                Exception(message).printStackTrace()
//            }
            viewDataBinding?.executePendingBindings()
        }

        inline fun <reified M> getModelOrNull(): M? = _data as? M

        /**
         * 返回数据模型
         */
        fun <M> getModel(): M = _data as M


        //<editor-fold desc="分组">
        /**
         * 展开子项
         * @param scrollTop 展开同时当前条目滑动到顶部
         * @param depth 递归展开子项的深度, 如等于-1则代表展开所有子项, 0表示仅展开当前
         * @return 展开后新增的条目数量
         */
        fun expand(scrollTop: Boolean = true, @IntRange(from = -1) depth: Int = 0): Int {
            val itemExpand = getModelOrNull<ItemExpand>()
            if (itemExpand?.itemExpand == true) return 0

            var position = if (bindingAdapterPosition == -1) layoutPosition else bindingAdapterPosition

            if (singleExpandMode && previousExpandPosition != -1 && findParentPosition() != previousExpandPosition) {
                val collapseCount = adapter.collapse(previousExpandPosition)
                if (position > previousExpandPosition) {
                    position -= collapseCount
                }
            }

            onExpand?.invoke(this, true)

            return if (itemExpand != null && !itemExpand.itemExpand) {
                val itemSublist = itemExpand.itemSublist
                itemExpand.itemExpand = true
                previousExpandPosition = position
                if (itemSublist.isNullOrEmpty()) {
                    notifyItemChanged(position)
                    0
                } else {
                    val sublistFlat = flat(ArrayList(itemSublist), true, depth)

                    (this@BindingAdapter.models as MutableList).addAll(position + 1, sublistFlat)
                    if (expandAnimationEnabled) {
                        notifyItemChanged(position)
                        notifyItemRangeInserted(position + 1, sublistFlat.size)
                    } else {
                        notifyDataSetChanged()
                    }
                    if (scrollTop) {
                        rv?.postDelayed({ rv?.smoothScrollToPosition(position) }, 200)
                    }
                    sublistFlat.size
                }
            } else {
                0
            }
        }

        /**
         * 折叠子项
         * @param depth 递归折叠子项的深度, 如等于-1则代表展开所有子项, 0表示仅展开当前
         * @return 折叠后减少的条目数量
         */
        fun collapse(@IntRange(from = -1) depth: Int = 0): Int {
            val itemExpand = getModelOrNull<ItemExpand>()

            if (itemExpand?.itemExpand == false) return 0
            val position = if (bindingAdapterPosition == -1) layoutPosition else bindingAdapterPosition

            onExpand?.invoke(this, false)

            return if (itemExpand != null && itemExpand.itemExpand) {
                val itemSublist = itemExpand.itemSublist
                itemExpand.itemExpand = false

                if (itemSublist.isNullOrEmpty()) {
                    notifyItemChanged(position, itemExpand)
                    0
                } else {
                    val sublistFlat = flat(ArrayList(itemSublist), false, depth)
                    (this@BindingAdapter.models as MutableList).subList(position + 1, position + 1 + sublistFlat.size).clear()
                    if (expandAnimationEnabled) {
                        notifyItemChanged(position, itemExpand)
                        notifyItemRangeRemoved(position + 1, sublistFlat.size)
                    } else {
                        notifyDataSetChanged()
                    }
                    sublistFlat.size
                }
            } else {
                0
            }
        }

        /**
         * 展开或折叠子项
         * @param scrollTop 展开同时当前条目滑动到顶部
         * @param depth 递归展开子项的深度, 如等于-1则代表展开所有子项, 0表示仅展开当前
         * @return 展开|折叠后变动的条目数量
         */
        fun expandOrCollapse(scrollTop: Boolean = false, @IntRange(from = -1) depth: Int = 0): Int {
            val itemExpand = getModelOrNull<ItemExpand>()
            return if (itemExpand != null) {
                if (itemExpand.itemExpand) collapse(depth) else expand(scrollTop, depth)
            } else 0
        }

        /**
         * 查找分组中的父项位置
         * @return -1 表示不存在父项
         */
        fun findParentPosition(): Int {
            for (index in layoutPosition - 1 downTo 0) {
                val item = models?.getOrNull(index) ?: break
                if (item is ItemExpand && item.itemSublist?.contains(_data) == true) {
                    return index
                }
            }
            return -1
        }

        /**
         * 查找分组中的父项ViewHolder
         * @return null表示不存在父项
         */
        fun findParentViewHolder(): BindingViewHolder? {
            return rv?.findViewHolderForLayoutPosition(findParentPosition()) as? BindingViewHolder
        }

        //</editor-fold>
    }



    /**
     * 扁平化数据. 将折叠分组铺平展开创建列表
     */
    private fun flat(
        list: MutableList<Any?>,
        expand: Boolean? = null,
        @IntRange(from = -1) depth: Int = 0,
    ): MutableList<Any?> {

        if (list.isEmpty()) return list
        val arrayList = ArrayList(list)
        list.clear()

        arrayList.forEachIndexed { index, item ->
            list.add(item)
            if (item is ItemExpand) {
                item.itemGroupPosition = index
                var nextDepth = depth
                if (expand != null && depth != 0) {
                    item.itemExpand = expand
                    if (depth > 0) nextDepth -= 1
                }

                val itemSublist = item.itemSublist
                if (!itemSublist.isNullOrEmpty() && (item.itemExpand || (depth != 0 && expand != null))) {
                    val nestedList = flat(ArrayList(itemSublist), expand, nextDepth)
                    list.addAll(nestedList)
                }
            }
        }
        return list
    }

    //<editor-fold desc="悬停">
    var hoverEnabled = true
    /**
     * 监听开始悬停
     */
    var onHoverAttachListener: OnHoverAttachListener? = null

    fun isHover(position: Int) : Boolean {
        val model = getModelOrNull<ItemHover>(position)
        return model != null && model.itemHover && hoverEnabled
    }
    //</editor-fold>
}