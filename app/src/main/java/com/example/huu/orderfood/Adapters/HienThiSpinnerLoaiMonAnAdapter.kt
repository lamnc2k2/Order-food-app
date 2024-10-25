package com.example.huu.orderfood.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.huu.orderfood.Entities.LoaiMonAnEntity2
import android.widget.TextView
import com.example.huu.orderfood.R


class HienThiSpinnerLoaiMonAnAdapter(val context: Context, val danhSachLoaiMonAn:List<LoaiMonAnEntity2>): BaseAdapter() {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View
        val viewHolder:ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_layout_spinner_loaithucdon, parent, false)
            viewHolder = ViewHolder()
            viewHolder.txtTenLoai = view.findViewById(R.id.txtTenLoai) as TextView

            view.tag = viewHolder

        } else {
            viewHolder = convertView.getTag() as ViewHolder
            view = convertView
        }
        viewHolder.txtTenLoai!!.setText(danhSachLoaiMonAn.get(position).tenloai);
        return view
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View
        val viewHolder:ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_layout_spinner_loaithucdon, parent, false)
            viewHolder = ViewHolder()
            viewHolder.txtTenLoai = view.findViewById(R.id.txtTenLoai) as TextView

            view.tag = viewHolder

        } else {
            viewHolder = convertView.getTag() as ViewHolder
            view = convertView
        }
        viewHolder.txtTenLoai!!.setText(danhSachLoaiMonAn.get(position).tenloai);
        return view
    }

    override fun getItem(position: Int): Any {
        return danhSachLoaiMonAn.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return danhSachLoaiMonAn.count()
    }


    private class ViewHolder {
        var txtTenLoai: TextView? = null
    }
}