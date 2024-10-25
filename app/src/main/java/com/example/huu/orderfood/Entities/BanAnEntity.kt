package com.example.huu.orderfood.Entities

object BanAnEntity {
    var MABAN:Int = 0
    var TENBAN = ""
    var TINHTRANG = "false"
    var DUOCCHON :Boolean = false


    fun createBanAnEntity(): BanAnEntity2 {
        return BanAnEntity2(MABAN, TENBAN, DUOCCHON)
    }
}

class BanAnEntity2(var maban: Int, var tenban: String, var duocchon:Boolean){

}