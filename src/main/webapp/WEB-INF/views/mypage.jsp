<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>마이페이지</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>
</head>
<body>

	<jsp:include page="menu.jsp" flush="false"></jsp:include>

	<jsp:include page="modalLogin.jsp" flush="false"></jsp:include>
	
	<div class="site-blocks-cover inner-page-cover overlay"
		style="background-image: url(<%=request.getContextPath()%>/resources/images/top.jpg);"
		data-aos="fade" data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row align-items-center justify-content-center text-center">

				<div class="col-md-8" data-aos="fade-up" data-aos-delay="400">
					<h1
						class="text-white font-weight-light text-uppercase font-weight-bold">마이페이지</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="site-section block-13">
		<div class="container">
			<div class="row">
				<div class="btn-group-vertical col-md-2">
				
					<c:if test="${ login_member.member_id=='admin' }" var="r">
						<div class="btn-group">
						    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">관리자</button>
						    <div class="dropdown-menu">
						      <a class="dropdown-item" href="#">회원 관리</a>
						      <a class="dropdown-item" href="#">권한 설정</a>
						      <a class="dropdown-item" href="#">게시물 관리</a>
						    </div>
						</div>
					</c:if>
				
					
					<c:if test="${ not r }">
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">회원 정보</button>
								<div class="dropdown-menu">
									<a class="dropdown-item" href="#">회원정보 변경</a>
						      		<a class="dropdown-item" href="#">최근 활동</a>
						      		<a class="dropdown-item" href="#">구매 목록</a>
						    	</div>
						</div>
						  
						<button type="button" class="btn btn-primary">권한 신청</button>
						  
						<div class="btn-group">
							<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Sony</button>
								<div class="dropdown-menu">
							      	<a class="dropdown-item" href="#">Tablet</a>
							      	<a class="dropdown-item" href="#">Smartphone</a>
						    	</div>
						</div>
					</c:if>
				</div>
				
				<div class="col-md-10">
					haha
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
</body>
</html>