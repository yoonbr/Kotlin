package com.yoond.a0203listview

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.nio.Buffer

class MovieListActivity : AppCompatActivity() {
    // 다운로드 받은 문자열을 저장할 변수
    var json:String? = null
    // 데이터를 다운받아 파싱을 수행해 줄 스레드 변수
    var th : MovieThread? = null

    // 데이터 목록을 저장할 변수
    var movieList : MutableList<Movie>? = null

    // 데이터 개수를 저장할 변수
    var count:Int? = null

    // ListView에 출력하기 위한 Adapter
    // var movieAdapter : ArrayAdapter<Movie>? = null
    var movieAdapter : MovieAdapter? = null

    // ListView
    var listView : ListView? = null

    // 다운로드 진행 상황을 출력할 프로그래스 바
    var downloadview : ProgressBar? = null

    // ListView를 다시 출력하는 핸들러
    val handler:Handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            Log.e("핸들러 호출", "핸들러 호출됨")
            Log.e("데이터", movieList!!.size.toString())
            // ListView를 다시 출력하고 프로그래스 바를 중지
            movieAdapter!!.notifyDataSetChanged()
            downloadview?.visibility = View.GONE
        }
    }

    // 데이터를 다운로드 받아서 파싱을 수행해주는 스레드
    // MovieThread를 Thread로 부터 상속받음
    inner class MovieThread : Thread(){
        override fun run(){
            try{
                // URL 생성
                var url = URL("http://cyberadam.cafe24.com/movie/list")

                // 다운로드 옵션 생성
                val con = url.openConnection() as HttpURLConnection

                con.requestMethod = "GET"
                con.useCaches = false
                con.connectTimeout = 30000

                // 문자열을 다운로드 받기 위한 스트림을 생성
                val br = BufferedReader(InputStreamReader(con.inputStream))

                // 중간 중간 문자열을 저장하기 위한 객체를 생성
                val sb = StringBuilder()

                // 문자열 읽어오기
                while(true){
                    val line = br.readLine()
                    if(line == null){
                        break
                    }
                    sb.append(line)
                }

                json = sb.toString()


            }catch (e:Exception) {
                Log.e("다운로드 실패", e.message!!)
            }

            // 출력 테스트
            // Log.e("json", json.toString())

            try{
                if(json != null){
                    val data = JSONObject(json)
                    // 데이터 개수 설정
                    count = data.getInt("count")
                    // 데이터 목록 (배열 -> JSONArray)
                    val list = data.getJSONArray("list")
                    var i = 0
                    while(i < list.length()){
                        // 배열의 요소를 하나씩 가져오기
                        val item = list.getJSONObject(i)
                        val movie = Movie()
                        movie.movieid = item.getInt("movieid")
                        movie.title = item.getString("title")
                        movie.subtitle = item.getString("subtitle")
                        movie.pubdate = item.getString("pubdate")
                        movie.genre = item.getString("genre")
                        movie.rating = item.getDouble("rating")
                        movie.thumbnail = item.getString("thumbnail")
                        movie.link = item.getString("link")

                        movieList!!.add(movie)

                        i = i + 1
                    }
                }
            }catch (e:Exception) {
                Log.e("파싱 실패", e.message!!)
            }

            Log.e("데이터 개수", count.toString())
            Log.e("영화 목록", movieList.toString())

            // 핸들러를 호출해서 ListView 를 다시 출력해달라고 요청
            handler.sendEmptyMessage(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        // parsing한 영화 목록을 저장할 List 생성
        movieList = mutableListOf<Movie>()

        // 뷰 찾아오기
        listView = findViewById<ListView>(R.id.listview)
        downloadview = findViewById<ProgressBar>(R.id.downloadview)

        // ListView와 movieList 연결
        /*
        movieAdapter = ArrayAdapter<Movie>(this,
            android.R.layout.simple_expandable_list_item_1, movieList!!)
        listView?.adapter = movieAdapter

         */
        movieAdapter = MovieAdapter(this, movieList!!, R.layout.movie_cell)

        listView?.adapter = movieAdapter

        // 옵션 설정
        listView?.divider = ColorDrawable(Color.GREEN)
        listView?.dividerHeight = 5


        // 스레드를 생성하고 시작
        if(th != null){
            return
        }
        th=MovieThread()
        th!!.start()
    }
}