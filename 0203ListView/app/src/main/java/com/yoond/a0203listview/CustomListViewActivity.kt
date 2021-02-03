package com.yoond.a0203listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import android.widget.ListView

class CustomListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list_view)

        // 출력할 데이터를 생성
        val data:MutableList<VO> = ArrayList<VO>()

        var vo = VO()
        vo.icon = R.mipmap.ic_launcher
        vo.name = "자유게시판"
        data.add(vo)

        vo = VO()
        vo.icon = R.mipmap.ic_launcher_round
        vo.name = "익명게시판"
        data.add(vo)

        vo = VO()
        vo.icon = R.mipmap.ic_launcher_round
        vo.name = "판매게시판"
        data.add(vo)

        // 어댑터 생성
        val adapter = MyAdapter(this, data, R.layout.icontext)

        // ListView에 어댑터를 설정
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        // 애니메이션 집합 만들기
        val set = AnimationSet(true)

        // 위치 이동하는 애니메이션
        val trl = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,0.0f
        )
        trl.duration = 1000
        set.addAnimation(trl)

        // 알파 애니메이션
        val alpha = AlphaAnimation(0.0f, 1.0f)
        alpha.duration = 1000
        set.addAnimation(alpha)

        // 애니메이션 설정
        listView.layoutAnimation = LayoutAnimationController(set, 0.5f)
    }
}