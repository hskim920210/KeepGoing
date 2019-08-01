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
						class="text-white font-weight-light text-uppercase font-weight-bold">리뷰</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<div class="site-section block-13"
		style="padding-bottom: 10; margin-left: 10%; margin-right: 10%; margin-top: 130;">
		<c:if test="${ login_member.auth == '99' }" var="r">
			<a href="<%=request.getContextPath()%>/review/write">리뷰 작성하기</a>
		</c:if>
		<div align="center">
			<table class="table">
				<tr>
					<td><a href="#">전체</a></td>
					<td><a href="#">운동기구</a></td>
					<td><a href="#">헬스장</a></td>
					<td><a href="#">장소</a></td>
				</tr>
			</table>

		</div>
	</div>

	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<div class="site-section block-13" style="padding-top: 5;">
			<div class="row">


				<table class="table">
					<tr align="center">
						<td colspan="3"><b style="color: black; font-size: 30px;">리뷰
								제목 : ${ detailReview.title }</b></td>
					</tr>

					<tr align="center">
						<td colspan="3"><div class="jumbotron">
								<a style="color: black; font-size: 15px;">${ detailReview.content }</a>
							</div></td>
					</tr>

					<tr>
						<td>
							<div class="mb-3" align="left">
								<label for="address">주소 </label><br> <input
									type="text" class="form-control" id="sample6_address" style="width: 70%"
									name="address_basic" readonly="readonly" value="${ detailReview.selectedAddress }">
							</div>
							<div id="map" style="width: 500px; height: 400px;" align="left">
								<!-- 지도 첨부 영역입니다. -->
							</div>
						</td>
					</tr>
					<tr>
						<td><b style="color: black; font-size: 15px;">작성 시간 : ${ detailReview.write_date }</b></td>
						<td><b style="color: black; font-size: 15px;">카테고리 : ${ detailReview.category }</b></td>
						<td><b style="color: black; font-size: 15px;">작성자 : ${ detailReview.nickname }</b></td>

					</tr>
				</table>

				<div>
					<form method="post" id="form6">
						<input type="hidden" name="board_id"
							value="${ detailReview.board_id }"> <input type="hidden"
							name="title" value="${ detailReview.title }"> <input
							type="hidden" name="content" value="${ detailReview.content }">
						<input type="hidden" name="category"
							value="${ detailReview.category }">
					</form>

				</div>

				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f7e87dcf28984113f6360f591c4d3f24&libraries=services,clustere"></script>
				<script type="text/javascript">
					var geocoder = new kakao.maps.services.Geocoder();
					var selectedAddress = '${ detailReview.selectedAddress }'; 
					var selectedLat = ${ detailReview.selectedLat };
					var selectedLng = ${ detailReview.selectedLng };

					var container = document.getElementById('map');
					// 아래 options에서 center는 지도를 생성하는데 필수이다.
					var options = {
						center : new kakao.maps.LatLng(selectedLat, selectedLng),
						level : 3
					// 지도의 레벨(확대, 축소의 정도)
					};
					var map = new kakao.maps.Map(container, options); // 지도의 생성 및 객체 리턴

					// 지도를 클릭한 위치에 마커를 생성
					var marker = new kakao.maps.Marker({
						// 중심 좌표에 마커를 생성
						position : map.getCenter()
					});
					marker.setMap(map);
					// 마커에 인포 윈도우를 표시하기.
					// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
					var iwContent = '<div style="padding:5px; font-size:11px;">'+selectedAddress+' <br><a href="https://map.kakao.com/link/map/'+selectedAddress+','+selectedLat+','+
					selectedLng+'" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/'+selectedAddress+','+selectedLat+','+selectedLng+'" style="color:blue" target="_blank">길찾기</a></div>', 
					iwPosition = new kakao.maps.LatLng(
							selectedLat, selectedLng); //인포윈도우 표시 위치입니다
					// 1. 인포 윈도우 생성
					var infowindow = new kakao.maps.InfoWindow({
						position : iwPosition,
						content : iwContent
					});
					// 2. 마커 위에 인포윈도우를 표시. 두번째 파라메터인 marker를 넣어주지 않으면 그냥 지도위에 표시됨.
					infowindow.open(map, marker);

				</script>

			</div>
		</div>
	</div>

	<jsp:include page="javascriptIncludeForReview.jsp" flush="false"></jsp:include>
</body>
</html>