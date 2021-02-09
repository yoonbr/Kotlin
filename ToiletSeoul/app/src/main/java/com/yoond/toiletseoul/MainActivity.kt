package com.yoond.toiletseoul

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.pedro.library.AutoPermissions
import com.pedro.library.AutoPermissionsListener

class MainActivity : AppCompatActivity(), AutoPermissionsListener {
    // map의 기본 zoom level
    val DEFAULT_ZOOM_LEVEL = 17F
    // 위치 정보를 가져오지 못했을 때 사용할 기본 위치 정보 (시청)
    val CITY_HALL = LatLng(37.5662952, 126.9779450)
    // 구글 맵을 위한 프로퍼티
    var googleMap: GoogleMap? = null
    // 권한 설정 여부 저장 프로퍼티
    var isGranted = false
    // 맵 뷰를 참조할 프로퍼티
    var mapView:MapView? = null

    // 메인 뷰를 저장할 프로퍼티
    var mainContainer:ConstraintLayout? = null

    // 현재 위치를 리턴해주는 함수
    @SuppressLint("MissingPermission")
    fun getMyLocation():LatLng {
        // 위치 확인을 위한 공급자 생성
        val locationProvider: String = LocationManager.GPS_PROVIDER
        // 위치 서비스 객체를 가져오기
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // 마지막 업데이트 된 위치 가져오기
        val lastLocation: Location? = locationManager.getLastKnownLocation(locationProvider)
        Log.e("위도 : ", lastLocation!!.latitude.toString())
        Log.e("경도 : ", lastLocation!!.longitude.toString())
        return LatLng(lastLocation!!.latitude, lastLocation!!.longitude)
    }

    // Activity가 생성될 때 호출되는 메소드
    // 맨 처음 한 번만 수행할 작업을 수행
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 디자인 한 뷰 찾아오기
        mainContainer = findViewById(R.id.mainContainer)
        mapView = findViewById(R.id.mapView)
            mapView!!.onCreate(savedInstanceState)

        var myLocationButton:FloatingActionButton =
                findViewById(R.id.myLocationButton)

        // 앱이 실행되면 지도를 출력
        if(isGranted) {
            initMap()
        } else {
            // 권한 요청
            AutoPermissions.loadAllPermissions(this, 101)
        }

        // 버튼 눌렀을 때 처리
        myLocationButton.setOnClickListener{
            onMyLocationButtonClick()
        }

    }


    // activity 화면에 보여질 때
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    // activity 비활성화 될 때
    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    // activity 파괴될 때
    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    // 배터리 부족시
    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    // 맵을 초기화해주는 사용자 정의 함수
    @SuppressLint("MissingPermission")
    fun initMap(){
        // 맵 뷰에서 구글 맵을 호출하는 함수
        Log.e("맵 호출", "맵 ") // success
        mapView?.getMapAsync {
            // 구글 맵 객체 저장
            googleMap = it
            // 현재 위치로 이동 버튼 비활성화
            it.uiSettings.isMyLocationButtonEnabled = false
            // 위치 사용 권한에 따른 분기
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
                } else -> {
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
    fun onMyLocationButtonClick(){
        when {
            isGranted ->
                googleMap!!.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(
                                getMyLocation(), DEFAULT_ZOOM_LEVEL
                        )
                )
            else ->
                Snackbar.make(mainContainer!!, "위치 사용 권한 없음.", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // 권한 허락의 결과를 AutoPermissions에게 전달
        AutoPermissions.parsePermissions(
                this, requestCode, permissions as Array<String>, this
        )
    }

    // 거부한 권한의 집합을 알려주는 메소드
    override fun onDenied(requestCode: Int, permissions: Array<String>) {
        // 스낵바를 이용해서 거부한 권한의 개수를 출력
        Snackbar.make(mainContainer!!, "거부한 권한 : " + permissions.size + "개",
                Snackbar.LENGTH_LONG).show()
        // 거부한 권한이 1개라도 있으면 그 내용을 표시
        if (permissions.size == 0) {
            isGranted = true
        } else {
            isGranted = false
        }
    }

    // 허가한 권한들의 집합을 알려주는 메소드
    override fun onGranted(requestCode: Int, permissions: Array<String>) {
        // 스낵바를 이용해서 허용한 권한의 개수를 출력
        Snackbar.make(mainContainer!!, "허용한 권한 : " + permissions.size + "개",
                Snackbar.LENGTH_LONG).show()

        // 맵을 초기화해주는 메소드를 호출
        initMap()
    }
}