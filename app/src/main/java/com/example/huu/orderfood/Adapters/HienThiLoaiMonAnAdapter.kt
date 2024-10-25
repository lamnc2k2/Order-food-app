package com.example.huu.orderfood.Adapters

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.huu.orderfood.Entities.LoaiMonAnEntity2
import android.widget.TextView
import com.example.huu.orderfood.Entities.LoaiMonAnEntity
import com.example.huu.orderfood.Entities.MonAnEntity2
import com.example.huu.orderfood.R
import com.example.huu.orderfood.Services.MonAnService


class HienThiLoaiMonAnAdapter(val context: Context, val danhSachLoaiMonAn:List<LoaiMonAnEntity2>): BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View
        val viewHolder:ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_layout_hienloaimonan, parent, false)
            viewHolder = ViewHolder()
            viewHolder.imHinhLoaiThucDon = view.findViewById(R.id.imHienThiMonAn) as ImageView
            viewHolder.txtTenLoaiThucDon = view.findViewById(R.id.txtTenLoaiThucDon) as TextView

            view.tag = viewHolder

        } else {
            viewHolder = convertView.getTag() as ViewHolder
            view = convertView
        }

        viewHolder.txtTenLoaiThucDon?.text = danhSachLoaiMonAn.get(position).tenloai


        return view
    }

    override fun getItem(position: Int): Any {
        return danhSachLoaiMonAn.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
return  danhSachLoaiMonAn.count()
    }

    private class ViewHolder {
        var imHinhLoaiThucDon: ImageView? = null
        var txtTenLoaiThucDon: TextView? = null
    }
}