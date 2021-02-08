package com.yoond.a0208customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

// 하나의 점의 좌표를 저장할 DTO 클래스
// x와 y는 좌표, isDraw는 선을 그려야 하는 좌표인지 구분하기 위한 프로퍼티
class Vertex internal constructor(var x:Float, var y:Float, var isDraw:Boolean)


// View 클래스로부터 상속
// View 클래스의 Default Constructor가 없으므로 생성자를 만들어서 필요한 데이터를 대입해주어야 함
class MyCustomView(context: Context?) : View(context){
    // 화면에 출력될 때 호출되는 함수 - 뷰를 그리는 함수
    override fun onDraw(canvas: Canvas?){
        super.onDraw(canvas)

        // Paint 객체 생성
        val pnt = Paint()
        pnt.setColor(Color.BLUE);
        // 단위 붙여주어야 함(F)
        pnt.strokeWidth = 30F

        // 캔버스를 이용해서 그리기
        canvas?.drawColor(Color.WHITE) // 전경색
        canvas?.drawCircle(100F, 100F, 80F, pnt)
        canvas?.drawLine(10F, 100F, 300F, 100F, pnt)

    }
}