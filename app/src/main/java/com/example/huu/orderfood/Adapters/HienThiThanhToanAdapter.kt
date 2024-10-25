package com.example.huu.orderfood.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.huu.orderfood.Entities.ThanhToanEntity2
import com.example.huu.orderfood.R


class HienThiThanhToanAdapter(val context: Context, val danhSachThanhToan:List<ThanhToanEntity2>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View
        val viewHolderThanhToan:ViewHolderThanhToan
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_layout_hienthithanhtoan, parent, false)
            viewHolderThanhToan = ViewHolderThanhToan()
            viewHolderThanhToan.txtTenMonAn = view.findViewById(R.id.txtTenMonAnThanhToan) as TextView
            viewHolderThanhToan.txtGiaTien = view.findViewById(R.id.txtGiaTienThanhToan) as TextView
            viewHolderThanhToan.txtSoLuong = view.findViewById(R.id.txtSoLuongThanhToan) as TextView

            view.tag = viewHolderThanhToan

        } else {
            viewHolderThanhToan = convertView.getTag() as ViewHolderThanhToan
            view = convertView
        }
        val thanhToanEntity2 = danhSachThanhToan.get(position)
        viewHolderThanhToan.txtTenMonAn?.text = thanhToanEntity2.tenmonan
        viewHolderThanhToan.txtSoLuong?.text = thanhToanEntity2.soluong.toString()
        viewHolderThanhToan.txtGiaTien?.text = thanhToanEntity2.giatien.toString()

        return view
    }

    override fun getItem(position: Int): Any {
        return danhSachThanhToan.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return danhSachThanhToan.count()
    }

    inner class ViewHolderThanhToan {
        internal var txtTenMonAn: TextView? = null
        internal var txtSoLuong: TextView? = null
        internal var txtGiaTien: TextView? = null
    }
}