package com.example.huu.orderfood

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.huu.orderfood.Services.BanAnService
import kotlinx.android.synthetic.main.activity_sua_ban_an.*

class SuaBanAnActivity : AppCompatActivity() {
    var iMaBan:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sua_ban_an)
        iMaBan = intent.getIntExtra("maban",0)
    }

    fun onClickedBtnDongYSua() {
        val tenban = edSuaTenBanAn.text.toString()
        BanAnService.capNhatLaiTenBan(this, iMaBan, tenban) { it->
            if (it) {
                val intent = Intent()
                intent.putExtra("kiemtra",it)
                setResult(Activity.RESULT_OK, intent)
                finish()

            } else {
                Toast.makeText(this, resources.getString(R.string.vuilongnhapdulieu), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
