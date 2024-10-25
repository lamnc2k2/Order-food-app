package com.example.huu.orderfood

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.Toast
import com.example.huu.orderfood.Adapters.HienThiThanhToanAdapter
import com.example.huu.orderfood.Entities.ThanhToanEntity2
import com.example.huu.orderfood.Services.BanAnService
import com.example.huu.orderfood.Services.GoiMonService
import kotlinx.android.synthetic.main.activity_thanh_toan.*

class ThanhToanActivity : AppCompatActivity() {
    lateinit var hienThiThanhToanAdapter: HienThiThanhToanAdapter
    lateinit var listThanhToan:List<ThanhToanEntity2>
    var iMaBan = 0
    var iMagoimon = 0
    var tongtien =0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanh_toan)
        iMaBan = intent.getIntExtra("maban", 0)
        if (iMaBan != 0) {
            hienThiThanhToan()
            for (i in 0 until listThanhToan.count()) {
                val soluong = listThanhToan.get(i).soluong
                val giatien = listThanhToan.get(i).giatien
                tongtien += (soluong*giatien)
            }
            txtTongTien.text = "Tổng cộng: $tongtien"
        }

    }

    private fun hienThiThanhToan() {
        iMagoimon = GoiMonService.layMaGoiMonTheoMaBan(this, iMaBan).toInt()
        listThanhToan= GoiMonService.layDanhSachMonAnTheoMaGoiMon(this, iMagoimon)
        hienThiThanhToanAdapter = HienThiThanhToanAdapter(this,listThanhToan)
        gvThanhToan.adapter = hienThiThanhToanAdapter
        hienThiThanhToanAdapter.notifyDataSetChanged()
    }

    fun onClickedBtnThanhToan(view: View) {
        BanAnService.capNhatTinhTrangBan(this, iMaBan) { b ->
            if (b) {
                GoiMonService.capNhatTrangThaiGoiMonTheoMaBan(this, iMaBan, "true") { boolean ->
                    if (boolean) {
                        Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_SHORT).show()
                        hienThiThanhToan()
                    } else {
                        Toast.makeText(this, "Thanh toán thất bại", Toast.LENGTH_SHORT).show()
                    }

                }
            } else {
                Toast.makeText(this,"Cập nhật tình trạng bàn thất bại",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onClickedBtnThoatThanhToan(view: View) {
            finish()
    }
}
