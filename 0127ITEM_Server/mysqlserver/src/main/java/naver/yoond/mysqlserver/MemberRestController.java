package naver.yoond.mysqlserver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.yoond.mysqlserver.service.MemberService;

@RestController
public class MemberRestController {
	@Autowired
	private MemberService memberService;
	
	// 이메일 중복 검사 (GET)
	@RequestMapping("member/emailcheck")
	public Map<String, Object> emailcheck(HttpServletRequest request){
		// 필요한 서비스 호출 
		memberService.emailCheck(request);
		return (Map<String,Object>)request.getAttribute("result");
	}
	
	// 닉네임 중복 검사 (GET)
	@RequestMapping("member/nicknamecheck")
	public Map<String, Object> nicknamecheck(HttpServletRequest request){
		// 필요한 서비스 호출 
		memberService.nicknameCheck(request);
		return (Map<String,Object>)request.getAttribute("result");
	}
	
	// 회원 가입 검사 (POST방식 - file) 
	@RequestMapping(value="member/join", method=RequestMethod.POST)
	public Map<String, Object> join(MultipartHttpServletRequest request){
		// 필요한 서비스 호출 
		memberService.join(request);
		return (Map<String,Object>)request.getAttribute("result");
	}
	
	// 로그인 검사
	@RequestMapping(value="member/login", method= {RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> login(HttpServletRequest request){
		// 필요한 서비스 호출 
		memberService.login(request);
		return (Map<String,Object>)request.getAttribute("result");
	}
}
