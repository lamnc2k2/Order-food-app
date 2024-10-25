package com.example.huu.orderfood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.huu.orderfood.Entities.NhanVienEntity
import com.example.huu.orderfood.Fragments.DatePickerFragment
import com.example.huu.orderfood.Services.NhanVienService
import kotlinx.android.synthetic.main.activity_dangky.*

class DangKyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dangky)
        //Bắt sự kiện người dùng có đang trỏ dấu nháy vào edtNgaySinh hay không
        edtNgaySinh.setOnFocusChangeListener { _, hasFocus ->
                //Nếu có thì sẽ tạo một đối tượng DatePickerFragment và show lên
                if (hasFocus) {
                    val datePickerFragment = DatePickerFragment()
                    datePickerFragment.show(supportFragmentManager,"Ngày Sinh")
                    //xuất popup ngày sinh
                }


        }
        rbNam.isChecked = true
    }

    fun onClickedBtnDongY() {
        NhanVienEntity.TENDN = edtTenDNDK.text.toString()
        NhanVienEntity.MATKHAU = edtMatKhau.text.toString()
        NhanVienEntity.NGAYSINH = edtNgaySinh.text.toString()
        if (!edtCMND.text.toString().equals("")) {
            NhanVienEntity.CMND = edtCMND.text.toString().toLong()
        }

        NhanVienEntity.GIOITINH = when (rgGioiTinh.checkedRadioButtonId) {
            R.id.rbNam -> "Nam"
            else-> "Nữ"
        }
        if (NhanVienEntity.TENDN.equals("")) {
            Toast.makeText(this,"Bạn cần nhập tên đăng nhập",Toast.LENGTH_SHORT).show()
        }else if (NhanVienEntity.MATKHAU.equals("")) {
            Toast.makeText(this, "Bạn cần nhập mật khẩu", Toast.LENGTH_SHORT).show()
        } else {
            val kiemtra = NhanVienService.themNhanVien(this)
            if (kiemtra != 0L) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onClickedBtnThoat(view: View) {
finish()
    }


}
