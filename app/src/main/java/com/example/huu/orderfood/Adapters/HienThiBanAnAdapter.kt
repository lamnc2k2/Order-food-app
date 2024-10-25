package com.example.huu.orderfood.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.huu.orderfood.Entities.BanAnEntity
import com.example.huu.orderfood.Entities.BanAnEntity2
import com.example.huu.orderfood.R
import com.example.huu.orderfood.Services.BanAnService
import java.text.SimpleDateFormat
import java.util.*
import com.example.huu.orderfood.TrangChuActivity
import android.widget.Toast
import com.example.huu.orderfood.Entities.GoiMonEntity
import com.example.huu.orderfood.Entities.NhanVienEntity
import com.example.huu.orderfood.Fragments.HienThiThucDonFragment
import com.example.huu.orderfood.Services.GoiMonService
import com.example.huu.orderfood.ThanhToanActivity
import kotlin.collections.ArrayList


class HienThiBanAnAdapter(val context: Context, val danhSachBanAn: List<BanAnEntity2>) : BaseAdapter() {
    private var listStringKtTinhTrang: ArrayList<String> = arrayListOf()

    @SuppressLint("SimpleDateFormat")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_layout_hienthibanan, parent, false)
            viewHolder = ViewHolder()
            viewHolder.imBanAn = view.findViewById(R.id.imBanAn) as ImageView
            viewHolder.imGoiMon = view.findViewById(R.id.imGoiMon) as ImageView
            viewHolder.imThanhToan = view.findViewById(R.id.imThanhToan) as ImageView
            viewHolder.imAnButton = view.findViewById(R.id.imAnButton) as ImageView
            viewHolder.txtTenBanAn = view.findViewById(R.id.txtTenBanAn) as TextView

            view.tag = viewHolder

        } else {
            viewHolder = convertView.getTag() as ViewHolder
            view = convertView
        }
        val banAnEntity2 = danhSachBanAn.get(position)
        if (banAnEntity2.duocchon) {
            HienThiButton(viewHolder);
        } else {
            AnButton(viewHolder);
        }
        if (listStringKtTinhTrang.isEmpty()) {
            for (i in 0 until danhSachBanAn.count()) {
                listStringKtTinhTrang.add(BanAnService.layTinhTrangBanTheoMa(context,danhSachBanAn.get(i).maban))
            }
        }


        if (listStringKtTinhTrang.get(position).equals("true")) {
            viewHolder.imBanAn?.setImageResource(R.drawable.banantrue);
        } else {
            viewHolder.imBanAn?.setImageResource(R.drawable.banan);
        }
        val maban = danhSachBanAn.get(position).maban
        viewHolder.txtTenBanAn!!.setText(danhSachBanAn.get(position).tenban);
        viewHolder.imBanAn!!.setOnClickListener { _ ->
           banAnEntity2.duocchon = true
            HienThiButton(viewHolder)
        }
        viewHolder.imGoiMon!!.setOnClickListener { _ ->
            val tinhtrang = BanAnService.layTinhTrangBanTheoMa(context, maban)
            if (tinhtrang.equals("false")) {
// thực hiện code thêm bảng gọi món và cập nhật lại tình trạng bàn
                val calendar = Calendar.getInstance()
                val dateFormat = SimpleDateFormat("dd-MM-yy")
                val ngaygoi = dateFormat.format(calendar.getTime())
                GoiMonEntity.NGAYGOI = ngaygoi
                GoiMonEntity.MABAN = maban
                GoiMonEntity.MANV = NhanVienEntity.MANV
                GoiMonService.themGoiMon(context) { it ->
                    if (!it) {
                        Toast.makeText(context,"Gọi món thất bại", Toast.LENGTH_SHORT).show()
                    }
                }
                BanAnEntity.TINHTRANG = "true"
                BanAnService.capNhatTinhTrangBan(context, maban) {}
                BanAnEntity.TINHTRANG = "false"
            }
            val fragManager = (context as TrangChuActivity).supportFragmentManager
            val fragTrans = fragManager.beginTransaction()
            val hienThiThucDonFragment = HienThiThucDonFragment()
            val bundleDuLieuThucDon = Bundle()
            bundleDuLieuThucDon.putInt("maban",maban)
            hienThiThucDonFragment.arguments = bundleDuLieuThucDon
            fragTrans.replace(R.id.content, hienThiThucDonFragment).addToBackStack("hienthibanan")
            fragTrans.commit()
        }
        viewHolder.imThanhToan!!.setOnClickListener { _ ->
            val intent = Intent(context, ThanhToanActivity::class.java)
            intent.putExtra("maban",maban)
            context.startActivity(intent)
        }
        viewHolder.imAnButton!!.setOnClickListener { _ ->
            AnButton(viewHolder)
            banAnEntity2.duocchon = false
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return danhSachBanAn.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return danhSachBanAn.count()
    }

    private class ViewHolder {
        var imBanAn: ImageView? = null
        var imGoiMon: ImageView? = null
        var imThanhToan: ImageView? = null
        var imAnButton: ImageView? = null
        var txtTenBanAn: TextView? = null
    }

    private fun HienThiButton(viewHolder: ViewHolder) {

        viewHolder.imGoiMon!!.setVisibility(View.VISIBLE)
        viewHolder.imThanhToan!!.setVisibility(View.VISIBLE)
        viewHolder.imAnButton!!.setVisibility(View.VISIBLE)
    }

    private fun AnButton(viewHolder: ViewHolder) {


        viewHolder.imGoiMon!!.setVisibility(View.INVISIBLE)
        viewHolder.imThanhToan!!.setVisibility(View.INVISIBLE)
        viewHolder.imAnButton!!.setVisibility(View.INVISIBLE)
    }


}