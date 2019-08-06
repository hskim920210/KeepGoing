<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Q&A 작성</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">Q&A</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="container">
	<div class="site-section block-13">

    	<form action="<%=request.getContextPath() %>/qna/write" id="add_qna_form" method="post">
    		<div class="form-group">
	    		<label for="title">카테고리</label><br>
	    		<select name="category">
					<option value="1">회원관련</option>
					<option value="2">리뷰</option>
					<option value="3">게시판</option>
					<option value="4">상품</option>
					<option value="5">기타</option>
				</select>
    		</div>
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" class="form-control" name="title" placeholder="Title" required>
			</div>
			<div class="form-group">
				<label for="title">글쓴이</label>
				<input type="text" class="form-control" name="member_id" placeholder="Nickname" value="${ login_member.member_id }" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea rows="20" cols="" class="form-control" name="content" placeholder="Content" required></textarea>
			</div>
			
			<button type="submit" class="btn btn-info" id="add_qna_insert">글 등록</button>
		</form>

    </div>
    </div>
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>

</body>
</html>