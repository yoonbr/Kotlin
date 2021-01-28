package naver.yoond.mysqlserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface MemberService {
	
	// email 중복 검사를 위한 메소드 
	public void emailCheck(HttpServletRequest request);
	
	// 닉네임 중복 검사를 위한 메소드 
	public void nicknameCheck(HttpServletRequest request);
	
	// 회원가입을 위한 메소드 (**Multipart붙임)
	public void join(MultipartHttpServletRequest request);
	
	// 로그인 처리를 위한 메소드 
	public void login(HttpServletRequest request);
}
