<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>상품 보기</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">상품 보기</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="container">
		<div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">${ searchedItem.title } </h2>
          <h4><span class="text-muted">작성자 : ${ searchedItem.nickname }</span></h4>
          <h4><span class="text-muted">작성 시간 : ${ searchedItem.write_date }</span></h4>
          <h4><span class="text-muted">카테고리 : ${ searchedItem.category }</span></h4>
          <%-- <h4><span class="text-muted">개수 : ${ searchedItem.number }</span></h4>
          <h4><span class="text-muted">가격 : ${ searchedItem.price }</span></h4> --%>
          <p class="lead">${ searchedItem.content }</p>
          <div>
	          <c:if	test="${ login_member.member_id eq 'admin' or login_member.member_id eq searchedItem.member_id }">
	          	<button class="btn btn-default" id="update_item">수정</button>
	          	<button class="btn btn-default" id="delete_item">삭제</button>
	          </c:if>
	          <form method="post" id="form6">
	          	<input type="hidden" name="board_id" value="${ searchedItem.board_id }">
	          	<input type="hidden" name="image" value="${ searchedItem.image }">
	          	<input type="hidden" name="title" value="${ searchedItem.title }">
	          	<input type="hidden" name="category" value="${ searchedItem.category }">
	          </form>
	          <button class="btn btn-default" id="buy" type="button">구매</button>
	          <button class="btn btn-default" id="add_cart" type="button">장바구니에 추가</button>
          </div>
        </div>
        <div class="col-md-5 col-md-pull-7">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" alt="500x500" src="<%=request.getContextPath() %>/resources/images/${ searchedItem.image }" data-holder-rendered="true">
        </div>
	</div>
	

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
</body>
</html>