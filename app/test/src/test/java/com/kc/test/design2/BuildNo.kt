package com.kc.test.design2


/**
 * 构建者为了解决什么问题？
 *    参数是可读的，人性化的
 *
 * 问题！
 *     多一个builder对象，最后需要build
 *
 * kotlin的可选参数，可以解决构建者需要解决的问题，同时避免它的不足
 *
 */
class BuildNo (
    val code :String,
    val battery : String? = null,
    val height : Int? = null,
    val weight : Int? = null
) {

    // 添加约束
    init {
        println("aaaa")

        require( weight == null || battery != null ) {
            "Battery should be detemine when setting weight"
        }
    }
}