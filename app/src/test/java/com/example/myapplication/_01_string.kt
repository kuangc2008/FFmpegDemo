package com.example.myapplication

import org.junit.Test
import java.util.*


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

//        val minimumTotal = minimumTotal2(arrayListOf)
//        println(minimumTotal)

//        val nums = intArrayOf(2,7,9,3,1)
//        val rob = rob(nums)
//        println(rob)


        val array = arrayOf(
            intArrayOf(0,0,1,0,0,0,0,1,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
            intArrayOf(0,1,1,0,1,0,0,0,0,0,0,0,0),
            intArrayOf(0,1,0,0,1,1,0,0,1,0,1,0,0),
            intArrayOf(0,1,0,0,1,1,0,0,1,1,1,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,0),
        )


        val abc = arrayOfNulls<IntArray>(2)

        abc[0] = IntArray(2)

        val bcd = Array(2) { IntArray(3) }

//        val maxAreaOfIsland = maxAreaOfIsland(array)
//        println(maxAreaOfIsland)


//        val first = TreeNode(
//            5,
//            TreeNode(4),
//            TreeNode(6,   TreeNode(3),  TreeNode(7))
//        )
//        val first = TreeNode(
//            2,
//            TreeNode(1),
//            TreeNode(3)
//        )

//        val first = TreeNode(
//            -10,
//            TreeNode(9),
//            TreeNode(20, TreeNode(15), TreeNode(7))
//        )

        val first = TreeNode(
            5,
            TreeNode(4, TreeNode(11, TreeNode(7), TreeNode(2))),
            TreeNode(8, TreeNode(13), TreeNode(4, null, TreeNode(1)))
        )


//        isValidBST(first)
        val maxPathSum = maxPathSum(first)
        println(maxPathSum)


        var result = IntArray(3)

        val second = TreeNode(
            2,
            TreeNode(1,  null, TreeNode(4)),
            TreeNode(3, null, TreeNode(7))
        )

        val first2 = TreeNode(
            3,
            TreeNode(9),
            TreeNode(20, TreeNode(15), TreeNode(7))
        )

//        val levelOrder = levelOrder(first2)
//        levelOrder.forEach {
//            it.forEach {
//                print("$it ")
//            }
//            println()
//        }

//        letterCasePermutation3("a1b2")

//        mergeTrees(first, second)


        val array1 = arrayOf (
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )
        val matrixReshape = matrixReshape(array1, 1, 4)

        val array2 = arrayOf(
            intArrayOf(0, 0 , 0),
            intArrayOf(0, 1 , 0),
            intArrayOf(0, 0 , 0),
        )

//        val orangesRotting = orangesRotting(array2)
//        println(orangesRotting)


//        updateMatrix(array2)
//
//        val reverseList = reverseList(head)


//        val a = arrayOf<CharArray>(
//            charArrayOf('5','3','.','.','7','.','.','.','.'),
//            charArrayOf('6','.','.','1','9','5','.','.','.'),
//            charArrayOf('.','9','8','.','.','.','.','6','.'),
//            charArrayOf('8','.','.','.','6','.','.','.','3'),
//            charArrayOf('4','.','.','8','.','3','.','.','1'),
//            charArrayOf('7','.','.','.','2','.','.','.','6'),
//            charArrayOf('.','6','.','.','.','.','2','8','.'),
//            charArrayOf('.','.','.','4','1','9','.','.','5'),
//            charArrayOf('.','.','.','.','8','.','.','7','9'),
//
//        )
//        isValidSudoku(a)

//        val list = mutableListOf<String>()
//        list.add("leet")
//        list.add("code")
//        wordBreak("leetcode", list)

//        val a = intArrayOf(1, 2, 3)
//        subsets(a)
//        result55.forEach {
//            println(it)
//        }

        val array55 = arrayOf(
            charArrayOf('A','B','C','E'),
            charArrayOf('S','F','E','S'),
            charArrayOf('A','D','E','E')
        )
        val exist = exist(array55, "ABCESEEEFS")
        println(exist)
    }


    fun exist(board: Array<CharArray>, word: String): Boolean {
        board.forEachIndexed { row, items ->
            items.forEachIndexed { colum, it ->
                set.clear()
                if (dfs(board, word, 0, row, colum)) {
                    return true
                }
            }
        }
        return false
    }

    val dx = intArrayOf(1, -1, 0, 0)
    val dy = intArrayOf(0, 0, 1, -1)
    val set = hashSetOf<Int>()

    fun dfs(board : Array<CharArray>, word: String, index : Int, row : Int, colum : Int) : Boolean{
        if (row < 0 || colum < 0 || row >= board.size || colum >= board[0].size) {
            return false
        }

        if (word[index] != board[row][colum] || set.contains(row * board[0].size + colum) ) {
            return false
        } else {
            set.add(row * board[0].size + colum)

            if (index == word.length - 1) {
                return true
            }

            var result = false
            for (i in 0..3) {
                result = result or dfs (board, word, index + 1, row + dx[i], colum + dy[i])
                if (result) {
                    return true
                }
            }

            set.remove(row * board[0].size + colum)

            return false
        }
    }

    val result55 = ArrayList< List<Int> >()
    fun subsets(nums: IntArray): List<List<Int>> {
        val path = LinkedList<Int>()
        backTrace(nums, 0, path)
        return result55
    }

    fun backTrace(nums : IntArray, start : Int, path : LinkedList<Int>) {
        result55.add ( ArrayList(path))

        for (i in start until nums.size) {
            path.add( nums[i])
            backTrace(nums, i + 1, path)
            path.removeLast()
        }
    }

    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val set = HashSet<String>(wordDict)
        val dp = BooleanArray(s.length + 1)
        dp[0] = true

        loop@ for (i in 0 until s.length) {
            for (j in 0 .. i) {
                if (dp[j] && set.contains(s.substring(j, i + 1))) {
                    dp[i + 1] = true
                    continue@loop
                }
            }
        }

        return dp[dp.lastIndex]

    }



    fun isValidSudoku(board: Array<CharArray>): Boolean {
        for (charItems in board) {
            if (isContains(charItems)) {
                return false
            }
        }

        val columnItems = Array<CharArray>(9) {
            CharArray(9)
        }

        val ThreeGridItems = Array<CharArray>(9) {
            CharArray(9)
        }
        board.forEachIndexed { i, array ->
            array.forEachIndexed { j, item ->
                columnItems[j][i] = item
                ThreeGridItems[ j / 3 + 3 * (i / 3)] [ j % 3 + 3 * (i % 3)] = item
            }
        }

        for (charItems in columnItems) {
            if (isContains(charItems)) {
                return false
            }
        }

        for (charItems in ThreeGridItems) {
            if (isContains(charItems)) {
                return false
            }
        }

        return true
    }

    fun isContains(items : CharArray) : Boolean  {
        for (i in items.indices) {
            if (items[i] == '.') {
                continue
            }
            for (j in (i+ 1)..items.size - 1) {
                if (items[i] == items[j]) {
                    return true
                }
            }
        }
        return false
    }


    val result3 = arrayListOf<String>()

    fun backTrace(s : String, start : Int, tracePah : StringBuilder) {
        if (start == s.length) {
            result3.add(tracePah.toString())
            return
        }

        for (i in start until  s.length) {
//            tracePah.append(c)

            backTrace(s, start + 1, tracePah)

            tracePah.deleteCharAt( tracePah.lastIndex )
        }
    }


    val result2 = arrayListOf<List<Int>>()
    fun permute(nums: IntArray): List<List<Int>> {
        val tracePath = LinkedList<Int>()
        backTrace(nums, tracePath)
        return result2
    }


    fun backTrace(nums : IntArray, tracePath : LinkedList<Int>) {
        if (tracePath.size == 3) {
            result2.add (ArrayList<Int>(tracePath))
            tracePath.clear()
            return
        }

        for (i in 0..nums.size) {
            if (tracePath.contains(i)) {
                continue
            }

            tracePath.add(i)

            backTrace(nums, tracePath)

            tracePath.peek()

            val ThreeGridItems = Array<CharArray>(9) {
                CharArray(9)
            }
        }
    }


    val result4 = arrayListOf<String>()

    fun letterCasePermutation3(s: String): List<String> {
        val tracePah = StringBuilder()

        backTrace(s, 0, tracePah)

        return result4
    }

    fun backTrace3(s : String, start : Int, tracePah : StringBuilder) {
        if (start == s.length) {
            result4.add(tracePah.toString())
            return
        }


        var c = s[start]
        if ((c in 'a'..'z') || (c in 'A'..'Z')) {
            tracePah.append(c)
            backTrace3(s, start + 1, tracePah)
            tracePah.deleteCharAt(tracePah.lastIndex)

            c = if (c in 'a'..'z') {
                c.toUpperCase()
            } else {
                c.toLowerCase()
            }
            tracePah.append(c)
            backTrace3(s, start + 1, tracePah)
            tracePah.deleteCharAt(tracePah.lastIndex)
        } else {
            tracePah.append(c)
            backTrace3(s, start + 1, tracePah)
            tracePah.deleteCharAt(tracePah.lastIndex)
        }
    }

    val result = arrayListOf<List<Int>>()
    fun combine(n: Int, k: Int): List<List<Int>> {
        val list = LinkedList<Int>()
        backTrace(n , k ,  list )

        var c = 'a'
        c.toLowerCase()
        return result
    }

    fun backTrace ( n : Int, k : Int, list : LinkedList<Int>) {
        if (k == 0) {
            result.add (ArrayList(list))
            return
        }

        for (i in (k) downTo 1) {
            if (list.contains(i)) {
                continue
            }

            list.add(i)

            backTrace(n , k - 1, list)

            list.removeFirst()
        }
    }


    fun maxPathSum(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        var leftSum = root?.`val`
        if (root?.left != null) {
            leftSum = maxPathSum(root?.left)
        }

        var rightSum = root?.`val`
        if (root?.right != null) {
            rightSum = maxPathSum(root?.right)
        }
        return max(maxPathSumItem(root), leftSum, rightSum)
    }

    fun maxPathSumItem( root : TreeNode?): Int {
        if (root == null) {
            return 0
        }

        val leftSum = maxPathSumItem(root.left)
        val rightSum = maxPathSumItem(root.right)

        return max (leftSum + root.`val` , rightSum + root.`val`, root.`val`, leftSum + rightSum + root.`val`)
    }

    fun max(vararg nums : Int ): Int {
        var max = nums[0]
        nums.forEach {
            if (max < it) {
                max = it
            }
        }
        return max
    }

    fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
        if (mat.isEmpty()) {
            return Array<IntArray>(0) { IntArray(0) }
        }
        val size = mat.size * mat[0].size
        var row = Math.min(size / c, r)

        return Array<IntArray>(row) { rowIndex: Int ->
            IntArray(c) { index->
                val count = rowIndex * c + index
                mat[count/mat[0].size][count%mat[0].size]
            }
        }


    }

    fun generate(numRows: Int): List<List<Int>> {
        val list = arrayListOf<List<Int>>()
        var preRow : List<Int>? = null

        for(i in 1..numRows) {
            val rows = arrayListOf<Int>()

            for (j in 1..i) {
                if (j == 1 || j == i) {
                    rows.add(1)
                } else {
                    rows.add ( preRow!![j - 2] + preRow!![j - 1])
                }

            }
            list.add(rows)
            preRow = rows
        }
        return list
    }

