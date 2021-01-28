package naver.yoond.mysqlserver.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.yoond.mysqlserver.domain.Item;

// DAO 클래스로 만들고 bean을 자동생성해주는 어노테이션 
@Repository
public class ItemDAO {
	// 동일한 자료형의 bean이 있으면 자동으로 주입 받아주는 어노테이션 
	@Autowired
	private SessionFactory sessionFactory;
	// 데이터의 개수를 가져오는 메소드 (일반적으로 정수)
	// 파라미터를 2개 가져오면 순서에 문제가 생김
	// - 묶을 수 있는 방법을 연습 (list(비교할 수 있는 것 끼리 묶음), map, dto class(매개변수 있는 것 마다 다 만들어야 하니 class가 늘어남))
	// 전체 데이터 개수를 가져오는 메소드 
	// public int count(Map<String, Object>map) {
	// public int count() {
	public int count(Map<String, Object>map) {
		// 기본값 없으면 0
		int result = 0;
		// Hibernate 세션 가져오기 
		Session session = sessionFactory.getCurrentSession();
		// sql을 저장할 변수 
		String sql = null;
		// Hibernate 쿼리를 만들기 위한 변수 (Query import hibernate)
		Query query = null; 
		
		
		// 검색 항목 
		String searchtype = null;
		if(map.get("searchtype") != null) {
			searchtype = (String)map.get("searchtype");
		}
		// 검색어 
		String keyword = null;
		if(map.get("keyword") != null) {
			keyword = (String)map.get("keyword");
		}
		
		if(searchtype != null) {
			keyword = "%" + keyword + "%";
		}
		
		// searchtype 이 없으면 전체 데이터 개수 
		if(searchtype == null) {
			sql = "select count(*) from item";
			query = session.createNativeQuery(sql);
			
		} else if(searchtype.equals("itemname")) {
			sql = "select count(*) from item where lower(itemname) like :keyword";
			query = session.createNativeQuery(sql);
			query.setParameter("keyword", keyword);
			
		}  else if(searchtype.equals("description")) {
			sql = "select count(*) from item where lower(description) like :keyword";
			query = session.createNativeQuery(sql);
			query.setParameter("keyword", keyword);
			
		} else if(searchtype.equals("both")) {
			sql = "select count(*) from item where lower(description) like :description or lower(itemname) like :itemname";
			query = session.createNativeQuery(sql);
			query.setParameter("description", keyword);
			query.setParameter("itemname", keyword);
		}
		
		/*
		// 수행할 SQL을 생성 Item 테이블에서 개수세기 
		sql = "select count(*) from item where lower(itemname) like :keyword";
		// hibernate가 실행하도록 쿼리를 생성 
		query = session.createNativeQuery(sql);
		
		// itemname으로 데이터의 개수 찾기 
		query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
		*/
		
		// 쿼리 실행 
		List<BigInteger> list = query.getResultList();
		
		// 데이터가 나온 경우 정수로 변환
		if(list.size()>0) {
			result = list.get(0).intValue();
		}
		
		return result;
	}
	

	// 테이블에서 데이터 목록을 가져오는 메소드 
	public List<Item> list(Map<String, Object>map){
		List<Item> list = new ArrayList<Item>();
		
		// 파라미터 읽어오기 
		int start = (Integer)map.get("start");
		int size = (Integer)map.get("size");
		
		// 검색 항목 
		String searchtype = null;
		if(map.get("searchtype") != null) {
			searchtype = (String)map.get("searchtype");
		}
		// 검색어 
		String keyword = null;
		if(map.get("keyword") != null) {
			keyword = (String)map.get("keyword");
		}

		if(searchtype != null) {
			keyword = "%" + keyword + "%";
		}
		
		// Hibernate 세션 가져오기 
		Session session = sessionFactory.getCurrentSession();
		// sql을 저장할 변수 
		String sql = null;
		// Hibernate 쿼리를 만들기 위한 변수 (Query import hibernate)
		Query query = null; 
		
		if(searchtype == null) {
			// 수행할 SQL을 생성 
			sql = "select * from item order by itemid desc limit :start, :size";
			// 하이버네이트가 실행하도록 쿼리를 생성 
			// 하나의 행을 Item 클래스로 변환하도록 설정 
			query = session.createNativeQuery(sql, Item.class);
			query.setParameter("start", start);
			query.setParameter("size", size);
			
		} else if(searchtype.equals("itemname")) {
			// 수행할 SQL을 생성 
			sql = "select * from item where lower(itemname) like :itemname order by itemid desc limit :start, :size";
			// 하이버네이트가 실행하도록 쿼리를 생성 
			// 하나의 행을 Item 클래스로 변환하도록 설정 
			query = session.createNativeQuery(sql, Item.class);
			query.setParameter("start", start);
			query.setParameter("size", size);
			query.setParameter("itemname", keyword);
			
		} else if(searchtype.equals("description")) {
			// 수행할 SQL을 생성 
			sql = "select * from item where lower(description) like :description order by itemid desc limit :start, :size";
			// 하이버네이트가 실행하도록 쿼리를 생성 
			// 하나의 행을 Item 클래스로 변환하도록 설정 
			query = session.createNativeQuery(sql, Item.class);
			query.setParameter("start", start);
			query.setParameter("size", size);
			query.setParameter("description", keyword);
			
		} else if(searchtype.equals("both")) {
			// 수행할 SQL을 생성 
			sql = "select * from item where lower(description) like :description "
					+ "or lower(itemname) like :itemname order by itemid desc limit :start, :size";
			// 하이버네이트가 실행하도록 쿼리를 생성 
			// 하나의 행을 Item 클래스로 변환하도록 설정 
			query = session.createNativeQuery(sql, Item.class);
			query.setParameter("start", start);
			query.setParameter("size", size);
			query.setParameter("description", keyword);
			query.setParameter("itemname", keyword);
		}
		
		
		/*
		// 수행할 SQL을 생성 Item list 가져오기 
		// sql = "select * from item";
		sql = "select * from item where lower(itemname) like :keyword limit :start, :size order by itemid desc";
		// hibernate가 실행하도록 쿼리를 생성
		// 하나의 행을 Item 클래스로 변환하도록 설정 
		query = session.createNativeQuery(sql, Item.class);
		// 파라미터 설정 
		// 번호보다는 이름을 쓰면 수정하기에 쉬움 
		query.setParameter("start", start);
		query.setParameter("size", size);
		query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
		*/
		
		// 실행 
		list = query.getResultList();
		
		return list;
	}

	// 테이블에서 데이터 1개를 가져오는 메소드 
	public Item detail(int itemid) {
		Item item = null;
		// 기본키를 가지고 하나의 데이터 찾아오는 방법 
		Session session = sessionFactory.getCurrentSession();
		item = session.get(Item.class, itemid);
		return item;
	}
	
	// 가장 큰 itemid를 찾아오는 메소드 (파라미터 X)
	public int maxid() {
		Session session = 
				sessionFactory.getCurrentSession();
		List<Integer> list = 
				session.createNativeQuery("select max(itemid) from item").getResultList();
		if(list == null || list.size() == 0) {
			return 0;
		} else {
			return list.get(0);
		}
	}
	
	// 데이터 삽입 메소드
	// 리턴되는 값은 기본기값 
	public Serializable insert(Item item) {
		Session session = 
				sessionFactory.getCurrentSession();
		return session.save(item);
		
	}
}





















