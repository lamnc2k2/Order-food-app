package com.example.huu.orderfood.Fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import android.widget.EditText
import com.example.huu.orderfood.R
import java.util.*
//Dùng để hiển thị một Dialog lựa chọn ngày tháng năm
class DatePickerFragment:DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Lấy ngày tháng năm hiện tại
        val calendar = Calendar.getInstance()
        val iNam = calendar.get(Calendar.YEAR)
        val iThang = calendar.get(Calendar.MONTH)+1
        val iNgay = calendar.get(Calendar.DAY_OF_MONTH)
        //Trả về dialog chọn mặc định là ngày tháng năm hiện tại
        return DatePickerDialog(activity!!,this,iNam,iThang,iNgay)
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        //setText và format ngày tháng năm mà người dùng đã lựa chọn lên edtNgaySinh trong DangKyActivity
       val edtNgaySinh = activity?.findViewById(R.id.edtNgaySinh) as EditText
        val sNgaySinh = "$dayOfMonth/${month+1}/$year"
        edtNgaySinh.setText(sNgaySinh)
    }
}