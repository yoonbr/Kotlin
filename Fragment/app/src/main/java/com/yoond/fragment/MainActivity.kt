package com.yoond.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentHome: fragment_home
    private lateinit var fragmentSearch: fragment_search
    private lateinit var fragmentNote: fragment_note
    private lateinit var fragmentMap: fragment_map
    private lateinit var fragmentMypage : fragment_mypage

    companion object {
        const val TAG : String = "로그"
    }


    // 화면이 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 레이아웃과 연결
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity - onCreate() Called")

        navi_menu.setOnNavigationItemSelectedListener(onBottomNavItemSelectedListener)

        fragmentHome = fragment_home.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragment_frame, fragmentHome).commit()

    }

    // main 클래스에 implement 하지 않고 변수로 만들어서 사용가능
    // class MainActivity : AppCompatActivity() : OnNavigationItemSelectedListener {
    // BottomItemClickListener 설정

    private val onBottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {

        when(it.itemId){
            R.id.navigation_home -> {
                Log.d(TAG, "Click Home Buttom")
                fragmentHome = fragment_home.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, fragmentHome).commit()
            }
            R.id.navigation_search -> {
                Log.d(TAG, "Click Search Buttom")
                fragmentSearch = fragment_search.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, fragmentSearch).commit()
            }
            R.id.navigation_note -> {
                Log.d(TAG, "Click Note Buttom")
                fragmentNote = fragment_note.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, fragmentNote).commit()
            }
            R.id.navigation_map -> {
                Log.d(TAG, "Click Map Buttom")
                fragmentMap = fragment_map.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, fragmentMap).commit()
            }
            R.id.navigation_mypage -> {
                Log.d(TAG, "Click Mypage Buttom")
                fragmentMypage = fragment_mypage.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, fragmentMypage).commit()
            }
        }
        true
    }

    /*
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "MainActivity - onNavigationItemSelected() Called")

        when(item.itemId){
            R.id.navigation_home -> {
                Log.d(TAG, "Click Home Buttom")
            }
            R.id.navigation_search -> {
                Log.d(TAG, "Click Search Buttom")
            }
            R.id.navigation_note -> {
                Log.d(TAG, "Click Note Buttom")
            }
            R.id.navigation_map -> {
                Log.d(TAG, "Click Map Buttom")
            }
            R.id.navigation_mypage -> {
                Log.d(TAG, "Click Mypage Buttom")
            }
        }
        return true
    }
     */
}