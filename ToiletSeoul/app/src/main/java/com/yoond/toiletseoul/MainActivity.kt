package com.yoond.toiletseoul

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.maps.android.clustering.ClusterManager
import com.pedro.library.AutoPermissions
import com.pedro.library.AutoPermissionsListener
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    // 구글 맵을 위한 프로퍼티
    lateinit var googleMap: GoogleMap

    // 권한 설정 여부 저장 프로퍼티
    var isGranted = false

    // 맵 뷰를 참조할 프로퍼티
    lateinit var mapView: MapView

    // 메인 뷰를 저장할 프로퍼티
    lateinit var mainContainer: ConstraintLayout

    // 화장실 위도와 경도를 저장할 List
    val toiletList:MutableList<Map<String, Any>> = mutableListOf<Map<String,Any>>()

    // 화장실 이미지 생성 - 지연 생성
    val bitmap by lazy {
        val drawable = resources.getDrawable(
                R.drawable.restroom_sign, null
        ) as BitmapDrawable
        Bitmap.createScaledBitmap(drawable.bitmap, 64,64, false)
    }

    companion object {
        //위치 권한 키
        private const val KEY_LOCATION_PERMISSEN = 101

        // map의 기본 zoom level
        private const val DEFAULT_ZOOM_LEVEL = 17F

        // 위치 정보를 가져오지 못했을 때 사용할 기본 위치 정보 (시청)
        private val CITY_HALL = LatLng(37.5662952, 126.9779450)
    }

    // 클러스터링을 위한 프로퍼티
    lateinit var clusterManager : ClusterManager<MyItem>
    lateinit var clusterRenderer: ClusterRenderer

    // 현재 위치를 리턴해주는 함수
    @SuppressLint("MissingPermission")
    private fun getMyLocation(): LatLng {
        // 위치 확인을 위한 공급자 생성
        val locationProvider: String = LocationManager.NETWORK_PROVIDER
        // 위치 서비스 객체를 가져오기
        val locationManager: LocationManager =
                this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // 마지막 업데이트 된 위치 가져오기
        val lastLocation: Location? = locationManager.getLastKnownLocation(locationProvider)

        return if (lastLocation != null) {
            Log.e("위도 : ", lastLocation.latitude.toString())
            Log.e("경도 : ", lastLocation.longitude.toString())

            LatLng(lastLocation.latitude, lastLocation.longitude)
        } else {
            Snackbar.make(mainContainer, "위치 경로를 찾을 수 없음.", Snackbar.LENGTH_LONG).show()
            // 위치 경로를 찾을 수 없을 때 시청의 정보를 반환
            CITY_HALL
        }
    }

    private fun addMarker(toilet:MutableMap<String, Any>) {
        /*
        googleMap.addMarker(
                MarkerOptions()
                        .position(LatLng(toilet.get("LAT") as Double,
                        toilet.get("LNG") as Double))
                        .title(toilet.get("FNAME") as String)
                        .snippet(toilet.get("ANAME") as String)
                        .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
        )
         */

        // ClusterManager를 이용해서 마커 출력
        clusterManager?.addItem(
                MyItem(
                    LatLng(toilet.get("LAT") as Double,
                    toilet.get("LNG") as Double),
                        toilet.get("FNAME") as String,
                        toilet.get("ANAME") as String,
                        BitmapDescriptorFactory.fromBitmap(bitmap)
                )
        )
    }

    // 스레드로부터 메시지를 받아서 addMarker를 호출하는 핸들러
    val handler = object:Handler(Looper.getMainLooper()){
        fun run() {
            fun handleMessage(msg: Message) {
                // 화장실 정보 목록을 순회하면서 addMarker를 호출
                for(temp in toiletList){
                    addMarker(temp as MutableMap<String, Any>)
                    clusterManager.cluster()
                }
            }
        }
    }


    // json 문자열을 다운로드 받는 스레드
    inner class ToiletThread : Thread() {
        override fun run(){
            // 시작 인덱스와 종료 인덱스를 저장할 프로퍼티
            var startIdx = 1
            var endIdx = 100

            // 데이터 전체 개수를 저장하기 위한 프로퍼티
            var count = 0

            do {
                // URL 생성
                var addr = "http://openapi.seoul.go.kr:8088/4c4f51745a796f6f3138455468654b/" +
                        "json/SearchPublicToiletPOIService/${startIdx}/${endIdx}/"
                val url = URL(addr)
                // URL 연결 객체 생성
                val connection = url.openConnection()

                // 데이터를 바이트 단위로 읽어서 문자열로 변환
                // 문자열의 길이가 짧을 때 사용
                val data = connection.getInputStream().readBytes().toString(charset("UTF-8"))

                // 받아온 문자열을 JSONObject로 변환
                var jsonData = JSONObject(data)
                // SearchPublicToiletPOIService 키로 객체 가져오기
                var root = jsonData.getJSONObject("SearchPublicToiletPOIService")

                // 데이터 개수 찾아서 count에 저장
                count = root.getInt("list_total_count")

                // 데이터 배열 찾아오기 - Key name : row
                val row = root.getJSONArray("row")

                for(i in 0 until row.length()){
                    // 배열 안의 요소들을 가져오기
                    val obj = row.getJSONObject(i)
                    // 필요한 속성들을 찾아서 하나의 객체를 만들고 이 객체를 리스트에 추가
                    val map = mutableMapOf<String, Any>()
                    // FNAME(문자열), ANAME(문자열), Y_WGS84(실수), X_WGS84(실수)
                    map.put("FNAME", obj.getString("FNAME"))
                    map.put("ANAME", obj.getString("ANAME"))
                    map.put("LAT", obj.getDouble("Y_WGS84"))
                    map.put("LNG", obj.getDouble("X_WGS84"))
                    toiletList.add(map)
               }
                startIdx = startIdx + 100
                endIdx = endIdx + 100

            } while (startIdx < count)
            Log.e("가져온 데이터", toiletList.toString())
            // 핸들러를 호출해서 UI갱신 요청
            handler.sendEmptyMessage(1)
        }
    }

    var toiletThread: ToiletThread? = null
    // 앱이 활성화 될 때 호출되는 메소드 : onResume에서 처리
    override fun onStart(){
        super.onStart()
        // 스레드가 동작중이지 않으면 스레드를 시작
        if(toiletThread == null) {
            toiletThread = ToiletThread()
            toiletThread!!.start()
        }
    }

    // 앱이 비활성화 될 때 호출되는 메소드 : onPause 에서 처리
    override fun onStop() {
        super.onStop()
        // 동작 중이면 스레드를 먼추고 삭제
        toiletThread!!.isInterrupted
        toiletThread = null
    }

    // Activity가 생성될 때 호출되는 메소드
    // 맨 처음 한 번만 수행할 작업을 수행
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 디자인 한 뷰 찾아오기
        mainContainer = findViewById(R.id.mainContainer)
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)

        var myLocationButton: FloatingActionButton =
                findViewById(R.id.myLocationButton)

        // 권한 요청
        if (android.os.Build.VERSION.SDK_INT >= 23) permissionCheck()

        // 버튼 눌렀을 때 처리
        myLocationButton.setOnClickListener {
            permissionCheck()
            onMyLocationButtonClick()
        }
    }

    //권한 체크
    private fun permissionCheck() {
        if (ContextCompat.checkSelfPermission(
                        this,
                        ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            //Granted가 아닌 상태라면 권한 요청 하는 메서드
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(ACCESS_FINE_LOCATION),
                    KEY_LOCATION_PERMISSEN
            )
        } else {
            //권한이 설정되어 있으면 True로 셋팅
            isGranted = true
        }
    }

    // activity 화면에 보여질 때
    override fun onResume() {
        super.onResume()
        mapView.onResume()

        // 권한이 허용된 상태라면 지도 로드
        // onResume으로 호출을 해 뷰가 다시 로드 될때마다 실행(이 단계에선 상관없지만 다른 페이지가 추가될때의 사이드 이펙트를 고려했음)
        if (isGranted) initMap()
    }

    // activity 비활성화 될 때
    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    // activity 파괴될 때
    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    // 배터리 부족시
    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    // 맵을 초기화해주는 사용자 정의 함수
    @SuppressLint("MissingPermission")
    private fun initMap() {
        // 맵 뷰에서 구글 맵을 호출하는 함수
        Log.e("맵 호출", "맵 ") // success
        mapView.getMapAsync {
            // 클러스터링을 위한 초기화 작업
            clusterManager = ClusterManager(this, it)
            clusterRenderer = ClusterRenderer(this, it, clusterManager)
            it.setOnCameraIdleListener(clusterManager)
            it.setOnMarkerClickListener(clusterManager)

            // 구글 맵 객체 저장
            googleMap = it
            // 현재 위치로 이동 버튼 비활성화
            it.uiSettings.isMyLocationButtonEnabled = false
            // 위치 사용 권한에 따른 분기
            Log.e("isGranted", isGranted.toString())
            when {
                isGranted -> {
                    // 현재 위치 활성화
                    it.isMyLocationEnabled = true

                    // 지도 이동
                    it.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                    getMyLocation(), DEFAULT_ZOOM_LEVEL
                            )
                    )
                }
                else -> {
                    // 서울 시청으로 이동
                    it.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                    CITY_HALL, DEFAULT_ZOOM_LEVEL
                            )
                    )
                }
            }
        }
    }

    // 플로팅 액션 버튼을 누를 때 호출될 함수
    // 권한 여부에 따라 지도의 포커스를 현재 위치로 옮기는 역할
    private fun onMyLocationButtonClick() {
        when {
            isGranted ->
                googleMap.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(
                                getMyLocation(), DEFAULT_ZOOM_LEVEL
                        )
                )
            else ->
                Snackbar.make(mainContainer, "위치 사용 권한 없음.", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == KEY_LOCATION_PERMISSEN) {
            when (PackageManager.PERMISSION_GRANTED) {
                grantResults[0] -> {
                    // 권한 설정 완료
                    // 스낵바를 이용해서 허용한 권한의 개수를 출력
                    Snackbar.make(
                            mainContainer, "허용한 권한 : " + permissions.size + "개",
                            Snackbar.LENGTH_LONG
                    ).show()

                    isGranted = true
                }
                else -> {
                    // 권한 설정 실패
                    // 스낵바를 이용해서 거부한 권한의 개수를 출력
                    Snackbar.make(
                            mainContainer, "거부한 권한 : " + permissions.size + "개",
                            Snackbar.LENGTH_LONG
                    ).show()
                    // 거부한 권한이 1개라도 있으면 그 내용을 표시
                }
            }
        }
    }
}