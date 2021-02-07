package itstudy.kakao.supportdesign0204

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yoond.a0204supportDesign.R

class AudioPlayActivity : AppCompatActivity() {
    //오디오 재생기
    private var mediaPlayer:MediaPlayer? = null
    //재생 위치를 저장할 프로퍼티
    private var playbackPosition:Int = 0

    //오디오 재생기 정리를 하는 메소드
    private fun killMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer!!.release()
        }
    }

    //오디오 재생을 위한 메소드
    private fun playAudio(){
        killMediaPlayer() //MediaPlayer가 실행중이면 종료

        //로컬에 있는 음원을 재생
        /*
        mediaPlayer = MediaPlayer.create(
            this, R.raw.birthday)

         */

        //서버에 있는 음원을 재생
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setDataSource("http://cyberadam.cafe24.com/song/tears.mp3")
        mediaPlayer!!.prepare()

        mediaPlayer!!.start()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_play)


        //버튼 찾아오기
        val startBtn = findViewById<Button>(R.id.playBtn)
        val pauseBtn = findViewById<Button>(R.id.pauseBtn)
        val restartBtn = findViewById<Button>(R.id.restartBtn)

        startBtn.setOnClickListener {
            playAudio()
        }

        pauseBtn.setOnClickListener {
            if(mediaPlayer != null){
                //재생 중인 위치를 저장
                playbackPosition = mediaPlayer!!.currentPosition
                mediaPlayer!!.pause()
            }
        }

        restartBtn.setOnClickListener {
            //재생 중인지 확인해서 재생이 멈춘 상태이면 재생
            if(mediaPlayer != null && !mediaPlayer!!.isPlaying){
                mediaPlayer!!.start()
                mediaPlayer!!.seekTo(playbackPosition)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        killMediaPlayer()
    }
}