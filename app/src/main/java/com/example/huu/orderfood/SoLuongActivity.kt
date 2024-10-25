package com.example.huu.orderfood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.huu.orderfood.Entities.ChiTietGoiMonEntity2
import com.example.huu.orderfood.R
import com.example.huu.orderfood.Services.GoiMonService
import kotlinx.android.synthetic.main.activity_so_luong.*

class SoLuongActivity : AppCompatActivity() {
    var iMaMonAn = 0
    var iMaBan = 0
    var iMaGoiMon = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_so_luong)
        val intent = intent
        iMaBan = intent.getIntExtra("maban",0)
        iMaMonAn = intent.getIntExtra("mamonan",0)

    }

    fun onClickedBtnThemSoLuong(view: View) {
        iMaGoiMon = GoiMonService.layMaGoiMonTheoMaBan(this,iMaBan).toInt()
        GoiMonService.kiemTraMonAnDaTonTai(this, iMaGoiMon,iMaMonAn){it ->
            if (it) {
                val soluongcu = GoiMonService.laySoLuongMonAnTheoMaGoiMon(this, iMaGoiMon)
                val soluongmoi = edSoLuongMonAn.text.toString().toInt()
                val tongsoluong = soluongcu.plus(soluongmoi)
                val chiTietGoiMonEntity2 = ChiTietGoiMonEntity2(iMaMonAn, iMaGoiMon, tongsoluong)
                GoiMonService.capNhatSoLuongMonAn(this, chiTietGoiMonEntity2) { boolean ->
                    if (boolean) {
                        Toast.makeText(this,"Thêm thành công",Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this,"Thêm thất bại",Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                val soluonggoi = edSoLuongMonAn.text.toString().toInt()
                val chiTietGoiMonEntity2 = ChiTietGoiMonEntity2(iMaMonAn, iMaGoiMon, soluonggoi)
                GoiMonService.themChiTietGoiMon(this, chiTietGoiMonEntity2) { b ->
                    if (b) {
                        Toast.makeText(this,"Thêm thành công",Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this,"Thêm thất bại",Toast.LENGTH_SHORT).show()
                    }
                }


            }

        }

    }



}
