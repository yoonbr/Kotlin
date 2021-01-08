fun main(){
    val freitagbag =
        arrayListOf<String>(
            "Jamie", "Dexter", "Hawaiifiveo", "Lassie")
    val freitagacc =
        arrayListOf<String>(
            "secrid", "Blair", "Fay")

    // 2차원 배열 생성
    var freitag =
        mutableListOf<List<String>>(
            freitagbag, freitagacc)
    /*
    // 데이터 목록 추가 - 출력하는 부분을 수정해야 함
    val freitagcase =
        arrayListOf<String>("Lobin", "Fox")
    freitag.add(freitagcase)
     */

    // 2차원 배열 출력
    for(ar in freitag){
        println(ar)
    }
    // 출력은 되지만 어떤 목록인지 구분이 안됨
    for(idx in freitag.indices){
        if(idx == 0)
            print("freitagbag : ")
        else
            print("freitagacc : ")
        println(freitag.get(idx))
    }

    // Map의 List 생성
    val freitagMap =
        mutableListOf<MutableMap<String, Any>>()
    val map1 =
        mutableMapOf<String, Any>()
    map1.put("name", "freitagbag")
    map1.put("freitag", freitagbag)
    freitagMap.add(map1)

    val map2 =
        mutableMapOf<String, Any>()
    map2.put("name", "freitagacc")
    map2.put("freitag", freitagacc)
    freitagMap.add(map2)

    val go = arrayListOf<String>("Lobin", "Fox")
    val map3 =
        mutableMapOf<String, Any>()
    map3.put("name", "freitagcase")
    map3.put("freitag", go)
    freitagMap.add(map3)

    // 출력
//    for(map in freitagMap){
//        println("${map.get("name")}:${map.get("freitag")}")
//    }
    for(map in freitagMap){
        println("${map.get("name")}:")
        // Any로 만들어진 데이터를 출력하는 것이 아닌 목적으로
        // 사용하는 경우는 원래의 자료형으로 형변환해서 사용해야함
        for(list in map.get("freitag") as List<String>){
            print("${list}")
        }
        println()
    }
}