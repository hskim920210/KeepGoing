<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>자유게시글</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">자유게시글</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	
	
	<div class="container"  >
		<div class="site-section block-13" >
		<br>
     
       
          
          
				
		
          <table class="table">
          	<tr align="center">
          	<td colspan="3"><b style="color: black; font-size: 30px; ">게시글 제목1  : ${ searchedFree.title }</b></td>
          	</tr>
          	
	          	<tr align="center">
	          	<td colspan="3"><div class="jumbotron"><a style="color: black; font-size: 15px; ">${ searchedFree.content }</a></div></td>
	          	</tr>
          	
			<tr>
			<td><b style="color: black; font-size: 15px; ">작성 시간 : ${ searchedFree.write_date }</b></td>
			<td><b style="color: black; font-size: 15px; ">관심사 : ${ searchedFree.category }</b></td>
			<td><b style="color: black; font-size: 15px; ">작성자 : ${ searchedFree.nickname }</b></td>
			
			</tr>
		</table>


         
   
          
          <div>
          <!-- 해당 아이디 가 접근시 수정및 삭제 가능 -->
	          <c:if	test="${ login_member.member_id eq 'admin' or login_member.member_id eq searchedFree.member_id }">
	          	<a class="btn btn-default" type="submit" href="<%=request.getContextPath()%>/update_free/${ searchedFree.board_id}"	id = "update_free" >수정</a>
	          	<a class="btn btn-default" type="submit" href="<%=request.getContextPath()%>/delete_free/${ searchedFree.board_id}"	id = "delete_free" >삭제</a>
	          </c:if>
	         <p align="right"><a class="btn btn-default" style="color : black;" type="submit" href="<%=request.getContextPath()%>/free">자유게시판으로 이동</a></p>
	         <p align="right"><a class="btn btn-default" style="color : black;" type="submit" href="<%=request.getContextPath()%>/home">홈페이지로 이동</a></p>
	          <!-- 해당 값을 가져와 출력 -->
	          <form method="post" id="form6">
	          	<input type="hidden" name="board_id" value="${ searchedFree.board_id }">
	          	<input type="hidden" name="title" value="${ searchedFree.title }">
	          	<input type="hidden" name="content" value="${ searchedFree.content }">
	          	<input type="hidden" name="category" value="${ searchedFree.category }" >
	          </form>
	        
          </div>
        </div>
        
	</div>
	

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
</body>
</html>