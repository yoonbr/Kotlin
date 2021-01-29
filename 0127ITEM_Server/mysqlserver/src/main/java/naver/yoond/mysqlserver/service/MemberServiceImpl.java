package naver.yoond.mysqlserver.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.yoond.mysqlserver.dao.MemberDAO;
import naver.yoond.mysqlserver.domain.Member;
import naver.yoond.mysqlserver.util.CryptoUtil;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDao;

	@Override
	@Transactional
	public void emailCheck(HttpServletRequest request) {
		
		// 이메일 중복 체크 결과 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		
		String email = request.getParameter("email");
		
		if(email != null) {
			CryptoUtil util = new CryptoUtil();
			// 입력한 email이 list에 없다면 
			try {
			List<String> list = memberDao.emailCheck(util.encrypt(email));
			if(list != null && list.size() == 0) {
				map.put("result", true);
				}
			} catch(Exception e) {}
		}
		request.setAttribute("result", map);
	}

	@Override
	@Transactional
	public void nicknameCheck(HttpServletRequest request) {
		
		// 닉네임 중복 체크 결과 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		
		String nickname = request.getParameter("nickname");
		
		if(nickname != null) {
			List<String>list = memberDao.nicknameCheck(nickname);
			if(list != null && list.size() == 0) {
				map.put("result", true);
			}
		}
		request.setAttribute("result", map);	
	}
	
	@Override
	@Transactional
	public void join(MultipartHttpServletRequest request) {
		// 결과를 저장할 Map을 생성 
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", true);
		map.put("emailcheck", true);
		map.put("nicknamecheck", true);
		
		// 필요한 파라미터 읽기 
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		// 처음에는 기본이름 작성 
		String profile = "default.jpg";
		
		MultipartFile image = request.getFile("profile");
		
		// emailcheck
		try {
		if(email != null) {
			CryptoUtil util = new CryptoUtil();
			List<String> list = memberDao.emailCheck(util.encrypt(email));
			if(list != null && list.size() != 0) {
				map.put("result", false);
				map.put("emailcheck", false);
			}else {
				if(nickname != null) {
					list = memberDao.nicknameCheck(nickname);
					if(list != null && list.size() !=0) {
						map.put("result", false);
						map.put("nicknamecheck", false);
					} else {
						if(image != null && image.isEmpty() == false) {
							// 파일을 저장할 디렉토리 경로를 생성 
							String filePath = request.getServletContext().getRealPath("/profile");
							// 파일 이름 생성 
							profile = UUID.randomUUID() + image.getOriginalFilename();
							// 파일 저장 경로 생성 
							filePath = filePath + "/" + profile;
							
							// 파일 업로드 
							try {
							File file = new File(filePath);
							FileOutputStream fos = new FileOutputStream(file);
							fos.write(image.getBytes());
							fos.close();
							} catch (Exception e) {}
						}
						
						// 실제 데이터 삽입 
						Member member = new Member();
						// 이메일은 복호화가 가능하도록 암호화를 해서 저장 - 예외처리  
						try {
						member.setEmail(util.encrypt(email));
						} catch(Exception e) {}
						
						// 비밀번호는 복호화가 불가능하도록 암호화를 해서 저장 - 예외처리  
						member.setPw(BCrypt.hashpw(pw, BCrypt.gensalt()));
						member.setNickname(nickname);
						member.setProfile(profile);
						
						Serializable result = memberDao.join(member);
						
						if(result == null) {
							map.put("result", false);
						}
					}
				}
			}
		}
		} catch (Exception e) {}
		request.setAttribute("result", map);
	}

	@Override
	@Transactional
	public void login(HttpServletRequest request) {
		// 파라미터 읽기 
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		// 로그인 결과를 저장할 Map 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		try {
			CryptoUtil util = new CryptoUtil();
			
			// email에 해당하는 데이터 가져오기 
			Member member = memberDao.login(util.encrypt(email));
			System.out.println(member);
			System.out.println(pw);
			// 가져온 데이터가 있으면 비밀번호 비교 
			if (member != null) {
				if(BCrypt.checkpw(pw, member.getPw())) {
					map.put("result", true);
					// email은 입력한 것으로 pw는 삭제하고 전달 
					map.put("member", member);
					// 웹을 위한 것이라면 
					request.getSession().setAttribute("member", member);
				}
			}
		}catch(Exception e) {
			System.err.println("로그인 에러: " + e.getLocalizedMessage());
		}
		
		request.setAttribute("result", map);
	}
}











