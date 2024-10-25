package com.example.huu.orderfood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.MenuItem
import android.widget.TextView
import com.example.huu.orderfood.Entities.NhanVienEntity
import com.example.huu.orderfood.Fragments.HienThiBanAnFragment
import com.example.huu.orderfood.Fragments.HienThiThucDonFragment
import kotlinx.android.synthetic.main.activity_trang_chu.*

class TrangChuActivity : AppCompatActivity(){
        private lateinit var fragmentManager:FragmentManager
    private lateinit var fragmentTransaction:FragmentTransaction
    private lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_trang_chu)
        setSupportActionBar(toolbar)
        //lấy view của layout Header_navigation
        val view = navigationview_trangchu.getHeaderView(0)
        //sau khi lấy được view header thì set text thuộc tính txtTenNhanVien có trong nó
        val txtTenNhanVien_Navigation:TextView = view.findViewById(R.id.txtTenNhanVien_Navigation)
            txtTenNhanVien_Navigation.setText(NhanVienEntity.TENDN)
        //Gọi ActionBarDrawerToggle
        val drawerToggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.mo,R.string.dong)
        //Tạo sự kiện lắng nghe drawerToggle
        drawerLayout.addDrawerListener(drawerToggle)
        //Đồng bộ trạng thái
        drawerToggle.syncState()
        //Set itemIcon về màu mặc định của Icon
        navigationview_trangchu.itemIconTintList =null
        //Tạo sự kiện click cho các item menu có trong navigation
        fragmentManager = supportFragmentManager
        navigationview_trangchu.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itTrangChu -> hienThiFragmentBanAn(menuItem)
                R.id.itThucDon -> hienThiThucDon(menuItem)


            }
return@setNavigationItemSelectedListener false
        }
        //Chọn fragment mặc định được hiển thị tại frame layout
        fragmentTransaction = fragmentManager.beginTransaction()
        fragment = HienThiBanAnFragment()
        fragmentTransaction.replace(R.id.content, fragment)
        fragmentTransaction.commitNow()
    }

    private fun hienThiThucDon(menuItem: MenuItem) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragment = HienThiThucDonFragment()
        fragmentTransaction.replace(R.id.content, fragment)
        fragmentTransaction.commit()
        //khi click vào menu item nào thì menu item đó sẽ được check
        menuItem.setChecked(true)
        //sau khi bấm vào menu item để hiển thị fragment khác thì cần gọi dòng này để tự động đóng thằng drawer lại
        //Gravity.START có liên quan với tools:openDrawer="start"
        drawerLayout.closeDrawer(Gravity.START)
    }

    private fun hienThiFragmentBanAn(menuItem: MenuItem) {
        fragmentTransaction = fragmentManager.beginTransaction()
        fragment = HienThiBanAnFragment()
        fragmentTransaction.replace(R.id.content, fragment)
       fragmentTransaction.commit()
        //khi click vào menu item nào thì menu item đó sẽ được check
        menuItem.setChecked(true)
        //sau khi bấm vào menu item để hiển thị fragment khác thì cần gọi dòng này để tự động đóng thằng drawer lại
        //Gravity.START có liên quan với tools:openDrawer="start"
        drawerLayout.closeDrawer(Gravity.START)

    }
}


