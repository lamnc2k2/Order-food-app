package com.example.huu.orderfood.Services

import android.content.ContentValues
import android.content.Context
import com.example.huu.orderfood.Entities.LoaiMonAnEntity
import com.example.huu.orderfood.Entities.LoaiMonAnEntity2
import com.example.huu.orderfood.Utilities.TB_LOAIMONAN
import com.example.huu.orderfood.Utilities.TB_LOAIMONAN_MALOAI
import com.example.huu.orderfood.Utilities.TB_LOAIMONAN_TENLOAI

object LoaiMonAnService {
    fun themLoaiMonAn(context: Context): Long {
        val contentValues = ContentValues()
        contentValues.put(TB_LOAIMONAN_TENLOAI, LoaiMonAnEntity.TENLOAI)
        return AuthService.createOrOpenDatabase(context).insert(TB_LOAIMONAN,null,contentValues)
    }

    fun layTatCaLoaiMonAn(context: Context): List<LoaiMonAnEntity2> {
        val danhSachLoaiMonAn = arrayListOf<LoaiMonAnEntity2>()
        val truyvan = "SELECT * FROM $TB_LOAIMONAN"
        val cursor = AuthService.createOrOpenDatabase(context).rawQuery(truyvan,null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            LoaiMonAnEntity.MALOAI = cursor.getInt(cursor.getColumnIndex(TB_LOAIMONAN_MALOAI))
            LoaiMonAnEntity.TENLOAI = cursor.getString(cursor.getColumnIndex(TB_LOAIMONAN_TENLOAI))
            val loaiMonAnEntity2 = LoaiMonAnEntity.createLoaiMonAnEntity()
            danhSachLoaiMonAn.add(loaiMonAnEntity2)
            cursor.moveToNext()
        }
        return danhSachLoaiMonAn

    }
}