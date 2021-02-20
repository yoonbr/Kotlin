package com.yoond.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class fragment_note : Fragment() {

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : fragment_note {
            return fragment_note()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Fragment - onCreate() called")
    }

    // 프래그먼트를 안고 있는 액티비에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "Fragment - onAttach() called")
    }

    // 뷰가 생성되었을 때
    // 프래그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "Fragment - onCreateView() called")

        val view = inflater.inflate(R.layout.fragment_note, container, false)

        return view
    }

}
