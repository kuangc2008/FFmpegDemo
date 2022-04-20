package com.kc.test.design4


interface MacBook {
    fun getCost() : Int
    fun getDesc() : String
    fun getProdDate() : String
}


class MacBookPro : MacBook {
    override fun getCost(): Int {
        return 1
    }

    override fun getDesc(): String {
        return ""
    }

    override fun getProdDate(): String {
        return ""
    }
}

class ProcessorUpgradeMacBookPro(private val macbook : MacBook) : MacBook by macbook {
    override fun getProdDate(): String {
        return "new"
    }
}