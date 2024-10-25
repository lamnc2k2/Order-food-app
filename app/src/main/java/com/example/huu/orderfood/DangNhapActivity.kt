package com.example.huu.orderfood

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.huu.orderfood.Entities.NhanVienEntity
import com.example.huu.orderfood.Services.NhanVienService
import com.example.huu.orderfood.Utilities.TB_NHANVIEN_TENDN
import kotlinx.android.synthetic.main.activity_dang_nhap.*

class DangNhapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)

        kiemTraNhanVienCoTonTaiHayKhong()

    }

    private fun kiemTraNhanVienCoTonTaiHayKhong() {
        NhanVienService.kiemTraNhanVienTonTai(this) {it->
            if (it) {
                btnDangKyDN.visibility = View.GONE
                btnDongYDN.visibility = View.VISIBLE
            } else {
                btnDongYDN.visibility = View.GONE
                btnDangKyDN.visibility = View.VISIBLE
            }
        }
    }

    fun onClickedBtnDongYDN(view: View) {
        NhanVienEntity.TENDN = edtUserNameDN.text.toString()
        NhanVienEntity.MATKHAU = edtPasswordDN.text.toString()
        NhanVienService.kiemTraDangNhap(this) { it->
            if (it) {
                val intent = Intent(this, TrangChuActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onClickedBtnDangKyDN(view: View) {
        val intent = Intent(this, DangKyActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        kiemTraNhanVienCoTonTaiHayKhong()
    }
}
