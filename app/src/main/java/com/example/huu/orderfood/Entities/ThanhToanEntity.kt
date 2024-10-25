package com.example.huu.orderfood.Entities

object ThanhToanEntity {
    var TENMONAN = ""
    var SOLUONG: Int = 0
    var GIATIEN:Int = 0

    fun createThanhToanEntity(): ThanhToanEntity2 {
        return ThanhToanEntity2(TENMONAN, SOLUONG, GIATIEN)
    }
}

class ThanhToanEntity2(var tenmonan:String,var soluong:Int, var giatien:Int)
