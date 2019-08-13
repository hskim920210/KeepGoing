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
			
				<jsp:include page="mypageMenu.jsp" flush="false"></jsp:include>
				
				<div class="col-md-10">
					<ul id="myTab" class="nav nav-tabs" role="tablist">
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent active" id="tabs0" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/board_management?tab=0'"><a href="#">전체</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs1" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/board_management?tab=1'"><a href="#">리뷰</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs2" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/board_management?tab=2'"><a href="#">자유 게시판</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs3" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/board_management?tab=3'"><a href="#">상품</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs4" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/board_management?tab=4'"><a href="#">공지사항</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs5" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/board_management?tab=5'"><a href="#">FAQ</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs6" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/board_management?tab=6'"><a href="#">Q&A</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
</body>
</html>