//Data 클래스 생성
//문자열로 이름과 정수로 점수를 저장
class Data {
    var name: String = ""
    var score: Int = 0
}

// Data 클래스를 가지고 사용자의 업무를 처리하는 클래스 DataService
// 가장 기본적인 작업은 CRUD
// 매개변수 없이 Data 목록을 찾아오는 Method : List<Data>, Array<Data>
// 이름을 매개변수로 받아서 Data 1개를 찾아오는 메소
// Data 삽입, 삭제, 수정하는 메소드 : 리턴을 할 때는 정수(영향받은 데이터의 개수), Boolean(성공과 실패),
// Unit(리턴이 없는 경우 - 실패는 없음) 으로 작성 가능
// 삭제를 할 때는 기본키를 가지고 하거나 DTO 전체를 가지고 수행
class DataService {
    // 매개변수 없이 Data 목록을 리턴하는 메소드
//    fun List<Data> getList(){}
//
//    fun Data getData(name:String){}
//
//    fun Boolean insertData(data:Data){}
//
//    fun Boolean updatetData(data:Data){}
//
//    fun Boolean deletetData(data:Data){}

}