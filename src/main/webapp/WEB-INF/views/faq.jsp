<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Index</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>

<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/faq.css">

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
		
		
		<ul class="nav nav-tabs">
		  <li role="presentation" class="active"><a href="#">전체</a></li>
		  <li role="presentation"><a href="#">회원가입</a></li>
		  <li role="presentation"><a href="#">상품</a></li>
		</ul>


			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			
			  <div class="panel panel-default">
			    <div class="panel-heading" role="tab" id="headingOne">
			      <h5 class="panel-title">
			        <a class="collapsed" data-toggle="collapse" data-parent="#accordion"
			        href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
			       Q. 자주 묻는 질문 1
			        </a>
			      </h5>
			    </div>
			    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
			      <div class="panel-body">
			      A. 답변입니다.
			      </div>
			    </div>
			  </div>
			  
			  <div class="panel panel-default">
			    <div class="panel-heading" role="tab" id="headingTwo">
			      <h4 class="panel-title">
			        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
						Q. 자주 묻는 질문 2
			        </a>
			      </h4>
			    </div>
			    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
			      <div class="panel-body">
						A. 그만 물으세요.
			      </div>
			    </div>
			  </div>
			  
			  <div class="panel panel-default">
			    <div class="panel-heading" role="tab" id="headingThree">
			      <h4 class="panel-title">
			        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
						Q. 자주 묻는 질문 3
			        </a>
			      </h4>
			    </div>
			    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
			      <div class="panel-body">
						A. 묻어 버리겠어!!!
			      </div>
			    </div>
			  </div>
			</div>





		</div>
	</div>
	<!-- 
	<script type="text/javascript">
	
	var acodian = {

			  click: function(target) {
			    var _self = this,
			      $target = $(target);
			    $target.on('click', function() {
			      var $this = $(this);
			      if ($this.next('dd').css('display') == 'none') {
			        $('dd').slideUp();
			        _self.onremove($target);

			        $this.addClass('on');
			        $this.next().slideDown();
			      } else {
			        $('dd').slideUp();
			        _self.onremove($target);

			      }
			    });
			  },
			  onremove: function($target) {
			    $target.removeClass('on');
			  }

			};
			acodian.click('dt');
	
	</script>
	 -->
	
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	
</body>
</html>