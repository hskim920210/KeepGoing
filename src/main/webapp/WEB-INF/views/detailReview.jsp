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
						class="text-white font-weight-light text-uppercase font-weight-bold">리뷰</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<div class="site-section block-13"
		style="padding-bottom: 10; margin-left: 10%; margin-right: 10%; margin-top: 130;">
		<c:if test="${ login_member.auth == '99' }" var="r">
			<a href="<%=request.getContextPath()%>/review/write">리뷰 작성하기</a>
		</c:if>
		<div align="center">
			<table class="table">
				<tr>
					<td><a href="#">전체</a></td>
					<td><a href="#">운동기구</a></td>
					<td><a href="#">헬스장</a></td>
					<td><a href="#">장소</a></td>
				</tr>
			</table>

		</div>
	</div>

	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<div class="site-section block-13" style="padding-top: 5;">
			<div class="row">


				<table class="table">
					<tr align="center">
						<td colspan="3"><b style="color: black; font-size: 30px;">리뷰
								제목 : ${ detailReview.title }</b></td>
					</tr>

					<tr align="center">
						<td colspan="3"><div class="jumbotron">
								<a style="color: black; font-size: 15px;">${ detailReview.content }</a>
							</div></td>
					</tr>

					<tr>
						<td><b style="color: black; font-size: 15px;">작성 시간 : ${ detailReview.write_date }</b></td>
						<td><b style="color: black; font-size: 15px;">관심사 : ${ detailReview.category }</b></td>
						<td><b style="color: black; font-size: 15px;">작성자 : ${ detailReview.nickname }</b></td>

					</tr>
				</table>

				<div>
					<form method="post" id="form6">
						<input type="hidden" name="board_id"
							value="${ detailReview.board_id }"> <input type="hidden"
							name="title" value="${ detailReview.title }"> <input
							type="hidden" name="content" value="${ detailReview.content }">
						<input type="hidden" name="category"
							value="${ detailReview.category }">
					</form>

				</div>

			</div>
		</div>
	</div>

	<jsp:include page="javascriptIncludeForReview.jsp" flush="false"></jsp:include>
</body>
</html>