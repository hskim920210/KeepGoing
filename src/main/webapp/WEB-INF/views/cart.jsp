<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<title>장바구니</title>

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
						class="text-white font-weight-light text-uppercase font-weight-bold">장바구니</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<div class="site-section">
		<div class="container">
			<table class="table table-bordered col-xs-12" style="margin: auto; text-align: center; vertical-align: middle;">
				<thead>
					<tr>
						<th><input type="checkbox" id="all_check"></th>
						<th>번호</th>
						<th>이미지</th>
						<th>제목</th>
						<th>가격</th>
						<th>수량</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${ login_member eq null }" var="r">
						<c:forEach items="${ sessionScope.cartList }" var="result"
							varStatus="status">
							<tr>
								<td style="vertical-align: middle;"><input type="checkbox" name="checkbox"
									value="${ result.price }"></td>
								<td style="vertical-align: middle;">${ status.count }</td>
								<td style="vertical-align: middle;"><img style="width: 100px; height: 100px;"
									alt="No Image"
									src="<%=request.getContextPath()%>/resources/images/${result.image}"></td>
								<td style="vertical-align: middle;"><a href="<%=request.getContextPath()%>/item_view/${result.board_id}">${ result.title }</a></td>
								<td style="vertical-align: middle;">${ result.price }</td>
								<td style="vertical-align: middle;"><input style="border: 1px solid; text-align: center;" type="number"
									name="number" min="1" max="100" value="1"></td>
							</tr>
						</c:forEach>
					</c:if>

					<c:if test="${ not r }">
						<c:forEach items="${ requestScope.cartList }" var="result"
							varStatus="status">
							<tr>
								<td style="vertical-align: middle;"><input type="checkbox" name="checkbox"
									value="${ result.price }"></td>
								<td style="vertical-align: middle;">${ status.count }</td>
								<td style="vertical-align: middle;"><img style="width: 100px; height: 100px;"
									alt="No Image"
									src="<%=request.getContextPath()%>/resources/images/${result.image}"></td>
								<td style="vertical-align: middle;"><a href="<%=request.getContextPath()%>/item_view/${result.board_id}">${ result.title }</a></td>
								<td style="vertical-align: middle;">${ result.price }</td>
								<td style="vertical-align: middle;"><input style="border: 1px solid; text-align: center;" type="number"
									name="number" min="1" max="100" value="1"></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="3">총계</td>
						<td colspan="3" id="total">0</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>


	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>

	
	
 <jsp:include page="site_footer.jsp"></jsp:include>

	

	<script type="text/javascript">
		$(document).ready(function() {
			$("#all_check").on("click", function() {
				var total = 0;
				
				if( $("#all_check").prop("checked") ){
					$("input[name=checkbox]").prop("checked", true);
					
					$('input[name=checkbox]:checked').each(function(i, c){
						total += isNaN(parseInt($(this).val())) ? 0 : parseInt($(this).val()) * parseInt( $("input[name=number]").eq(i).val() );
					});
					
					$("#total").text(total);
				}
				else{
					$("input[name=checkbox]").prop("checked", false);
					
					$("#total").text(total);
				}
			})
		});
	</script>
	
	<script type="text/javascript">
		
		$(document).ready(function() {
			$("input[name=checkbox], input[name=number]").change(function (){
				var total = 0;
				var checkbox=$("input[name=checkbox]:checked");
				
				checkbox.each(function(index){
					var tr=checkbox.parent().parent().eq(index);
					var td=tr.children();
					
					var price=parseInt( td.eq(4).text() );
					var number=parseInt( td.eq(5).children().val() );
					
					total+=price*number;
				})
				
				$("#total").text(total);
			});
		})
	</script>

</body>
</html>