package com.example.huu.orderfood.Fragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

import com.example.huu.orderfood.R
import com.example.huu.orderfood.ThemBanAnActivity
import android.widget.GridView
import com.example.huu.orderfood.Adapters.HienThiBanAnAdapter
import com.example.huu.orderfood.Entities.BanAnEntity2
import com.example.huu.orderfood.Services.BanAnService
import com.example.huu.orderfood.TrangChuActivity
import android.view.ContextMenu
import android.widget.AdapterView
import android.widget.Toast
import com.example.huu.orderfood.SuaBanAnActivity
import com.example.huu.orderfood.Utilities.REQUEST_CODE_SUA
import android.R.attr.data




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HienThiBanAnFragment : Fragment() {
    lateinit var danhSachBanAn:List<BanAnEntity2>
    lateinit var adapter: HienThiBanAnAdapter
    lateinit var gvHienThiBanAn:GridView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hien_thi_ban_an, container, false)
        //Khai báo rằng fragment này có option menu
        setHasOptionsMenu(true)
        val activityTrangChuActivity:TrangChuActivity? = activity as TrangChuActivity?
        activityTrangChuActivity?.supportActionBar!!.setTitle(R.string.banan)
        gvHienThiBanAn = view.findViewById(R.id.gvHienBanAn) as GridView
        hienThiDanhSachBanAn()
        registerForContextMenu(gvHienThiBanAn)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        //thêm item menu sẵn có không cần inflate menu
        if (menu != null) {
            val itThemBanAn = menu.add(1,R.id.itThemBanAn,1,R.string.thembanan)
            //tạo icon cho menu item
            itThemBanAn.setIcon(R.drawable.thembanan)
            //hiển thị menu item nếu còn chỗ trống trên toolbar
            itThemBanAn.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }


    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        activity!!.menuInflater.inflate(R.menu.edit_context_menu, menu)

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            val menuInfo:AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val vitri = menuInfo.position
            val maban = danhSachBanAn.get(vitri).maban
            when (item.itemId) {
                R.id.itSua -> suaBanAn(maban)
                R.id.itXoa -> xoaBanAn(maban)
            }
        }

        return super.onContextItemSelected(item)
    }

    private fun xoaBanAn(maban: Int) {
        BanAnService.xoaBanAnTheoMa(activity!!, maban) {
            it->
            if (it) {
                hienThiDanhSachBanAn()
                Toast.makeText(activity!!, resources.getString(R.string.xoathanhcong),Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity!!, resources.getString(R.string.loi),Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun suaBanAn(maban: Int) {

        val intent = Intent(activity, SuaBanAnActivity::class.java)
        intent.putExtra("maban",maban)
        startActivityForResult(intent, REQUEST_CODE_SUA)
    }

    override fun onResume() {
        super.onResume()
        hienThiDanhSachBanAn()
    }





    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.itThemBanAn -> themBanAn()
        }
        return true
    }

    private fun themBanAn() {
        val intent = Intent(activity, ThemBanAnActivity::class.java)
        startActivity(intent)
    }

    private fun hienThiDanhSachBanAn() {
        danhSachBanAn = BanAnService.layTatCaBanAn(activity!!)
        adapter = HienThiBanAnAdapter(activity!!,danhSachBanAn)
        gvHienThiBanAn.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SUA) {
            if (resultCode == Activity.RESULT_OK) {
                val intent = data
                val kiemtra = intent?.getBooleanExtra("kiemtra", false)
                hienThiDanhSachBanAn()
                if (kiemtra!!) {
                    Toast.makeText(activity, resources.getString(R.string.suathanhcong), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, resources.getString(R.string.loi), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
