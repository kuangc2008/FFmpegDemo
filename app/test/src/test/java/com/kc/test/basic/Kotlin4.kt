package com.kc.test.basic

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import org.junit.Test
import kotlin.contracts.ExperimentalContracts

class Kotlin4 {


    // 1
    fun View.postDelayed(delayMillis: Long, runnable: Runnable) {
        postDelayed(runnable, delayMillis)
    }

    //2
    val Float.dp2px : Float
        get() =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                this,
                null
            )

    // 3 单例
    object Pro

    class Pro2 private constructor() {
        companion object {
            val INSTANCE by lazy {
                Pro2()
            }
        }
    }


    class Pro5 private constructor(private val property: Int) {//这里可以根据实际需求发生改变

        companion object {
            @Volatile private var instance: Pro5? = null
            fun getInstance(property: Int) =
                instance ?: synchronized(this) {
                    instance ?: Pro5(property).also { instance = it }
                }
        }
    }

    class Pro3 private constructor(){
        companion object {
            var singleton: Pro3? = null

            fun getInstance() : Pro3 {
                if (singleton == null) {
                    synchronized(Pro3::class.java) {
                        if (singleton == null) {
                            singleton = Pro3()
                        }
                    }
                }
                return singleton!!
            }
        }
    }

    class Pro4 private constructor() {
        companion object {
            val instance: Pro4 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
                Pro4()
            }
        }
    }


    class SingletonDemo private constructor() {
        companion object {
            private var instance: SingletonDemo? = null
                get() {
                    if (field == null) {
                        field = SingletonDemo()
                    }
                    return field
                }

            @Synchronized
            fun get(): SingletonDemo {
                //细心的小伙伴肯定发现了，这里不用getInstance作为为方法名，是因为在伴生对象声明时，内部已有getInstance方法，所以只能取其他名字
                return instance!!
            }
        }
    }


    // 4
    class CustomView @JvmOverloads constructor(
        context: Context,
        attributes: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : View(context, attributes, defStyleAttr)


    class CustomView2 : View {
        constructor(context: Context) : super(context)
        constructor(context: Context, attributes: AttributeSet? = null) : super(context, attributes)
        constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int) : super(
            context,
            attributes,
            defStyleAttr
        )
    }

//    class CustomView3 constructor(context: Context) : View(context) {
//        constructor(context: Context, attributes: AttributeSet? = null) : super(context, attributes)
//        constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int) : super(
//            context,
//            attributes,
//            defStyleAttr
//        )
//    }

    // 5
    lateinit var mContext : String

    // 6
    @JvmField
    var mData :String = ""

    @get: JvmName("getSource")
    @set: JvmName("setSource")
    var mData2 = ""

    val isSqual : Boolean = "aa".equals("bb", true)

    // 7



    @ExperimentalContracts
    @Test
    fun test1() {
        val view = View(null)
        view.postDelayed(300) {
            println("aaaa")
        }

        10f.dp2px

        if (this::mContext.isInitialized) {

        }

        mData2.isNullOrEmpty()


    }
}