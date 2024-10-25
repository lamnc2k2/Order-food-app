package com.example.huu.orderfood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.huu.orderfood.Entities.BanAnEntity
import com.example.huu.orderfood.Services.BanAnService
import kotlinx.android.synthetic.main.activity_them_ban_an.*

class ThemBanAnActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_ban_an)


    }

    fun onClickedBtnThemBanAn(view: View) {

        BanAnEntity.TENBAN = edThemTenBanAn.text.toString()

        if (!BanAnEntity.TENBAN.equals("")) {
           val kiemtra = BanAnService.themBanAn(this)
            if (kiemtra != 0L) {
                Toast.makeText(this, "Thêm Bàn Ăn Thành Công", Toast.LENGTH_SHORT).show()
                finish()
            }
        }else {
            Toast.makeText(this, "Thêm Bàn Ăn Thất bại", Toast.LENGTH_SHORT).show()
        }
    }
}
