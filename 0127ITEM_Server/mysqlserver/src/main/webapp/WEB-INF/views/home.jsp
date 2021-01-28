<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTER</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
<style>
	.toptext {
		margin-top : 10px;
	}
</style>
</head>
<body>
<div class="text-center" id="toptext" style=margin-top:100px;>
  <h1> 환영합니다! </h1>
  <p> 기본 정보를 등록해주세요 :D </p>
</div>
<div class="mx-auto" style=" width:400px;"> 
	<form action="member/login" method="post" enctype="multipart/form-data"
	id="memberform">
	<div class="id">
    <label for="exampleInputEmail1" class="form-label">이메일</label>
    <input type="text" class="form-control" id="exampleInputEmail1" name="email" placeholder="이메일을 입력하세요">
    <br/>
  	</div>
	<div class="password">
	  <label for="exampleInputPassword1" class="form-label">비밀번호</label>
	  <input type="password" class="form-control" id="exampleInputPassword1" name="pw" placeholder="비밀번호를 입력하세요">
	</div>
 <br/>
	<div class="nickname">
    <label for="exampleInputNickname" class="form-label">닉네임</label>
    <input type="text" class="form-control" id="exampleInputNickname" name="nickname" placeholder="닉네임을 입력하세요">
 	</div>
 <br/>
 	<div class="profile">
  	<label for="formFile" class="form-label">프로필사진</label>
  	<input class="form-control" type="file" id="profile" accept="image/*"/>
	</div>
 <br/>
<div class="btn">
  <button type="submit" class="btn btn-outline-secondary" id="backbtn">뒤로가기</button>
  <button type="submit" class="btn btn-outline-primary" id="joinbtn">회원가입</button>
</div>
</form>
</div>
</body>
<script>
	document.getElementById("joinbtn").addEventListener('click', function(e){
		// 폼에 입력된 데이터 가져오기 
		var formData = new FormData(
				document.getElementById("memberform"));
		// ajax로 데이터를 전송해서 결과를 받기 
		var xhr = new XMLHttpRequest();
		// 요청 생성 
		xhr.open("POST", "member/join", true);
		// 전송 
		xhr.send(formData);
		// 응답 받기 
		xhr.onload = function(){
			if(xhr.status == 200){
				alert(xhr.responseText);
				if(o.result == true){
					alert("회원가입 완료")
					location.href="./"
			}
		}
	}
})
	
</script>
</html>