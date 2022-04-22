package com.github.kc.brv

import android.annotation.SuppressLint
import android.util.NoSuchPropertyException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.github.kc.brv.item.ItemBind
import com.github.kc.brv.listener.throttleClick
import com.github.kc.brv.util.BRV
import java.lang.reflect.Modifier

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
//            ?:
//            interfacePool?.run {
//                for (interfaceType in this) {
//                    if (interfaceType.key.isAssignableFrom(modelClass)) {
//                        return@run interfaceType.value.invoke(model, position)
//                    }
//                }
//                null
//            }
        ?: throw NoSuchPropertyException("please add item model type : addType<${model.javaClass.name}>(R.layout.item)"))
    }

    fun ViewGroup.getView(@LayoutRes layout: Int): View {
        return LayoutInflater.from(context).inflate(layout, this, false)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.bind(getModel(position))
    }

    override fun getItemCount(): Int = headerCount + modelCount + footerCount

    fun <M> getModel(@IntRange(from = 0) position: Int): M {
        return when {
            isHeader(position) -> headers[position] as M
            isFooter(position) -> footers[position - headerCount - modelCount] as M
            else -> models!!.let { it[position - headerCount] as M }
        }
    }


    // 类型池
    val typePool = mutableMapOf<Class<*>, Any.(Int) -> Int>()

    inline fun <reified M> addType(@LayoutRes layout : Int) {
        if (Modifier.isInterface(M::class.java.modifiers)) {
//            M::class.java.add
        } else {
            typePool[M::class.java] = {
                layout
            }
        }
    }

    fun Class<*>.addInterfaceType(block : Any.(Int) -> Int) {

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


    private val clickListeners =  HashMap<Int, Pair< (BindingViewHolder.(Int) -> Unit)?, Boolean>>()
    fun @receiver:androidx.annotation.IdRes Int.onClick(listener : BindingViewHolder.(viewId : Int) -> Unit) {
        clickListeners[this] = Pair(listener, false)
    }




    inner class BindingViewHolder : RecyclerView.ViewHolder {
        lateinit var _data: Any private set
        private var viewDataBinding: ViewDataBinding? = null

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

//            if (model is ItemPosition) {
//                model.itemPosition = modelPosition
//            }

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

        /**
         * 返回数据模型
         */
        fun <M> getModel(): M = _data as M
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
//            if (item is ItemExpand) {
//                item.itemGroupPosition = index
//                var nextDepth = depth
//                if (expand != null && depth != 0) {
//                    item.itemExpand = expand
//                    if (depth > 0) nextDepth -= 1
//                }
//
//                val itemSublist = item.itemSublist
//                if (!itemSublist.isNullOrEmpty() && (item.itemExpand || (depth != 0 && expand != null))) {
//                    val nestedList = flat(ArrayList(itemSublist), expand, nextDepth)
//                    list.addAll(nestedList)
//                }
//            }
        }
        return list
    }

}