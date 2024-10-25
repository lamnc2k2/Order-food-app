package com.example.huu.orderfood.Services

import android.content.ContentValues
import android.content.Context
import com.example.huu.orderfood.Entities.LoaiMonAnEntity
import com.example.huu.orderfood.Entities.MonAnEntity
import com.example.huu.orderfood.Entities.MonAnEntity2
import com.example.huu.orderfood.Utilities.*

object MonAnService {
    fun themMonAn(context: Context): Long {
        val contentValues = ContentValues()
        contentValues.put(TB_MONAN_MALOAI, MonAnEntity.MALOAI)
        contentValues.put(TB_MONAN_TENMONAN, MonAnEntity.TENMONAN)
        contentValues.put(TB_MONAN_GIATIEN, MonAnEntity.GIATIEN)
        contentValues.put(TB_MONAN_HINHANH, MonAnEntity.HINHANH)
        return AuthService.createOrOpenDatabase(context).insert(TB_MONAN,null,contentValues)
    }

    fun layDanhSachMonAnTheoMaLoai(context: Context): List<MonAnEntity2> {
        val danhSacMonAnTheoLoai = arrayListOf<MonAnEntity2>()
        val truyvan = "SELECT * FROM $TB_MONAN WHERE $TB_MONAN_MALOAI = ${LoaiMonAnEntity.MALOAI}"
        val cursor = AuthService.createOrOpenDatabase(context).rawQuery(truyvan,null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            MonAnEntity.MAMONAN = cursor.getInt(cursor.getColumnIndex(TB_MONAN_MAMON))
            MonAnEntity.MALOAI = cursor.getInt(cursor.getColumnIndex(TB_MONAN_MALOAI))
            MonAnEntity.TENMONAN = cursor.getString(cursor.getColumnIndex(TB_MONAN_TENMONAN))
            MonAnEntity.GIATIEN = cursor.getString(cursor.getColumnIndex(TB_MONAN_GIATIEN))
            MonAnEntity.HINHANH = cursor.getString(cursor.getColumnIndex(TB_MONAN_HINHANH))
            danhSacMonAnTheoLoai.add(MonAnEntity.createMonAnEntity())
            cursor.moveToNext()
        }
        return danhSacMonAnTheoLoai
    }


}