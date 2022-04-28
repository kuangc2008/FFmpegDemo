package com.example.myapplication.rv.model

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import com.drake.spannable.addSpan
import com.drake.spannable.replaceSpan
import com.drake.spannable.setSpan
import kotlinx.serialization.Serializable


@Serializable
data class CommentModel (
    var name : String = "",
    var avatar : String = "",
    var content : String = "",
    var date : String = "",
    var vip : Int = 0,
    var upCount : Int = 0,
    var up : Boolean = false,
    var down : Boolean = false,
    var sb : Boolean = false,
    var commentUp : Boolean = false,
    var commentCount : Long = 0,
    var comment : List<Comment> = listOf()
) {

    fun getNameColor(): Int {
        return if (vip == 6) Color.parseColor("#ed6f98") else Color.parseColor("#62666d")
    }

    /** 显示更多子评论 */
    fun comment(): List<CharSequence> {
        val data = mutableListOf<CharSequence>()
        data.addAll(comment.map { it.getSpannable() })

        // 如果小于等于三条评论
        if (commentCount <= 3) return data

        val span = SpannableStringBuilder()

        // 如果UP参与评论
        if (commentUp) {
            span.addSpan("UP主等人 ", ForegroundColorSpan(Color.parseColor("#979ba1")))
        }

        // 如果超过三条子评论
        val more = span.addSpan("共${commentCount}条回复", ForegroundColorSpan(Color.parseColor("#3b87bf")))
        data.add(more)
        return data
    }


    @Serializable
    data class Comment(
        var name: String = "",
        var content: String = ""
    ) {

        /**
         * 因为需要换行所以使用Spannable来填充一个textView
         * 使用依赖库快速渲染评论内容 https://github.com/liangjingkanji/spannable
         */
        fun getSpannable(): CharSequence {
            return name.setSpan(ForegroundColorSpan(Color.parseColor("#3b87bf"))).addSpan(": $content").replaceSpan("@[^@]+?(?=\\s|\$)".toRegex()) {
                ForegroundColorSpan(Color.parseColor("#3b87bf"))
            }
        }
    }
}
