package com.example.myapplication

import org.junit.Test
import kotlin.math.max


class _01_string {

    /**
     * 已知：
    给定一个字符串 s，求 字符串 s 不含有重复字符的 最长子串 的长度。

    如输入："abcabcbb" 应输出："3" ，最长子串为abc
     */
    @Test
    fun getLongestSubStrLen() {
        /** aabcdfdfdffdabcabc **/
        /* abcdefghyt */
        val longestSubStrLenInternal = getLongestSubStrLenInternal("aabcdfdfdffdabcabc")
        println(longestSubStrLenInternal)

        val longestSubStrLenInternal2 = getLongestSubStrLenInternal2("aabcdfdfdffdabcabc")
        println(longestSubStrLenInternal2)
    }


    /**
     *  方法二  滑动窗口
     */
    private fun getLongestSubStrLenInternal2(s: String): Int {

        var start = 0
        var max = 0
        val hashMap = HashMap<Char, Int>()

        s.forEachIndexed { end, c ->
            if (hashMap.containsKey(c)) {
                val oldStart : Int= hashMap[c] ?: 0
                if (oldStart >= start) {
                    val length = end - start
                    max = max(length, max)
                    start = oldStart + 1
                }
            }
            hashMap[c] = end
        }

        max = max(s.length - start, max)
        return max
    }

    /**
     * 方法1，暴力，双循环
     */
    private fun getLongestSubStrLenInternal(s: String): Int {
        if (s.length == 1){
            return 1;
        }

        val hashSet = HashSet<Char>()
        var maxLen = 0
        var currentLen = 0


        s.forEachIndexed { index, c ->
            hashSet.clear()
            hashSet.add(c)
            currentLen = 1

            for (i in index+1 until  s.length) {
                if (hashSet.contains(s[i])) {
                    break
                } else {
                    hashSet.add(s[i])
                    currentLen++
                }
            }
            maxLen = Math.max(maxLen, currentLen)
        }
        return maxLen
    }
}