package com.example.huu.orderfood.Services

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.huu.orderfood.Entities.NhanVienEntity
import com.example.huu.orderfood.Utilities.*
//Đối tượng singleton này được dùng để kiểm tra, thao tác CRUD các dữ liệu  có trong table NHANVIEN
object NhanVienService {
    fun themNhanVien(context: Context): Long {
        val contentValues = ContentValues()
        contentValues.put(TB_NHANVIEN_TENDN, NhanVienEntity.TENDN)
        contentValues.put(TB_NHANVIEN_CMND, NhanVienEntity.CMND)
        contentValues.put(TB_NHANVIEN_GIOITINH, NhanVienEntity.GIOITINH)
        contentValues.put(TB_NHANVIEN_MATKHAU, NhanVienEntity.MATKHAU)
        contentValues.put(TB_NHANVIEN_NGAYSINH, NhanVienEntity.NGAYSINH)
        return AuthService.createOrOpenDatabase(context).insert(TB_NHANVIEN,null,contentValues)
    }


    //Phương thức này kiểm tra trong table đã có nhân viên hay chưa, dùng để kiểm tra sự kiện ẩn hiện button Đăng Ký
    //Và button Đồng Ý, dùng trong DangNhapActivity
    @SuppressLint("Recycle")
    fun kiemTraNhanVienTonTai(context: Context, complete:(Boolean)-> Unit) {
        val truyvan = "SELECT * FROM $TB_NHANVIEN"
        val cursor = AuthService.createOrOpenDatabase(context).rawQuery(truyvan,null)
        if (cursor.count != 0) {
            complete(true)
        } else {
            complete(false)
        }
    }


//Kiểm tra sự kiên click button Đồng Ý sau khi người dùng đã nhập tên đăng nhập và mật khẩu
    @SuppressLint("Recycle")
    fun kiemTraDangNhap(context: Context, complete: (Boolean) -> Unit) {
        val truyvan = "SELECT * FROM $TB_NHANVIEN WHERE $TB_NHANVIEN_TENDN = '${NhanVienEntity.TENDN}' " +
                "AND $TB_NHANVIEN_MATKHAU = '${NhanVienEntity.MATKHAU}'"
        val cursor = AuthService.createOrOpenDatabase(context).rawQuery(truyvan, null)
    cursor.moveToFirst()

        if (cursor.count != 0) {
            while (!cursor.isAfterLast) {
                NhanVienEntity.MANV = cursor.getInt(cursor.getColumnIndex(TB_NHANVIEN_MANV))
                cursor.moveToNext()
            }
            complete(true)

        } else {
            complete(false)
        }
    }
}