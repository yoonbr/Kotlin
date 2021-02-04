package com.yoond.a0203listview

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

    // 하단에서 스크롤 한 것인지 여부를 저장하기 위한 프로퍼티
    var lastItemVisibleFlag = false

    // 현재 페이지 번호를 저장하기 위한 프로퍼티
    var pageno = 1

    // ListView를 다시 출력하는 핸들러
    val handler:Handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            Log.e("핸들러 호출", "핸들러 호출됨")
            Log.e("데이터", movieList!!.size.toString())
            // ListView를 다시 출력하고 프로그래스 바를 중지
            movieAdapter!!.notifyDataSetChanged()
            downloadview?.visibility = View.GONE
            th = null
        }
    }

    // 데이터를 다운로드 받아서 파싱을 수행해주는 스레드
    // MovieThread를 Thread로 부터 상속받음
    inner class MovieThread : Thread(){
        override fun run(){
            try{
                // URL 생성
                var url = URL("http://cyberadam.cafe24.com/movie/list?page=${pageno}&count=10")

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


                        // 맨 앞에 데이터를 추가
                        movieList!!.add(0, movie)

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

        // ListView의 항목 클릭 이벤트 처리
        listView?.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            // 선택한 항목의 데이터를 찾아오기
            val movie = movieList!!.get(i)
            // 넘겨줄 데이터 가져오기
            val link = movie.link
            // 인텐트를 생성해서 데이터와 함께 다른 Activity 출력
            val intent = Intent(this, LinkActivity::class.java)
            intent.putExtra("link", link)
            startActivity(intent)
        }

        // ListView의 스크롤 이벤트 처리
        listView?.setOnScrollListener(object: AbsListView.OnScrollListener{
            // 스크롤이 발생할 때 호출되는 메소드
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                // 하단에서 스크롤 했는지 여부를 판단
                // 첫번째 보여지는 데이터와 보여지는 데이터 개수를 더한 것이
                // 전체 데이터 개수보다 크거나 같다면 가장 하단으로 내려온 것
                lastItemVisibleFlag = totalItemCount > 0 &&
                        firstVisibleItem + visibleItemCount >= totalItemCount

            }
            // 스크롤의 상태가 변경될 때 호출되는 메소드
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                // 스크롤이 멈추고 가장 하단에서 스크롤 한 것이라면 데이터 업데이트
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                    && lastItemVisibleFlag == true) {
                    pageno = pageno + 1
                    val cnt = 10
                    if (pageno * cnt >= count!!) {
                        Toast.makeText(this@MovieListActivity, "더이상 데이터가 없습니다.", Toast.LENGTH_LONG)
                            .show()
                        Log.e("msg", "더 이상 데이터가 없음")
                    }else{
                        // 기존 스레드가 동작 중이면 가져오지 않음
                        if(th != null){
                            return
                        }
                        // 프로그래스 뷰를 출력하고 스레드를 새로 만들어서 시작
                        downloadview!!.visibility = View.VISIBLE
                        th = MovieThread()
                        th!!.start()
                    }
                }
            }
        })

        // 스와이프 레이아웃의 리프레시 이벤트 처리
        val swipe_layout = findViewById<SwipeRefreshLayout>(R.id.swipe_layout)
        swipe_layout.setOnRefreshListener {
            pageno = pageno + 1
            var cnt = 10
            if(pageno * cnt >= count!!){
                Log.e("message", "더이상 데이터가 존재하지 않습니다.")
            } else {
                if(th == null) {
                    downloadview?.visibility = View.VISIBLE
                    th = MovieThread()
                    th!!.start()
                    swipe_layout.isRefreshing = false
                }
            }
        }
    }
}