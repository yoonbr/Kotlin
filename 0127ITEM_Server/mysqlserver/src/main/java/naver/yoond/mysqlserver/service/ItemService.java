package naver.yoond.mysqlserver.service;

import javax.servlet.http.HttpServletRequest;

public interface ItemService {
	// 목록 보기 (파일 보기 유무 중요)
					// 파일이 있으면 여기에 Multipart
	public void list(HttpServletRequest request);

}
