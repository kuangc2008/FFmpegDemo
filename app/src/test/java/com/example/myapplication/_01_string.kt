package com.example.myapplication

import org.junit.Test


class _01_string {
    @Test
    fun a() {
//        val a = intArrayOf(
//            1, 2, 3, 4, 5, 6, 7
//        )
//        rotate2(a, 3)
//
//        a.forEach {
//            println(it)
//        }

//        val s = "Let's take LeetCode contest"
//        val reverseWords = reverseWords(s)
//        println(reverseWords)

        val head = ListNode(2, ListNode(3, ListNode(4, ListNode(5, ListNode(6, ListNode(7))))))
//        val middleNode = middleNode2(head)
//        println(middleNode?.value)

//        removeNthFromEnd(head, 3)
//
//        var node : ListNode? = head
//        while (node != null) {
//            println(node.value)
//            node = node?.next
//        }

//        val lengthOfLongestSubstring = lengthOfLongestSubstring(" ")
//        println(lengthOfLongestSubstring)

//        val checkInclusion = checkInclusion("ab", "eidboaoo")
//        println(checkInclusion)


//        val intArrayOf = intArrayOf(4, 1, 2, 3, 3, 2, 1)
//        val singleNumber = singleNumber(intArrayOf)
//        println(singleNumber)

//        reverseBits(1210061376)
//        isPowerOfTwo(16)

        val arrayListOf = arrayListOf<List<Int>>()


        var arrayListOf1 = arrayListOf<Int>()
        arrayListOf1.add(2)
        arrayListOf.add(arrayListOf1)

        arrayListOf1 = arrayListOf<Int>()
        arrayListOf1.add(3)
        arrayListOf1.add(4)
        arrayListOf.add(arrayListOf1)

         arrayListOf1 = arrayListOf<Int>()
        arrayListOf1.add(6)
        arrayListOf1.add(5)
        arrayListOf1.add(7)
        arrayListOf.add(arrayListOf1)

         arrayListOf1 = arrayListOf<Int>()
        arrayListOf1.add(4)
        arrayListOf1.add(1)
        arrayListOf1.add(8)
        arrayListOf1.add(3)
        arrayListOf.add(arrayListOf1)

        val minimumTotal = minimumTotal2(arrayListOf)
        println(minimumTotal)

    }

    fun rob(nums: IntArray): Int {

    }

    fun minimumTotal2(triangle: List<List<Int>>): Int {
        val f = Array(triangle.size) { IntArray(triangle.size) }

        f[0][0] = triangle[0][0]

        for (i in 1..triangle.lastIndex) {
            f[i][0] = f[i-1][0] + triangle[i][0]
            val list = triangle[i]

            for (j in 1 until i) {
                f[i][j] = Math.min(  f[i-1][j-1],  f[i-1][j] ) + triangle[i][j]
            }

            f[i][i] = f[i-1][i - 1] + triangle[i][i]
        }

        var maxItem = f[f.lastIndex][0]
        for (item in f[f.lastIndex]) {
            if (item < maxItem) {
                maxItem = item
            }
        }

        return maxItem
    }


    fun minimumTotal(triangle: List<List<Int>>): Int {
        val result = arrayListOf<List<Int>>()

        triangle.forEachIndexed { index , items ->
            val resultItems = ArrayList<Int>(items.size)

            items.forEachIndexed { itemsIndex, item ->
                if (index == 0) {
                    resultItems.add(item)
                } else {
                    val list = result[index - 1]
                    var lastMaxItem : Int
                    if (itemsIndex == 0) {
                        lastMaxItem = list[0]
                    } else if (itemsIndex == items.lastIndex) {
                        lastMaxItem = list[list.lastIndex]
                    } else {
                        lastMaxItem = Math.min( list[itemsIndex] , list[itemsIndex -1] )
                    }
                    resultItems.add( lastMaxItem + item  )
                }
            }
            result.add( resultItems )
        }

        val list = result[result.lastIndex]
//        val maxItem = list.maxOf {
//            it
//        }
        var maxItem = list[0]
        for (item in list) {
            if (item < maxItem) {
                maxItem = item
            }
        }

        return maxItem
    }



    fun climbStairs(n: Int): Int {
        val intArray = IntArray(n)
        intArray[0] = 1
        intArray[1] = 2

        for (i in 2 .. intArray.lastIndex) {
            intArray[i] = intArray[i - 1] + intArray[i - 2]
        }

        return intArray[n - 1]
    }

    fun isPowerOfTwo(n: Int): Boolean {
        var n = n
        while (n != 0) {
            if (n == 1) {
                return true
            } else if (n and 1 == 1) {
                return false
            }
            n = n shr 1
        }
        return true
    }

