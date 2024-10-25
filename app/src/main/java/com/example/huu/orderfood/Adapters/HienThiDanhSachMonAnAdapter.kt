package com.example.huu.orderfood.Adapters

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.huu.orderfood.Entities.MonAnEntity2
import android.widget.TextView
import com.example.huu.orderfood.R


class HienThiDanhSachMonAnAdapter (val context: Context, val danhSachMonAn:List<MonAnEntity2>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View
        val viewHolder:ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_layout_hienthidanhsachmonan, parent, false)
            viewHolder = ViewHolder()
            viewHolder.imHinhMonAn = view.findViewById(R.id.imHienThiDSMonAn) as ImageView
            viewHolder.txtTenMonAn = view.findViewById(R.id.txtTenDSMonAn) as TextView
            viewHolder.txtGiaTien = view.findViewById(R.id.txtGiaTienDSMonAn) as TextView
            view.tag = viewHolder
        } else {
            viewHolder = convertView.getTag() as ViewHolder
            view = convertView
        }

        val monAnEntity2 = danhSachMonAn.get(position)
        Log.d("tenmonan",monAnEntity2.tenmonan)
        val hinhanh: String = monAnEntity2.hinhanh
        if (hinhanh.equals("")) {
            viewHolder.imHinhMonAn?.setImageResource(R.drawable.backgroundheader1)
        } else {
            val uri = Uri.parse(hinhanh)
            viewHolder.imHinhMonAn?.setImageURI(uri)
        }
        viewHolder.txtTenMonAn?.setText(monAnEntity2.tenmonan);
        viewHolder.txtGiaTien?.setText(context.getResources().getString(R.string.gia) + monAnEntity2.giatien);


        return view
    }

    override fun getItem(position: Int): Any {
        return danhSachMonAn.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getCount(): Int {
        return danhSachMonAn.count()

    }

    private class ViewHolder {
        var imHinhMonAn: ImageView? = null
        var txtTenMonAn: TextView? = null
        var txtGiaTien:TextView? = null
    }

}