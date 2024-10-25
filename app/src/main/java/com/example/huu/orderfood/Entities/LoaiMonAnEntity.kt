package com.example.huu.orderfood.Entities

object LoaiMonAnEntity {
    var MALOAI:Int = 0
    var TENLOAI = ""

    fun createLoaiMonAnEntity(): LoaiMonAnEntity2 {
        return LoaiMonAnEntity2(MALOAI, TENLOAI)
    }

}

class LoaiMonAnEntity2(var maloai:Int,var tenloai:String)