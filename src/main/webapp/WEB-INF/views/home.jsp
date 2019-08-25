<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>home</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>

<style type="text/css">
.px-5 { width:300px; display: inline-block; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;white-space: normal; line-height: 1.2; height: 1.2em;

}


</style>


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
						class="text-white font-weight-light text-uppercase font-weight-bold">KEEPGOING</h1>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<!-- item -->
	<div class="site-section block-13">
      <!-- <div class="container"></div> -->


      <div class="owl-carousel nonloop-block-13">
        

<%-- 
        <c:forEach items="${rl}" var="ra">
	        <div>
	          <a href="#" class="unit-1 text-center">
	            <img src="<%=request.getContextPath() %>/resources/images/${ra.image}" alt="Image" class="img-fluid"/>
	            <div class="unit-1-text">
	              <h3 class="unit-1-heading">${ra.title}</h3>
	              <p class="px-6">${ra.content}</p>
	            </div>z
	          </a>
	        </div>
        </c:forEach>
        
        <c:forEach items="${ reviewList }" var="review_article">
	              <a href="<%= request.getContextPath() %>/review/detail/${review_article.category}_${review_article.board_id}">${ review_article.title }</a>
	              <h3 class="px-5" >
	 				<a href="<%=request.getContextPath() %>/review/detail/${review_article.category}_${review_article.board_id}">${ review_article.content }</a></h3>
	              <p></p>
	              <p><a href="<%=request.getContextPath() %>/review/detail/${review_article.category}_${review_article.board_id}">더보기</a></p>
	             
	              
              </c:forEach>
        
          --%>

 		<c:forEach items="${ reviewList }" var="review_article">
        <div>
          <a href="<%= request.getContextPath() %>/review/detail/${review_article.category}_${review_article.board_id}" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/${review_article.image}" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h1 class="unit-1-heading">${ review_article.title }</h1>
              <p class="px-6 target p">${review_article.abstractContent }</p>
            </div>
          </a>
        </div>
        </c:forEach>
      </div>
    </div>
  

   
    
    
    <!-- board_free -->
    <div class="site-section bg-light">
      <div class="container">
        <div class="row align-items-stretch">
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="unit-4 d-flex">
            <!-- 
              <div class="unit-4-icon mr-4"><span class="text-primary flaticon-travel"></span></div>
             -->
              <div>
                
        <c:forEach items="${ reviewList }" var="review_article">
	              <a href="<%= request.getContextPath() %>/review/detail/${review_article.category}_${review_article.board_id}">${ review_article.title }</a>
	              <p><a href="#">
	              
              </c:forEach>
        
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="unit-4 d-flex">
            <!-- 
              <div class="unit-4-icon mr-4"><span class="text-primary flaticon-sea-ship-with-containers"></span></div>
             -->
               <div> 
                <c:forEach items="${ freeList }" var="free_article">
              
              
              <a href="<%=request.getContextPath() %>/free_view/${ free_article.board_id }">${ free_article.title }</a>
              <p><a href="#">
          
             
              
              </c:forEach>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="unit-4 d-flex">
            <!-- 
              <div class="unit-4-icon mr-4"><span class="text-primary flaticon-frontal-truck"></span></div>
             -->
              <div>
                <c:forEach items="${ itemList }" var="item_article">
              
				<a href="<%= request.getContextPath() %>/item_view/${item_article.board_id}">${item_article.title}</a>
              <%-- <h3>${item_article.title }</h3>--%>
             
              <p><a href="#">
 <%--${item_article.nickname }</p>--%>
              </c:forEach>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    
  
    <!-- review -->
	<div class="site-section">
      <div class="container">
        <div class="row">
         <c:forEach items="${ itemList }" var="item_article">
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="h-entry">
              <img src="<%= request.getContextPath() %>/resources/images/${ item_article.image }" alt="Image" class="img-fluid">
              <h2 class="font-size-regular"><a href="<%= request.getContextPath() %>/item_view/${item_article.board_id}"">${ item_article.title } </a></h2>
              <div class="meta mb-4">${item_article.nickname }<span class="mx-2">•</span>${item_article.write_date }<span class="mx-2">•</span> </div>
              <p>${item_article.content }</p>
            </div> 
          </div>
          </c:forEach>
        </div>
      </div>
    </div>
    

 <jsp:include page="site_footer.jsp"></jsp:include>
    
 



</div>
    
    <%-- 
	<footer class="site-footer">
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<div class="row">
						<div class="col-md-3">
							<h2 class="footer-heading mb-4">Quick Links</h2>
							<ul class="list-unstyled">
								<li><a href="#">About Us</a></li>
								<li><a href="#">Services</a></li>
								<li><a href="#">Testimonials</a></li>
								<li><a href="#">Contact Us</a></li>
							</ul>
						</div>
						<div class="col-md-3">
							<h2 class="footer-heading mb-4">Products</h2>
							<ul class="list-unstyled">
								<li><a href="#">About Us</a></li>
								<li><a href="#">Services</a></li>
								<li><a href="#">Testimonials</a></li>
								<li><a href="#">Contact Us</a></li>
							</ul>
						</div>
						<div class="col-md-3">
							<h2 class="footer-heading mb-4">Features</h2>
							<ul class="list-unstyled">
								<li><a href="#">About Us</a></li>
								<li><a href="#">Services</a></li>
								<li><a href="#">Testimonials</a></li>
								<li><a href="#">Contact Us</a></li>
							</ul>
						</div>
						<div class="col-md-3">
							<h2 class="footer-heading mb-4">Follow Us</h2>
							<a href="#" class="pl-0 pr-3"><span class="icon-facebook"></span></a>
							<a href="#" class="pl-3 pr-3"><span class="icon-twitter"></span></a>
							<a href="#" class="pl-3 pr-3"><span class="icon-instagram"></span></a>
							<a href="#" class="pl-3 pr-3"><span class="icon-linkedin"></span></a>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<h2 class="footer-heading mb-4">Subscribe Newsletter</h2>
					<form action="#" method="post">
						<div class="input-group mb-3">
							<input type="text"
								class="form-control border-secondary text-white bg-transparent"
								placeholder="Enter Email" aria-label="Enter Email"
								aria-describedby="button-addon2">
							<div class="input-group-append">
								<button class="btn btn-primary text-white" type="button"
									id="button-addon2">Send</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="row pt-5 mt-5 text-center">
				<div class="col-md-12">
					<div class="border-top pt-5">
						<p>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>
								document.write(new Date().getFullYear());
							</script>
							All rights reserved | This template is made with <i
								class="icon-heart" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
				</div>

			</div>
		</div>
	</footer>
	--%>
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	<script type="text/javascript">
	<%--
	window.onload = function(){ 
			window.open("<%= request.getContextPath() %>/homePopup", "pop", "width=400,height=500,history=no,resizable=no,status=no,scrollbars=yes,menubar=no");
			
		}
		$('document').ready(function () {
			$(".target p").css({"width" : "200px", "over-flow" : "hidden", "text-overflow" : "ellipsis",
				"white-space" : "normal", "line-height" : "1.2"});
		});
	--%>
	</script>
	
	
</body>
</html>