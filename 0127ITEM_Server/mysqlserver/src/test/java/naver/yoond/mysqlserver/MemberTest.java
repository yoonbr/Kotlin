package naver.yoond.mysqlserver;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import naver.yoond.mysqlserver.dao.MemberDAO;
import naver.yoond.mysqlserver.domain.Member;
import naver.yoond.mysqlserver.util.CryptoUtil;

// 설정 파일의 위치를 지정해서 설정 파일의 내용을 실행해서 테스트 
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })

public class MemberTest {

	// 데이터베이스 연결 객체 
	@Autowired
	private DataSource dataSource;
	
	// ItemDAO 연결 객체 
	@Autowired
	private MemberDAO memberDao;
	
	
	@Test
	@Transactional
	public void memberTest(){
		// 존재하는 경우는 List에 데이터가 보이고 그렇지 않은 경우에는 데이터가 없음 
		// list size = 0이면 없는것, 0보다 크면 있는것 
		System.err.println("이메일 체크:" + memberDao.emailCheck("yoond@naver.com"));
		System.err.println("이메일 체크:" + memberDao.emailCheck("yoond1@naver.com"));
		
		System.err.println("닉네임 체크:" + memberDao.nicknameCheck("yoond"));
		System.err.println("닉네임 체크:" + memberDao.nicknameCheck("yoond11"));
		
		// 회원 가입 
		Member member = new Member();
		member.setEmail("yoond1@naver.com");
		member.setPw("1234");
		member.setNickname("윤디");
		member.setProfile("yoond.png");
		
		System.err.println("회원가입 : " + memberDao.join(member));
		// 실제 넣는 데이터가 아니여서 중복가입처럼 보이지만, 실제론 중복가입 되는 형태 
		System.err.println("회원가입 : " + memberDao.join(member));
		
		// 로그인 확인 
		try {
		CryptoUtil util = new CryptoUtil();
		System.err.println(memberDao.login(util.encrypt("yoond@naver.com")));
		System.err.println(memberDao.login(util.encrypt("yoond123123@naver.com")));
		} catch (Exception e) {}
		
	}
}
