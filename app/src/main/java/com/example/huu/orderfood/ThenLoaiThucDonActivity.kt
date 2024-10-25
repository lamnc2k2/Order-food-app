package com.example.huu.orderfood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.huu.orderfood.Entities.LoaiMonAnEntity
import com.example.huu.orderfood.Services.LoaiMonAnService
import kotlinx.android.synthetic.main.activity_then_loai_thuc_don.*

class ThenLoaiThucDonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_then_loai_thuc_don)
    }

    fun onClickedBtnDongYThemLoaiThucDon(view: View) {
        LoaiMonAnEntity.TENLOAI = edThemLoaiThucDon.text.toString()
        if (!LoaiMonAnEntity.TENLOAI.equals("")) {
            val kiemtra = LoaiMonAnService.themLoaiMonAn(this)
            if (kiemtra != 0L) {
                Toast.makeText(this, "Thêm Loại Món Ăn Thành Công", Toast.LENGTH_SHORT).show()
                finish()
            }
        }else {
            Toast.makeText(this, "Thêm Thất bại", Toast.LENGTH_SHORT).show()
        }
    }
}
