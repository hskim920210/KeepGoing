<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>자주묻는질문</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>

<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/faq.css">

</head>
<body>

	<jsp:include page="menu.jsp" flush="false"></jsp:include>
	<jsp:include page="modalLogin.jsp" flush="false"></jsp:include>
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>


	
	
	
	
	<div class="site-blocks-cover inner-page-cover overlay"
		style="background-image: url(<%=request.getContextPath()%>/resources/images/hero_bg_1.jpg);"
		data-aos="fade" data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row align-items-center justify-content-center text-center">

				<div class="col-md-8" data-aos="fade-up" data-aos-delay="400">
					<h1
						class="text-white font-weight-light text-uppercase font-weight-bold">자주 묻는 질문(FAQ)</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="site-section block-13">
		<div style="margin-left: 15%; margin-right: 15%;">
		<div class="panel panel-default overlay" style="height: 100px; background-image: url(<%=request.getContextPath()%>/resources/images/hero_bg_1.jpg);">
			<h3 class="text-white font-weight-light text-uppercase font-weight-bold">자주 묻는 질문들을 모았습니다.</h3>
		</div>
		
		
		<ul id="myTab" class="nav nav-tabs" role="tablist">
		  <li role="presentation" class="tabcontent active" id="tabs1" data-toggle="tab"><a href="<%=request.getContextPath()%>/faq?p=0" style="display: block;">전체</a></li>
		  <li role="presentation" class="tabcontent" id="tabs2" value="1" data-toggle="tab"><a href="<%=request.getContextPath()%>/faq?p=1" style="display: block;">회원가입</a></li>
		  <li role="presentation" class="tabcontent" id="tabs3" value="2" data-toggle="tab" ><a href="<%=request.getContextPath()%>/faq?p=2" style="display: block;">상품</a></li>
		  <li role="presentation" class="tabcontent" id="tabs4" value="3" data-toggle="tab" ><a href="<%=request.getContextPath()%>/faq?p=3" style="display: block;">기타</a></li>
		</ul>
		
		
	<script>
	var tabValue
		$(function() {
			$('#myTab li').click(function() {
				var activeTab = $(this).attr('data-toggle');
				$('#myTab li').removeClass('active');
				$('.tabcontent').removeClass('active');
				$(this).addClass('active');
				$('#' + activeTab).addClass('active');
				tabValue = $(this).attr('value');
				console.log(tabValue)
			})
		});
	</script>
		
		
		
		
		

			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			<c:forEach items="${ faq }" var="faq">
			<c:if test="${ faq.category == param.p }">
			  <div class="panel panel-default">
			    <div class="panel-heading" role="tab" id="heading${ faq.board_id }">
			      <h5 class="panel-title">
			        <a class="collapsed" data-toggle="collapse" data-parent="#accordion"
			        href="#collapse${ faq.board_id }" aria-expanded="true" aria-controls="collapse${ faq.board_id }">
			       Q. ${ faq.title }
			        </a>
			      </h5>
			    </div>
			    <div id="collapse${ faq.board_id }" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading${ faq.board_id }">
			      <div class="panel-body">
			      A. ${ faq.content }
			      </div>
			    </div>
			  </div>
			</c:if>
			</c:forEach>
			</div>
			







		<c:if test="${ login_member.auth >= 3 }">
      		<p align="right"><a href="<%=request.getContextPath()%>/faq/write" class="btn btn-info btn-xs" type="button">작성</a></p>
      	</c:if>

		</div>
		
	</div>

	
	

	

    
 <jsp:include page="site_footer.jsp"></jsp:include>

	
</body>
</html>