    fun hammingWeight(n:Int):Int {
        var count = 0
        var n = n
        for (i in 0 .. 31) {
            if (n and 1 == 1) {
                count++
            }
            n = n ushr 1
        }
        return count
    }

    // you need treat n as an unsigned value
    fun reverseBits(n:Int):Int {


        var result = 0
        var n = n
        for (i in 0 .. 31) {
            if (n < 0) break

            result = result or (n and 1 shl (31 - i))
            n = n ushr 1
        }
        return result
    }

    fun wordPattern(pattern: String, s: String): Boolean {
        val strings = s.split(' ')
        if (pattern.length != strings.size)    {
            return false
        }

        val char2String = hashMapOf<Char, String>()

        pattern.forEachIndexed {index, c ->
            if (char2String.containsKey(c)) {
                if (strings[index] != char2String[c]) {
                    return false
                }
            }
            char2String[c] = strings[index]
        }


        val string2char = hashMapOf<String, Char>()
        strings.forEachIndexed { index, s ->
            if (string2char.containsKey(s)) {
                if (string2char[s] != pattern[index]) {
                    return false
                }
            }
            string2char[s] = pattern[index]
        }

        return true
    }



    fun singleNumber(nums: IntArray): Int {
        var result = nums[0]
        for (i in 1 .. nums.lastIndex) {
            result = result xor nums[i]
        }
        return result
    }


//    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
//        return Array<IntArray>(3)
//    }

    fun checkInclusion(s1: String, s2: String): Boolean {
        val s1Map = hashMapOf<Char, Int>()
        val s2Map = hashMapOf<Char, Int>()
        s1.forEach {
            s1Map[it] = (s1Map[it] ?: 0) + 1
        }

        var valid = 0
        var start = 0
        s2.forEachIndexed { index, c ->
            s2Map[c] = (s2Map[c] ?: 0) + 1
            if (s1Map[c] == s2Map[c]) {
                valid++
            }

            if (index - start + 1 == s1.length) {
                val startC = s2[start]
                if (valid == s1Map.size) {
                    return true
                }

                if (s2Map[startC] == s1Map[startC]) {
                    valid--
                }
                s2Map[startC] = (s2Map[startC] ?: 0) - 1
                start++
            }
        }

        return false
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.isEmpty()) {
            return 0
        }
        val map = hashMapOf<Char, Int>()
        var start = 0
        var lenght = 0
        s.forEachIndexed { index, c ->
            if (map.containsKey(c)) {
                val cIndex = map[c]!!
                if (start <= cIndex) {
                    lenght = max(index - start, lenght)
                    start = cIndex + 1
                }
            }

            map[c] = index
        }

