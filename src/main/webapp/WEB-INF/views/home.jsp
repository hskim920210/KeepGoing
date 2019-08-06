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
						class="text-white font-weight-light text-uppercase font-weight-bold">KeepGoing</h1>
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
            <img src="<%=request.getContextPath() %>/resources/images/img1.jpg" alt="Image" class="img-fluid" width="700" height="799">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">오늘의 운동법</h3>
              <p class="px-6">제가 그동안 운동을 하면서 느낀 운동팁을 회원님들과 공유하려고 합니다. 먼저, 웜업에 관한 건데요.</p>
            </div>
          </a>
        </div>

        <div>
          <a href="#" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/img2.jpg" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">요가</h3>
              <p class="px-6">요가를 시작한지 3개월째인데, 몸이 가벼워 지는 게 확연히 느껴지네요. 요가 배우길 너무 잘 한 것 같아요.</p>
            </div>
          </a>
        </div>

        <div>
          <a href="#" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/img3.jpg" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">최대 리뷰 헬스장 </h3>
              <p class="px-6">신촌의 더좋은 짐에 등록했는데요. 운동 기구들도 많고, 트레이너도 좋고, 샤워실도 깨끗해서 맘에 듭니다.  </p>
            </div>
          </a>
        </div>
        
        <div>
          <a href="#" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/img6.jpg" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">그룹 운동</h3>
              <p class="px-6">친구들과 같이 운동 시작한지 3개월째인데, 몸이 가벼워 지는 게 확연히 느껴지네요. 요가 배우길 너무 잘 한 것 같아요.</p>
            </div>
          </a>
        </div>

        <div>
          <a href="#" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/img4.jpg" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">오늘의 최다 클릭 리뷰</h3>
              <p class="px-6">안녕하세요. 트레이너 김동현입니다. 오늘은 효과적인 다이어트 식단을 소개하려고 합니다.</p>
            </div>
          </a>
        </div>

        <div>
          <a href="#" class="unit-1 text-center">
            <img src="<%=request.getContextPath() %>/resources/images/img5.jpg" alt="Image" class="img-fluid">
            <div class="unit-1-text">
              <h3 class="unit-1-heading">오늘의 최다 좋아요 게시글</h3>
              <p class="px-6">제가 그동안 운동을 하면서 느낀 운동팁을 회원님들과 공유하려고 합니다. 먼저, 웜업에 관한 건데요.</p>
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
              <c:forEach items="${ reviewList }" var="review_article">
              
              <h3>${ review_article.title }</h3>
              <p>${ review_article.content }</p>
              <p><a href="#">더 보기</a></p>
             
              
              </c:forEach>
              <!-- 

                <h3>오늘의 최다 클릭 리뷰</h3>
                <h3>오늘의 최다 좋아요 게시글</h3>
                <p>제가 그동안 운동을 하면서 느낀 운동팁을 회원님들과 공유하려고 합니다. 먼저, 웜업에 관한 건데요.</p>

                <p><a href="#">더 보기</a></p>
                <h3>최대 리뷰 헬스장</h3>
                <p>신촌의 더좋은 짐에 등록했는데요. 운동 기구들도 많고, 트레이너도 좋고, 샤워실도 깨끗해서 맘에 듭니다.</p>
                <p><a href="#">더 보기</a></p>
              -->
              </div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="unit-4 d-flex">
            <!-- 
              <div class="unit-4-icon mr-4"><span class="text-primary flaticon-sea-ship-with-containers"></span></div>
             -->
              <div>
                <h3>웨이트 트레이닝 게시판 글</h3>
                <p>오늘 드디어 바벨 100kg를 들었습니다. 옆에 친구들이 보조해 줘서 안심하고 도전했는데, 성공해서 너무 기쁘네요.</p>
                <p><a href="#">더 보기</a></p>
                <h3>다이어트 게시판 글</h3>
                <p>오늘 인바디 검사했는데, 체지방률이 15퍼센트 아래가 됐어요. 너무나 감격스럽네요. 더욱더 열심히 운동하겠습니다.</p>
                <p><a href="#">더 보기</a></p>
                <h3>요가  게시판 글</h3>
                <p>요가를 시작한지 3개월째인데, 몸이 가벼워 지는 게 확연히 느껴지네요. 요가 배우길 너무 잘 한 것 같아요.</p>
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
                <h3>상품 게시판</h3>
                <a>홈 트레이닝을 위해서 바벨과 벤치를 샀는데요. 제가 생각한 것보다 더 좋더군요. 이제는 집에서도 열심히 운동해야겠습니다.</a>
                <p><a href="#">더 보기</a></p>
                <h3>레시피 게시판</h3>
                <p>요즘 너무 삶은 닭가슴살만 먹으니까 닭가슴살이 질려서요. 훈제된 닭가슴살로 메뉴를 바꿨는데, 맛있더군요. </p>
                <p><a href="#">더 보기</a></p>
                <h3>피트니스 게시판</h3>
                <p>버핏 테스트를 했는데 너무나 힘들어서 온몸이 후들거렸네요. 아...버핏 테스트 너무 힘들어요..</p>
                <p><a href="#">더 보기</a></p>
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
              <img src="/webapp/resources/images/equipment1.jpg" alt="Image" class="img-fluid">
              <h2 class="font-size-regular"><a href="#">칼라풀 케틀벨 </a></h2>
              <div class="meta mb-4">박지성 <span class="mx-2">•</span> Jan 18, 2019<span class="mx-2">•</span> <a href="#">더보기</a></div>
              <p>최고의 상품입니다. 배송도 빠르고요. 처음 구매할 땐 많이 망설였는데 막상 구입해 보니  너무 좋아서 재구입하게 됐습니다. 가성비 좋구요. 여러면에서 마음에 쏙 들어요.</p>
            </div> 
          </div>
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="h-entry">
              <img src="/webapp/resources/images/equipment2.jpg" alt="Image" class="img-fluid">
              <h2 class="font-size-regular"><a href="#">닭가슴살 허브 1KG</a></h2>
              <div class="meta mb-4">김연아 <span class="mx-2">•</span> Jan 18, 2019<span class="mx-2">•</span> <a href="#">더보기</a></div>
              <p>최고의 상품입니다. 배송도 빠르고요. 처음 구매할 땐 많이 망설였는데 막상 구입해 보니  너무 좋아서 재구입하게 됐습니다. 가성비 좋구요. 여러면에서 마음에 쏙 들어요. </p>
            </div>
          </div>
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="h-entry">
              <img src="/webapp/resources/images/equipment3.jpg" alt="Image" class="img-fluid">
              <h2 class="font-size-regular"><a href="#">휴대용 홈 피트니스 용품 세트 </a></h2>
              <div class="meta mb-4">최태용 <span class="mx-2">•</span> Jan 18, 2019<span class="mx-2">•</span> <a href="#">더보기</a></div>
              <p>최고의 상품입니다. 배송도 빠르고요. 처음 구매할 땐 많이 망설였는데 막상 구입해 보니  너무 좋아서 재구입하게 됐습니다. 가성비 좋구요. 여러면에서 마음에 쏙 들어요.</p>
            </div> 
          </div>
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
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
</body>
</html>