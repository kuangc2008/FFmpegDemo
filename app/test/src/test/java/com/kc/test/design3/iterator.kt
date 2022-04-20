package com.kc.test.design3


/**
 * 迭代器
 */
data class Book (val name : String)

// 1
class BookStore(val books : List<Book>) {
    operator fun iterator() : Iterator<Book> = this.books.iterator()
}

class BookStore2(val books : List<Book>)


// 2  可以有更多控制
operator fun BookStore2.iterator() : Iterator<Book>  = object : Iterator<Book> {

    val iterator = books.iterator()
    override fun hasNext() =  iterator.hasNext()

    override fun next()  = iterator.next()
}