        return max(lenght, s.lastIndex - start + 1)
    }

    fun max(item1:Int, item2:Int) = if (item1 > item2) item1 else item2

    class ListNode(var value : Int = 0, var next : ListNode? = null)


    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val count = getNodeCount(head)

        if (n > count) {
            return head
        }

        if (n == count) {
            return head?.next
        }

        var node = head
        for (i in 1 until count - n) {
            node = node?.next
        }

        node?.next = node?.next?.next

        return head
    }

    fun getNodeCount(head : ListNode?) : Int {
        var count = 0
        var node = head
        while (node != null) {
            node = node.next
            count++
        }
        return count
    }


    fun middleNode2(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }

        var count = 0
        var node = head
        while (node != null) {
            node = node.next
            count++
        }

        val preSize = (count + 1) /2
        var firstNode = head
        for (i in preSize downTo 0) {
            firstNode = firstNode?.next
        }
        return firstNode
    }

    /**   876
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。

    如果有两个中间结点，则返回第二个中间结点。
     */
    fun middleNode(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }

        var count = 0
        var node = head
        while (node != null) {
            node = node.next
            count++
        }

        val preSize = (count - 1) /2
        var firstNode = head
        var secondNode = head
        for (i in preSize downTo 0) {
            firstNode = firstNode?.next
        }

        while (firstNode != null) {
            firstNode = firstNode?.next
            secondNode  = secondNode?.next
        }

        return secondNode
    }



    /** 557
     * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

     

    示例 1：

    输入：s = "Let's take LeetCode contest"
    输出："s'teL ekat edoCteeL tsetnoc"

    来源：力扣（LeetCode）
    链接：https://leetcode.cn/problems/reverse-words-in-a-string-iii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun reverseWords(s: String): String {
        val sb = StringBuilder()

        var start = 0
        s.forEachIndexed { index, c ->
            if (c == ' ') {
                if (index > start) {
                    // 反转
                    for (i in index - 1 downTo start) {
                        sb.append(s[i])
                    }
                }
                sb.append(' ')
                start = index + 1
            } else if (index == s.length -1) {
                if (index >= start) {
                    // 反转
                    for (i in index downTo start) {
                        sb.append(s[i])
                    }
                }
            }
        }
        return sb.toString()
    }



    /** 344
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。

    不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

    来源：力扣（LeetCode）
    链接：https://leetcode.cn/problems/reverse-string
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun reverseString(s: CharArray): Unit {
        for (i in 0..s.size/2) {
            val tmp = s[i]
            s[i] = s[s.lastIndex - i]
            s[s.lastIndex - i ] = tmp
        }
    }


    /**  167
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。

    以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。

    你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。

    你所设计的解决方案必须只使用常量级的额外空间。

    来源：力扣（LeetCode）
    链接：https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        val map = HashMap<Int, Int>()
        numbers.forEachIndexed { index, i ->
            if (map.containsKey(i)) {
                return intArrayOf( map[i]!!, index )
            }
            map[target - i] = index
        }
        return IntArray(0)
    }


    /** 283
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

    请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    fun moveZeroes2(nums: IntArray) {
        var start = 0
        for (i in nums.indices) {
            if (nums[i] != 0) {
                if (i != start) {
                    nums[start] = nums[i]
                }
                start++
            }
        }

        for (i in start..nums.size -1 ) {
            nums[i] = 0
        }
    }


    fun moveZeroes(nums: IntArray) {
        for (i in nums.indices.reversed()) {
            if (nums[i] == 0) {
                moveZeroEnd(nums, i)
            }
        }
    }

    fun moveZeroEnd(nums: IntArray, zeroPos: Int) {
        for (i in zeroPos..nums.size - 2) {
            nums[i] = nums[i + 1]
        }
        nums[nums.size - 1] = 0
    }

    /**  189
     * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

     

    示例 1:

    输入: nums = [1,2,3,4,5,6,7], k = 3
    输出: [5,6,7,1,2,3,4]
    解释:
    向右轮转 1 步: [7,1,2,3,4,5,6]
    向右轮转 2 步: [6,7,1,2,3,4,5]
    向右轮转 3 步: [5,6,7,1,2,3,4]
     */
    fun rotate(nums: IntArray, k: Int): Unit {
        val n = nums.size
        val newArr = IntArray(n)
        for (i in 0 until n) {
            newArr[(i + k) % n] = nums[i]
        }
        System.arraycopy(newArr, 0, nums, 0, newArr.size)
    }

    fun rotate2(nums: IntArray, k: Int) {
        reverse(nums, 0, nums.size - 1)
        reverse(nums, 0, k - 1)
        reverse(nums, k, nums.size - 1)
    }

    fun reverse(nums: IntArray, start: Int, end: Int) {
        var start = start
        var end = end
        while (start < end) {
            val tmp = nums[start]
            nums[start] = nums[end]
            nums[end] = tmp
            start++
            end--
        }
    }


    @Test
    fun sortedSquares() {
        val nums = intArrayOf(-4, -3, -2, 0, 1, 5, 9)
        val sortedSquaresInternal = sortedSquaresInternal(nums)

        sortedSquaresInternal.forEach {
            println(it)
        }

        3.dp
        4.dp()
    }

    fun sortedSquaresInternal(nums: IntArray) : IntArray {
        val newNums = IntArray(nums.size)

        var start = 0
        var end = nums.size -1

        var newNumsPos : Int = end

        while (start <= end && newNumsPos >= 0) {

            when {
                nums[start] >= 0 -> {
                    newNums[newNumsPos] = nums[end] * nums[end]
                    end--
                }
                nums[end] <= 0 -> {
                    newNums[newNumsPos] = nums[start] * nums[start]
                    start ++
                }
                nums[start] * nums[start] > nums[end] * nums[end] -> {
                    newNums[newNumsPos] = nums[start] * nums[start]
                    start ++
                }
                else -> {
                    newNums[newNumsPos] = nums[end] * nums[end]
                    end--
                }
            }
            newNumsPos--
        }

        return newNums
    }



    @Test
    fun firstBadVersionTest() {
        val firstBadVersion = firstBadVersion(2126753390)
        println(firstBadVersion)
    }

    fun firstBadVersion(n: Int) : Int {
        var start = 1
        var end =  n

        while (start < end) {
            val middle = start + (end - start) /2
            if (isBadVersion(middle)) {
                end = middle
            } else {
                start = middle + 1
            }
        }
        return start
    }


    fun isBadVersion(version : Int): Boolean {
        if (version <= 1702766719 - 1) {
            return false
        }
        return true
    }

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