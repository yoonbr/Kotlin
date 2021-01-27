package naver.yoond.mysqlserver;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import naver.yoond.mysqlserver.dao.ItemDAO;

// 설정 파일의 위치를 지정해서 설정 파일의 내용을 실행해서 테스트 
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })

public class ConnectTest {

	// 데이터베이스 연결 객체 
	@Autowired
	private DataSource dataSource;
	
	// Hibernate 연결 객체 
	@Autowired
	private SessionFactory factory;
	
	// ItemDAO 연결 객체 
	@Autowired
	private ItemDAO itemDao;
	
	@Test
	@Transactional
	public void connectTest() throws Exception{
		// 데이터베이스 연결 객체의 생성 여부를 출력 
		// System.err.println(dataSource.getConnection().toString());
		
		// Hibernate 연결 객체 출력 
		// System.err.println(factory.toString());
		
		// ItemDAO 연결 객체 출력 
		// System.err.println("데이터 개수 : " + itemDao.count());
		// System.err.println("데이터 목록 : " + itemDao.list());
		
		Map<String, Object> map = 
				new HashMap<String, Object>();
		
		map.put("start", 0); // 시작번호 
		map.put("size", 5); // 갯수 
	
		map.put("searchtype", "both"); // null=전체 데이터의 개수를 가져옴, itemname=과일이름, description=설명, both=둘다
		map.put("keyword", "비타민");
		
		System.err.println("데이터 개수 : " + itemDao.count(map));
		System.err.println("데이터 목록 : " + itemDao.list(map));
	}

}
