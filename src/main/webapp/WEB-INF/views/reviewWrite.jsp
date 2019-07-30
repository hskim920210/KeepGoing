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

	<div class="site-section block-13" align="center" style="padding-bottom: 10; margin-left: 10%; margin-right: 10%; margin-top: 130;">
		<form action="<%= request.getContextPath() %>/review/write" method="post">
				<table style="width: 650px;">
				<tr>
					<th> <label for="nickname">작성자</label><input type="text" name="nickname" readonly="readonly" value="${ login_member.nickname }"> </th>
					<th> <input type="hidden" name="member_id" readonly="readonly" value="${ login_member.member_id }"> </th>
				</tr>
				<tr>
					<th> <label for="title">제목</label><input type="text" id="title" name="title" style="width: 100%;"></th>
					<th>
					<div class="btn-group">
					  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
					    <span class="caret">카테고리</span>
					  </button>
					  <ul class="dropdown-menu" role="menu">
					    <li value="1" onclick="adjustCategory('1');">전체</li>
					    <li class="divider"></li>
					    <li value="2" onclick="adjustCategory('2');">운동기구</li>
					    <li value="3" onclick="adjustCategory('3');">헬스장</li>
					    <li value="4" onclick="adjustCategory('4');">장소</li>
					  </ul>
					</div>
					<input type="hidden" id="category" name="category" value="0">
					</th>
				</tr>
				<tr>
					<td colspan="2"><textarea id="content" name="content" cols="60" rows="15"></textarea></td>
		     	</tr>
		     	<tr>
		     		<td><input type="button" id="insertBoard" name="insertBoard" value="등록하기"></td>
		     	</tr> 
				</table>
		</form>
	</div>
	


	<jsp:include page="javascriptIncludeForReview.jsp" flush="false"></jsp:include>
	<script type="text/javascript">
    $(function(){
        //전역변수
        var oEditors = [];              
        //스마트에디터 프레임생성
        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: "content",
            sSkinURI: "<%= request.getContextPath() %>/resources/editor/SmartEditor2Skin.html",
            fCreator: "createSEditor2",
            htParams : {
                // 툴바 사용 여부
                bUseToolbar : true,            
                // 입력창 크기 조절바 사용 여부
                bUseVerticalResizer : true,    
                // 모드 탭(Editor | HTML | TEXT) 사용 여부
                bUseModeChanger : true,
            }
        });
        /*
        $("#insertBoard").click(function(){
            //id가 smarteditor인 textarea에 에디터에서 대입
            obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
            //폼 submit
            $("#insertBoardFrm").submit();
        });
        */
    	var btn = document.getElementById("insertBoard");
    	btn.onclick = function () {
    		submitContents(btn);
    	}
    	
    	function submitContents(elClickedObj) {
    		var isTitle = false;
    		var isContent = false;
    		var isCategory = false;
    		var isOk = false;
    		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
    		
    		// 에디터 내용에 대한 검증은 여기서 한다.
    		// document.getElementById("content").value를 이용
    		if( $("#category").val() == 0 ) {
    			alert("카테고리를 선택해주세요.");
    			isCategory = false;
    		} else {
    			isCategory = true;
    		}
    		
    		var strTitle = $("#title").val();
    		var trimTitle = $.trim(strTitle);
    		if( trimTitle.length != 0 ){
    			isTitle = true;
    		} else {
    			alert("제목을 입력해주세요.");
    			isTitle = false;
    		}
    		
    		if( $("#content").val().trim != '' ) {
    			isContent = true;
    		} else {
    			alert("내용을 입력해주세요.");
    			isContent = false;
    		}
    		
    		isOk = isCategory && isTitle && isContent;
    		
    		if(isOk) {
	    		try {
	    			// 해당 오브젝트가 위치한 form이 submit 된다.
	    			elClickedObj.form.submit();
	    		} catch(e) {
	    			
	    		}
    		} else {
    			alert("누락된 내용을 기재해주세요.");
    		}
    	}
    });
	</script>
	
	
	<script type="text/javascript">
	function adjustCategory(e) {
		if(e == 1){
			$(".caret").text('전체');
			$("#category").val(1);
			
		} else if (e == 2){
			$(".caret").text('운동기구');
			$("#category").val(2);
			
		} else if (e == 3){
			$(".caret").text('헬스장');
			$("#category").val(3);
			
		} else if (e == 4){
			$(".caret").text('장소');
			$("#category").val(4);
		}
	}
	
	
	</script>
</body>
</html>