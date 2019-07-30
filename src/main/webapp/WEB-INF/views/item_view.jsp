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
						class="text-white font-weight-light text-uppercase font-weight-bold">상품
						보기</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<div class="container">
		<div class="site-section">
			<div class="container">
				<div class="row mb-5">

					<div class="col-md-5 ml-auto mb-5 order-md-2 aos-init aos-animate"
						data-aos="fade">
						<img
							src="<%=request.getContextPath() %>/resources/images/${ searchedItem.image }"
							alt="Image" class="img-fluid rounded">
					</div>
					<div class="col-md-6 order-md-1 aos-init aos-animate"
						data-aos="fade">
						<div class="text-left pb-1 border-primary mb-4">
							<h2 class="text-primary">${ searchedItem.title }</h2>
						</div>
						<h4>
							<span class="text-muted">작성자 : ${ searchedItem.nickname }</span>
						</h4>
						<h4>
							<span class="text-muted">작성 시간 : ${ searchedItem.write_date }</span>
						</h4>
						<h4>
							<span class="text-muted">카테고리 : ${ searchedItem.getCategoryString() }</span>
						</h4>
						<h4>
							<span class="text-muted">가격 : ${ searchedItem.price }</span>
						</h4>
						<h4>
							<span class="text-muted">개수 : ${ searchedItem.number }</span>
						</h4>
						<p>${ searchedItem.content }</p>

						<div class="row">
							<div class="col-md-12 mb-md-5 mb-0 col-lg-6">
								<div class="unit-4">
									<div>
										<c:if
											test="${ login_member.member_id eq 'admin' or login_member.member_id eq searchedItem.member_id }">
											<button class="btn btn-default" id="update_item">수정</button>
											<button class="btn btn-default" id="delete_item">삭제</button>
										</c:if>
										<form method="post" id="form6">
											<input type="hidden" name="board_id"
												value="${ searchedItem.board_id }"> <input
												type="hidden" name="image" value="${ searchedItem.image }">
											<input type="hidden" name="title"
												value="${ searchedItem.title }"> <input
												type="hidden" name="category"
												value="${ searchedItem.category }">
										</form>
										<button class="btn btn-default" id="buy" type="button">구매</button>
										<button class="btn btn-default" id="add_cart" type="button">장바구니에
											추가</button>
									</div>
								</div>

								<button class="btn btn-default" id="like" type="button">좋아요[${searchedItem.like_cnt}]</button>
								<button class="btn btn-default" id="dislike" type="button">싫어요[${searchedItem.dislike_cnt}]</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">
			<ul id="myTab" class="nav nav-tabs" role="tablist">
				<li role="presentation" class=""><a href="#home"
					id="home-tab" role="tab" data-toggle="tab" aria-controls="home"
					aria-expanded="true">댓글[${ searchedItem.comment_cnt }]</a></li>
				<li role="presentation" class=""><a href="#profile" role="tab"
					id="profile-tab" data-toggle="tab" aria-controls="profile"
					aria-expanded="false">리뷰</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div role="tabpanel" class="tab-pane fade active in" id="home"
					aria-labelledby="home-tab">

					<c:if test="${ login_member != null }">
						<form action="" method="post" id="comment_form">
							<input type="hidden" name="member_id" value="${ login_member.member_id }">
							<input type="hidden" name="nickname" value="${ login_member.nickname }">
							<input type="hidden" name="board_id" value="${ searchedItem.board_id }">
							<input type="hidden" name="topic" value="${ searchedItem.topic }">
							<textarea rows="5" cols="" class="form-control" name="content"
								placeholder="Comment"></textarea>
							<button class="btn btn-default" id="add_comment" type="button">댓글
								작성</button>
						</form>
					</c:if>

					<c:forEach items="${ commentList }" var="comment">
						<div class="rw">
							<div class="bpd">
								<div class="bpb">
									<h6>${ comment.nickname }</h6>
									<small class="acx axc">${ comment.write_date }</small>
								</div>
								<p>${ comment.content }</p>
							</div>
							<c:if test="${ comment.member_id == login_member.member_id or login_member.auth >= 2 }">
								<button class="" name="comment_delete_btn" type="button" value="${ comment.comment_id }">댓글 삭제</button>
							</c:if>
						</div>
					</c:forEach>

				</div>
				<div role="tabpanel" class="tab-pane fade" id="profile"
					aria-labelledby="profile-tab">
					<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla
						single-origin coffee squid. Exercitation +1 labore velit, blog
						sartorial PBR leggings next level wes anderson artisan four loko
						farm-to-table craft beer twee. Qui photo booth letterpress,
						commodo enim craft beer mlkshk aliquip jean shorts ullamco ad
						vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic
						magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna
						velit sapiente labore stumptown. Vegan fanny pack odio cillum wes
						anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa
						terry richardson biodiesel. Art party scenester stumptown, tumblr
						butcher vero sint qui sapiente accusamus tattooed echo park.</p>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	<!-- 댓글, 리뷰 숨기기 보이기 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#home-tab").on("click", function() {
				$("#home").show();
				$("#profile").hide();
			})
			
			$("#profile-tab").on("click", function() {
				$("#profile").show();
				$("#home").hide();
			})
		})
		
		$("#home").hide();
		$("#profile").hide();
	</script>
	
	<!-- 댓글 작성 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#add_comment").on("click", function() {
				var params=$("#comment_form").serialize();
				console.log(params);

				$.ajax({
		            url: "<%=request.getContextPath()%>/comment_add",
		            type: "post",
		            data: params,
		            success: function(data){
		                console.log(data);
		                alert("댓글 작성을 완료했습니다.");
		                
		                var Now = new Date();
		                var NowTime = Now.getFullYear();
		                
		                if((Now.getMonth() + 1)>10)
		                	NowTime += '-' + (Now.getMonth() + 1);
		                else
		                	NowTime += '-' + '0' + (Now.getMonth() + 1);
		                
		                if(Now.getDate()>10)
		                	NowTime += '-' + Now.getDate();
		                else
		                	NowTime += '-' + '0' + Now.getDate();
		                
		                if(Now.getHours()>10)
		                	NowTime += ' ' + Now.getHours();
		                else
		                	NowTime += ' ' + '0' + Now.getHours();
		                
		                if(Now.getMinutes()>10)
		                	NowTime += ':' + Now.getMinutes();
		                else
		                	NowTime += ':' + '0' + Now.getMinutes();
		                
		                if(Now.getSeconds()>10)
		                	NowTime += ':' + Now.getSeconds();
		                else
		                	NowTime += ':' + '0' + Now.getSeconds();
		                
		                NowTime += '.0';
		                
						var tag="";
						tag+='<div class="rw">';
						tag+='<div class="bpd">';
						tag+='<div class="bpb">';
						tag+='<h6>'+data.nickname+'</h6>';
						tag+='<small class="acx axc">'+NowTime+'</small>';
						tag+='</div>';
						tag+='<p>'+data.content+'</p>';
						tag+='</div>';
						if( (data.member_id == "${login_member.member_id}" ) || ${login_member.auth >= 2})
							tag+='<button class="" name="comment_delete_btn" type="button" value="'+data.comment_id+'">댓글 삭제</button>';
						tag+='</div>';
						
						$("#home").append(tag);
		            },
		            error: function(){
		                alert("댓글 작성을 실패했습니다.");
		            }
		        });
			});
		});
	</script>
	
	<!-- 댓글 삭제 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("button[name=comment_delete_btn]").on("click", function() {
				var rw=$(this).parent(".rw");
				var comment_id=$(this).val();
				console.log(comment_id);
				
				
				$.ajax({
		            url: "<%=request.getContextPath()%>/comment_delete",
		            type: "post",
		            data: "comment_id="+comment_id,
		            success: function(data){
		                alert(data);
		                
		                rw.empty();
						rw.remove();
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