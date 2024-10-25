package com.example.huu.orderfood.Entities

object ChiTietGoiMonEntity {
    var MAMONAN: Int = 0
    var MAGOIMON: Int = 0
    var SOLUONG:Int = 0

    fun createChiTietGoiMonEntity():ChiTietGoiMonEntity2 {
        return ChiTietGoiMonEntity2(MAMONAN, MAGOIMON, SOLUONG)
    }
}

class ChiTietGoiMonEntity2(var mamonan: Int, var magoimon: Int, var soluong: Int)

