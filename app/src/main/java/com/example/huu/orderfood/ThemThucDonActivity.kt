package com.example.huu.orderfood

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.huu.orderfood.Adapters.HienThiSpinnerLoaiMonAnAdapter
import com.example.huu.orderfood.Entities.LoaiMonAnEntity2
import com.example.huu.orderfood.Entities.MonAnEntity
import com.example.huu.orderfood.Services.LoaiMonAnService
import com.example.huu.orderfood.Services.MonAnService
import com.example.huu.orderfood.Utilities.REQUEST_CODE_MOHINH
import kotlinx.android.synthetic.main.activity_them_thuc_don.*
import kotlinx.android.synthetic.main.activity_then_loai_thuc_don.*
import java.io.IOException

class ThemThucDonActivity : AppCompatActivity() {
    lateinit var danhSachLoaiMonAn:List<LoaiMonAnEntity2>
    lateinit var adapterSpinner: HienThiSpinnerLoaiMonAnAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_thuc_don)
        hienThiDanhSachLoaiMonAnSpinner()
    }

    override fun onResume() {
        super.onResume()
        hienThiDanhSachLoaiMonAnSpinner()
    }

    private fun hienThiDanhSachLoaiMonAnSpinner() {
        danhSachLoaiMonAn = LoaiMonAnService.layTatCaLoaiMonAn(this)
        adapterSpinner = HienThiSpinnerLoaiMonAnAdapter(this, danhSachLoaiMonAn)
        spinLoaiMonAn.adapter = adapterSpinner
    }

    fun onClickedThemLoaiThucDon(view: View) {
        val intent = Intent(this, ThenLoaiThucDonActivity::class.java)
        startActivity(intent)
    }

    fun onClickedHinhThucDon(view: View) {
        val pickImageIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

            startActivityForResult(Intent.createChooser(pickImageIntent,"Chọn hình thực đơn"), REQUEST_CODE_MOHINH)



    }

    fun onClickedBtnThemMonAn(view: View) {
        val position = spinLoaiMonAn.selectedItemPosition
        MonAnEntity.MALOAI =  LoaiMonAnService.layTatCaLoaiMonAn(this).get(position).maloai
        MonAnEntity.TENMONAN = edThemTenMonAn.text.toString()
        MonAnEntity.GIATIEN = edThemGiaTien.text.toString()
        if (!MonAnEntity.TENMONAN.equals("") && !MonAnEntity.GIATIEN.equals("")) {
            val kiemtra = MonAnService.themMonAn(this)
            if (kiemtra != 0L) {
                Toast.makeText(this, "Thêm Món Thành Công", Toast.LENGTH_SHORT).show()
            }
        }else {
            Toast.makeText(this, "Thêm Thất bại", Toast.LENGTH_SHORT).show()
        }

        Log.d("maloai","${MonAnEntity.MALOAI}")
    }

    fun onClickedThoatThemThucDon(view: View) {
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_MOHINH) {
            if (resultCode == Activity.RESULT_OK && data!=null) {
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,data.data)
                    imHinhThucDon.setImageBitmap(bitmap)
                } catch (e: IOException) {
                e.printStackTrace()
                }
                MonAnEntity.HINHANH = Uri.parse(data.data?.toString()).toString()
            }

        }
    }
}
