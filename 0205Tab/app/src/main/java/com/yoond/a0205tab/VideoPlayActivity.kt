package com.yoond.a0205tab

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView

class VideoPlayActivity : AppCompatActivity() {
    //비디오 재생 URL
    val url = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)

        //디자인 한 버튼과 VideoView 가져오기
        val startBtn = findViewById<Button>(R.id.startBtn)
        val volumeBtn = findViewById<Button>(R.id.volumeBtn)
        val videoView = findViewById<VideoView>(R.id.videoView)

        //비디오 재생 버튼을 누르면 처리하는 코드
        startBtn.setOnClickListener {
            videoView.seekTo(0)
            videoView.start()
        }

        //볼륨 조절 버튼을 누르면 처리하는 코드
        volumeBtn.setOnClickListener {
            //볼륨 조절 매니저 찾아오기
            val mAudionManager =
                getSystemService(Context.AUDIO_SERVICE) as AudioManager

            //최대 볼륨 찾아오기
            val maxVolume =
                mAudionManager.getStreamMaxVolume((
                        AudioManager.STREAM_MUSIC))
            mAudionManager.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                maxVolume, AudioManager.FLAG_SHOW_UI)

        }

        //비디오 뷰 제어를 위한 MediaController 객체
        val mc = MediaController(this)
        videoView.setMediaController(mc)
        videoView.setVideoURI(Uri.parse(url))
        videoView.requestFocus()

        //재생 준비가 되면 메시지를 출력하도록 리스너를 설정
        videoView.setOnPreparedListener(
            MediaPlayer.OnPreparedListener {
                Log.e("메시지", "동영상 재생이 준비되었습니다.")
            })

        //재생이 완료되면 메시지를 출력하도록 리스너를 설정
        videoView.setOnCompletionListener(
            MediaPlayer.OnCompletionListener {
                Log.e("메시지", "동영상 재생이 완료되었습니다.")
            })
    }
}