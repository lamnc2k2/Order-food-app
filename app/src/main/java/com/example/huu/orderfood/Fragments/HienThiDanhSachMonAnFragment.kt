package com.example.huu.orderfood.Fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.huu.orderfood.Adapters.HienThiDanhSachMonAnAdapter
import com.example.huu.orderfood.Entities.GoiMonEntity
import com.example.huu.orderfood.Entities.MonAnEntity2

import com.example.huu.orderfood.R
import com.example.huu.orderfood.Services.MonAnService
import com.example.huu.orderfood.SoLuongActivity
import com.example.huu.orderfood.Utilities.TB_GOIMON_MABAN

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HienThiDanhSachMonAnFragment : Fragment() {
    lateinit var gvHienThiMonAn: GridView
    lateinit var adapter: HienThiDanhSachMonAnAdapter
    lateinit var danhSachMonAn:List<MonAnEntity2>
    var iMaBan = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hien_thi_danh_sach_mon_an, container, false)
        gvHienThiMonAn = view.findViewById(R.id.gvHienThiMonAn) as GridView
        danhSachMonAn = MonAnService.layDanhSachMonAnTheoMaLoai(activity!!)
        adapter = HienThiDanhSachMonAnAdapter(activity!!, danhSachMonAn)
        gvHienThiMonAn.adapter = adapter
        val bundle = arguments
        if (bundle != null) {
            iMaBan = bundle.getInt("maban",0)
        }

        adapter.notifyDataSetChanged()
        gvHienThiMonAn.setOnItemClickListener { parent, _, position, id ->
            if (iMaBan != 0) {
                val intent = Intent(activity!!, SoLuongActivity::class.java)
                intent.putExtra("maban",iMaBan)
                intent.putExtra("mamonan",danhSachMonAn.get(position).mamonan)
                startActivity(intent)
            }

        }



        view.setOnKeyListener(object : View.OnKeyListener {
            override
            fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action === KeyEvent.ACTION_DOWN) {
                    fragmentManager!!.popBackStack("hienthiloai", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                }
                return false
            }
        })
        return view
    }


}