//    fun maxPathSum(root: TreeNode?): Int {
//        return maxPathSumItem(root)
//    }
//
//    fun maxPathSumItem( root : TreeNode?): Int {
//        if (root == null) {
//            return 0
//        }
//
//        val leftSum = maxPathSumItem(root.left)
//        val rightSum = maxPathSumItem(root.right)
//
//        return Math.max (leftSum + root.data , rightSum + root.data)
//    }

    fun isValidBST(root: TreeNode?): Boolean {
        if (root == null) {
            return true
        }
        if (root.right != null) {
            var right = root.right
            while (right != null) {
                if (right.`val` <= root.`val`) {
                    return false
                }
                right = right.left
            }
        }

        if (root.left != null) {
            var left = root.left
            while (left != null) {
                if (left.`val` >= root.`val`) {
                    return false
                }
                left = left.right
            }
        }
        return isValidBST(root?.left) && isValidBST(root?.right)

    }

    fun average(salary: IntArray): Double {
        if (salary.size <= 2) {
            return 0.toDouble()
        }

        var max = salary[0]
        var min = salary[0]
        var all = 0

        salary.forEach {
            if (it > max) {
                max = it
            }
            if (it < min) {
                min = it
            }
            all += it
        }

        return (all - max -min).toDouble() / (salary.size - 2)
    }

    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val result = arrayListOf<ArrayList<Int>>()

        var currCell = LinkedList<TreeNode>()
        var nextCell = LinkedList<TreeNode>()

        currCell.push(root)

        var cellItem : ArrayList<Int> = arrayListOf()

        while (!currCell.isEmpty() || !nextCell.isEmpty() ) {
            if (currCell.isEmpty()) {
                result.add(cellItem)
                cellItem = arrayListOf<Int>()

                val tmp = currCell
                currCell = nextCell
                nextCell = tmp
            }

            val item = currCell.pop()
            cellItem.add (item.`val`)

            item?.left?.run {
                nextCell.offer (this)
            }
            item?.right?.run {
                nextCell.offer (this)
            }

        }
        result.add(cellItem)


        return result
    }

    fun containsDuplicate(nums: IntArray): Boolean {
        val set = hashSetOf<Int>()

        nums.forEach {
            if (set.contains(it)) {
                return false
            }
            set.add(it)
        }
        return true
    }



    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {

        val result = Array<IntArray>( mat.size ) { IntArray(mat[0].size) }

        mat.forEachIndexed{ row, array ->
            array.forEachIndexed {  column, item ->
                result[row][column] = dfs(mat, row, column)
            }
        }
        return result
    }

    fun dfs(mat : Array<IntArray>, x : Int , y : Int) : Int{
        if (mat[x][y] == 0 ) {
            return 0
        }

        val dx = intArrayOf(1, -1, 0, 0)
        val dy = intArrayOf(0, 0, 1, -1)

        var array = LinkedList<Pair<Int, Int>>()
        var adjustArray = LinkedList<Pair<Int, Int>>()

        array.push( Pair<Int, Int> (x, y) )
        mat[x][y] = 2
        var result = 0

        label@ while (!array.isEmpty() || !adjustArray.isEmpty()) {
            if (array.isEmpty()) {
                val temp = array
                array = adjustArray
                adjustArray = temp
            }
            result++
            mat[x][y] = 2

            val itemPos = array.pop()

            for (i in 0..3) {
                val x = itemPos.first + dx[i]
                val y = itemPos.second + dy[i]
                if (x >= 0 && y>= 0 && x < mat.size && y < mat[0].size) {
                    if (mat[x][y] == 0) {
                        break@label
                    } else if (mat[x][y] == 1) {
                        adjustArray.push(Pair<Int,Int>(x, y))
                    }
                }
            }
        }

        mat.forEachIndexed { row, array ->
            array.forEachIndexed { column, item ->
                if (item == 2) {
                    mat[x][y] = 1
                }
            }

        }

        return result
    }



    fun reverseList(head: ListNode?): ListNode? {
        if (head == null || head.next == null) {
            return head
        }
        val next = reverseList(head.next)
        head?.next?.next = head;
        head.next = null;
        return next

    }

    fun orangesRotting(grid: Array<IntArray>): Int {
        return orangesRottingMy(grid)
    }

    fun orangesRottingMy(grid: Array<IntArray>): Int {
        var first = LinkedList<Pair<Int, Int>>()
        grid.forEachIndexed { row , array ->
            array.forEachIndexed { colum, item ->
                if (item == 2) {
                    first.push( Pair<Int, Int> (row, colum) )
                }
            }
        }

        var next = LinkedList<Pair<Int, Int>>()

        var PathItem = 0

        while (!first.isEmpty() || !next.isEmpty() ) {
            if (first.isEmpty() && !next.isEmpty()) {
                val tmp = first
                first = next
                next = tmp
                PathItem++
            }

            val item =  first.pop()
            val dx = intArrayOf(1, -1, 0, 0)
            val dy = intArrayOf (0, 0, 1, -1)

            for (i in 0..3) {
                val x = item.first + dx[i]
                val y = item.second + dy[i]
                if (x >= 0 && y>= 0 && x < grid.size && y < grid[0].size) {
                    if (grid[x][y] == 1) {
                        grid[x][y] = 2
                        next.push(Pair<Int,Int>(x, y))
                    }
                }
            }
        }

        label@ for (i in 0..grid.lastIndex) {
            val rowItems = grid[i]
            for (item in rowItems) {
                if (item == 1) {
                    PathItem = -1
                    break@label
                }
            }
        }


        return PathItem

    }


    class Node(var data : Int  = 0, var left : Node? = null, var right : Node? = null, var next : Node? = null)


    class TreeNode(var `val` : Int  = 0, var left : TreeNode? = null, var right : TreeNode? = null)

    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        return  dfs( root1, root2)
    }

    private fun dfs(root1: TreeNode?, root2: TreeNode?) : TreeNode?{
        if (root1 == null && root2 == null) {
            return null
        }


        var result = TreeNode()
        result.`val` = (root1?.`val` ?:0)  +  (root2?.`val` ?:0)
        result.left = dfs (root1?.left, root2?.left)
        result.right = dfs (root1?.right, root2?.right)
        return result
    }


    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        var maxIsland = 0

        grid.forEachIndexed {row, array ->
            array.forEachIndexed { column, item ->
                if (item == 1) {
                    maxIsland = Math.max( maxIsland, searchIsland(grid, row, column, column))
                }

            }
        }

        grid.forEachIndexed { row, array ->
            array.forEachIndexed { column, item ->
                if (item == 2) {
                    grid[row][column] = 1
                }
            }
        }

        return maxIsland
    }

    fun searchIsland(grid: Array<IntArray>, row : Int, column : Int, startColumn : Int) : Int{
        if (row < 0 || column < 0 || row >= grid.size || column >= grid[0].size) {
            return 0
        }
        var count = 0

        if (grid[row][column] == 1) {
            count++
            grid[row][column] = 2
            count += searchIsland(grid, row , column -1, startColumn)
            count += searchIsland(grid, row , column + 1, startColumn)
            count += searchIsland(grid, row + 1, column, startColumn)
            count += searchIsland(grid, row - 1, column, startColumn)
        }
        return count
    }


    fun floodFill2(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {

        if (image.isEmpty() || image[0].isEmpty()) {
            return image
        }
        val oldColor = image[sr][sc]
        if (oldColor == newColor) {
            return image
        }


        val stack = LinkedList<Pair<Int,Int>>()
        stack.push(Pair<Int,Int>(sr, sc))

        while (!stack.isEmpty()) {
            val popItem = stack.pop()
            image[popItem.first][popItem.second] = newColor

            var sr = popItem.first
            var sc = popItem.second

            if (sc -1 >= 0 && image[sr][sc-1] != newColor && image[sr][sc-1] == oldColor) {
                stack.push(Pair(sr, sc - 1))
            }
            if (sc + 1 < image[0].size && image[sr][sc+ 1] != newColor && image[sr][sc+1] == oldColor) {
                stack.push(Pair(sr, sc + 1))
            }
            if (sr -1 >= 0 && image[sr - 1][sc] != newColor && image[sr - 1][sc] == oldColor) {
                stack.push(Pair(sr - 1, sc))
            }
            if (sr + 1 < image.size && image[sr + 1][sc] != newColor && image[sr + 1][sc] == oldColor) {
                stack.push(Pair(sr + 1, sc))
            }
        }
        return image
    }


    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int): Array<IntArray> {
        if (image.isEmpty() || image[0].isEmpty()) {
            return image
        }

        val oldColor = image[sr][sc]
        if (oldColor == newColor) {
            return image
        }

        return dfs(image, sr, sc , oldColor, newColor)
    }

    fun dfs(image: Array<IntArray>, sr: Int, sc : Int, oldColor: Int, newColor: Int): Array<IntArray> {
        var currColor = image[sr][sc]
        if (currColor != newColor && currColor == oldColor) {
            image[sr][sc] = newColor

            for (i in 0 until 4) {
                if (sc -1 >= 0) {
                    dfs(image, sr, sc - 1, oldColor, newColor)
                }
                if (sc + 1 < image[0].size) {
                    dfs(image, sr, sc + 1, oldColor, newColor)
                }
                if (sr -1 >= 0) {
                    dfs(image, sr - 1, sc, oldColor, newColor)
                }
                if (sr + 1 < image.size) {
                    dfs(image, sr + 1, sc, oldColor, newColor)
                }
            }
        }


        return image
    }


    fun rob(nums: IntArray): Int {
        val f = IntArray(nums.size)
        f[0] = nums[0]
        f[1] = nums[1]

        for (i in 2..nums.lastIndex) {
            f[i] = Math.max( f[i-2] + nums[i], f[i-1] )
        }
        return Math.max(f[f.lastIndex], f[f.lastIndex-1])
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
    输出："s'teL ekat edoCteeL tsetnoc'

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