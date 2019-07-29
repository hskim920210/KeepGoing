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
						class="text-white font-weight-light text-uppercase font-weight-bold">Index</h1>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<!-- item -->
	<div class="site-section block-13">
      <!-- <div class="container"></div> -->


      <div class="owl-carousel nonloop-block-13">
        <div>
          <a href="#" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/img_1.jpg" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">오늘의 운동법</h3>
              <p class="px-5">운동을 여ㅑㄹ심히 하자 운동을 여ㅑㄹ심히 하자  운동을 여ㅑㄹ심히 하자  운동을 여ㅑㄹ심히 하자  운동을 여ㅑㄹ심히 하자</p>
            </div>
          </a>
        </div>

        <div>
          <a href="#" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/img_2.jpg" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">Air Transports</h3>
              <p class="px-5">운동을 여ㅑㄹ심히 하자 운동을 여ㅑㄹ심히 하자  운동을 여ㅑㄹ심히 하자  운동을 여ㅑㄹ심히 하자  운동을 여ㅑㄹ심히 하자it. Eos tempore ullam minus voluptate libero.</p>
            </div>
          </a>
        </div>

        <div>
          <a href="#" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/img_3.jpg" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">Cargo Transports</h3>
              <p class="px-5">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eos tempore ullam minus voluptate libero.</p>
            </div>
          </a>
        </div>

        <div>
          <a href="#" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/img_4.jpg" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">Cargo Ship</h3>
              <p class="px-5">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eos tempore ullam minus voluptate libero.</p>
            </div>
          </a>
        </div>

        <div>
          <a href="#" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/img_5.jpg" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">Ware Housing</h3>
              <p class="px-5">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eos tempore ullam minus voluptate libero.</p>
            </div>
          </a>
        </div>
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
                <h3>에어 에어 에어에어ㅔ어</h3>
                <p>가나다ㅏㄹ마바사 게시판 글이 화면에 노출됩니다</p>
                <p><a href="#">더 보기</a></p>
                <h3>에어 에어 에어에어ㅔ어</h3>
                <p>가나다ㅏㄹ마바사 게시판 글이 화면에 노출됩니다</p>
                <p><a href="#">더 보기</a></p>
                <h3>에어 에어 에어에어ㅔ어</h3>
                <p>가나다ㅏㄹ마바사 게시판 글이 화면에 노출됩니다</p>
                <p><a href="#">더 보기</a></p>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="unit-4 d-flex">
            <!-- 
              <div class="unit-4-icon mr-4"><span class="text-primary flaticon-sea-ship-with-containers"></span></div>
             -->
              <div>
                <h3>오션 배ghdfh 게시판 글</h3>
                <p>가나다라마바사 게시판 글이 화면에 노출됩니다 한글로 쓰니까 이상하네요.</p>
                <p><a href="#">더 보기</a></p>
                <h3>오션 배 게시판 글</h3>
                <p>가나다라마바사 게시판 글이 화면에 노출됩니다 한글로 쓰니까 이상하네요.</p>
                <p><a href="#">더 보기</a></p>
                <h3>오션 배 게시판 글</h3>
                <p>가나다라마바사 게시판 글이 화면에 노출됩니다 한글로 쓰니까 이상하네요.</p>
                <p><a href="#">더 보기</a></p>
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="unit-4 d-flex">
            <!-- 
              <div class="unit-4-icon mr-4"><span class="text-primary flaticon-frontal-truck"></span></div>
             -->
              <div>
                <h3>Ground Shidhdthdgfhpping</h3>
                <a>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis quis molestiae vitae eligendi at.</a>
                <p><a href="#">Learn More</a></p>
                <h3>Ground Shipping</h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis quis molestiae vitae eligendi at.</p>
                <p><a href="#">Learn More</a></p>
                <h3>Ground Shipping</h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Perferendis quis molestiae vitae eligendi at.</p>
                <p><a href="#">Learn More</a></p>
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
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="h-entry">
              <img src="/webapp/resources/images/blog_1.jpg" alt="Image" class="img-fluid">
              <h2 class="font-size-regular"><a href="#">Warehousing Your Packages</a></h2>
              <div class="meta mb-4">Theresa Winston <span class="mx-2">•</span> Jan 18, 2019<span class="mx-2">•</span> <a href="#">News</a></div>
              <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Natus eligendi nobis ea maiores sapiente veritatis reprehenderit suscipit quaerat rerum voluptatibus a eius.</p>
            </div> 
          </div>
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="h-entry">
              <img src="/webapp/resources/images/blog_2.jpg" alt="Image" class="img-fluid">
              <h2 class="font-size-regular"><a href="#">Warehousing Your Packages</a></h2>
              <div class="meta mb-4">Theresa Winston <span class="mx-2">•</span> Jan 18, 2019<span class="mx-2">•</span> <a href="#">News</a></div>
              <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Natus eligendi nobis ea maiores sapiente veritatis reprehenderit suscipit quaerat rerum voluptatibus a eius.</p>
            </div>
          </div>
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="h-entry">
              <img src="/webapp/resources/images/blog_1.jpg" alt="Image" class="img-fluid">
              <h2 class="font-size-regular"><a href="#">Warehousing Your Packages</a></h2>
              <div class="meta mb-4">Theresa Winston <span class="mx-2">•</span> Jan 18, 2019<span class="mx-2">•</span> <a href="#">News</a></div>
              <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Natus eligendi nobis ea maiores sapiente veritatis reprehenderit suscipit quaerat rerum voluptatibus a eius.</p>
            </div> 
          </div>
        </div>
      </div>
    </div>
    
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
	</div>

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#member_login").on("click", function() {
				var params=$("#login_form").serialize();
				console.log(params);
				
				$.ajax({
		            url: "<%=request.getContextPath()%>/login",
		            type: "POST",
		            data: params,
		            success: function(data){
		                console.log(data);
		                alert(data);
		                location.reload();
		            },
		            error: function(){
		                alert("err");
		            }
		        });
			})
		})
	</script>
</body>
</html>