<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Index</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>
</head>
<body>

	<jsp:include page="menu.jsp" flush="false"></jsp:include>

	<jsp:include page="modalLogin.jsp" flush="false"></jsp:include>

	<div class="site-blocks-cover inner-page-cover overlay"
		style="background-image: url(<%=request.getContextPath()%>/resources/images/hero_bg_1.jpg);"
		data-aos="fade" data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row align-items-center justify-content-center text-center">

				<div class="col-md-8" data-aos="fade-up" data-aos-delay="400">
					<h1
						class="text-white font-weight-light text-uppercase font-weight-bold">자유게 시판</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="site-section block-13">
		<div align="center"  style="margin-left: 15%; margin-right: 15%; margin-top: 130;">
		<table class="table">
			<tr>
				<td><a href="#">전체</a></td>
				<td><a href="#">이두</a></td>
				<td><a href="#">삼두</a></td>
				<td><a href="#">하체</a></td>
				<td><a href="#">광배</a></td>
				<td><a href="#">배</a></td>
			</tr>
		</table>
		
		</div>
		
		<div align="center"  style="margin-left: 15%; margin-right: 15%;">
		<table class="table">
			<tr style="text-align: center;">
				<th>카테고리</th>
				<th>제목(댓글수)</th>
				<th>글쓴이</th>
				<th>조회수</th>
				<th>좋아요/싫어요</th>
				<th>작성일</th>
			</tr>
			
			<!-- 상단노출 3개 -->
			
			<!-- 상단노출 3개 -->
			<c:forEach items="${ simpleBoardFreeViewList }" var="item">
				<tr style="text-align: center;">
					<td>${ item.category }</td>
					<td><a href="<%= request.getContextPath() %>/free/${ item.board_id} ">${ item.title } (${ item.comment_cnt })</a></td>
					<td>${ item.nickname }</td>
					<td>${ item.view_cnt }</td>
					<td>${ item.like_cnt }/${ item.dislike_cnt }</td>
					<td>${ item.write_date }</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
</body>
</html>