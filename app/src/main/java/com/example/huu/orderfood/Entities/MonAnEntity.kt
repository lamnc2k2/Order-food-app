package com.example.huu.orderfood.Entities

object MonAnEntity {
    var MAMONAN:Int = 0
    var MALOAI:Int = 0
    var TENMONAN = ""
    var GIATIEN = ""
    var HINHANH = ""

    fun createMonAnEntity(): MonAnEntity2 {
        return MonAnEntity2(MAMONAN, MALOAI, TENMONAN, GIATIEN, HINHANH)
    }
}

class MonAnEntity2(var mamonan:Int,var maloai:Int,var tenmonan:String,var giatien:String, var hinhanh:String)