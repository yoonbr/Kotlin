package com.yoond.a0204supportDesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

//가장 기본적인 Fragment : layout을 출력
class TabOne: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab_one,
            container, false) as LinearLayout
    }
}

class TabTwo: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab_two,
            container, false) as LinearLayout
    }
}

class TabThree: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab_three,
            container, false) as LinearLayout
    }
}

class MyPagerAdapter(fm: FragmentManager):
    FragmentPagerAdapter(fm!!,
        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    //Fragment 모임을 저장할 변수
    var fragments:MutableList<Fragment> = ArrayList()
    //각 탭의 타이틀
    var title = arrayOf("Tab1", "Tab2", "Tab3")

    //인덱스에 따라 출력할 Fragment를 리턴해주는 메소드
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
    //출력할 Fragment 개수를 설정하는 메소드
    override fun getCount(): Int {
        return fragments.size
    }

    //각 Fragment의 타이틀을 설정하는 메소드
    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }

    //초기화 메소드
    init{
        fragments.add(TabOne())
        fragments.add(TabTwo())
        fragments.add(TabThree())
    }
}

class ViewPagerTabActivity : AppCompatActivity() {
    var viewPager: ViewPager? = null
    var fab: FloatingActionButton? = null
    var relativeLayout: RelativeLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_tab)

        //뷰 찾아오기
        viewPager = findViewById<ViewPager>(R.id.viewpager)
        fab = findViewById<FloatingActionButton>(R.id.fab)
        relativeLayout = findViewById<RelativeLayout>(R.id.container)

        //뷰 페이지 어댑터 설정하기
        viewPager!!.adapter = MyPagerAdapter(supportFragmentManager)

        //탭과 뷰 페이저 연결
        val tab = findViewById<TabLayout>(R.id.tabs)
        tab.setupWithViewPager(viewPager)

        fab!!.setOnClickListener{
            Snackbar.make(relativeLayout!!, "I am snackbar", Snackbar.LENGTH_LONG)
                .setAction("동작", View.OnClickListener { }).show()
        }

    }
}