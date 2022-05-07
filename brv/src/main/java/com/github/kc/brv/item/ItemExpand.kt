package com.github.kc.brv.item

interface ItemExpand {

    /** 同级别的分组的索引位置 */
    var itemGroupPosition: Int

    /** 是否已展开 */
    var itemExpand: Boolean

    /** 子列表 */
    var itemSublist: List<Any?>?
}