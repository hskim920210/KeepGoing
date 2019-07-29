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
						class="text-white font-weight-light text-uppercase font-weight-bold">회원가입</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<%----------                          
	<div class="container">
    <h3>회원가입 폼 입니다.</h3>
    <form action="/ajax/signup" method="post" id="myForm">
        <div class="form-group has-feedback">
            <label class="control-label" for="id">아이디</label>
            <input class="form-control" type="text" name="id" id="id"/>
            <span id="overlapErr" class="help-block">사용할 수 없는 아이디 입니다.</span>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="pwd">비밀번호</label>
            <input class="form-control" type="password" name="pwd" id="pwd"/>
            <span id="pwdRegErr" class="help-block">8글자 이상 입력하세요.</span>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="rePwd">비밀번호 재확인</label>
            <input class="form-control" type="password" name="rePwd" id="rePwd"/>
            <span id="rePwdErr" class="help-block">비밀번호와 일치하지 않습니다. 다시 입력해 주세요.</span>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="email">이메일</label>
            <input class="form-control" type="text" name="email" id="email"/>
            <span id="emailErr" class="help-block">올바른 이메일 형식이 아닙니다. 다시 입력해 주세요.</span>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <button class="btn btn-success" type="submit">가입</button>
    </form>
</div>
	
              
	--------%>




	<!-- 회원가입 -->

	<div class="site-section block-13">

		<!--Body-->
		<div class="modal-body" align="center">
			<form action="" method="post" id="regist_form" style="width: 400px">

				<c:if test="${ login_sns_member != null }" var="r">
					<h3>소셜 로그인을 통한 회원가입</h3>
					<input type="hidden" name="member_id"
						value="${ login_sns_member.member_id }">
					<input type="hidden" name="password"
						value="${ login_sns_member.password }">
					<input type="hidden" name="member_type"
						value="${ login_sns_member.member_type }">

					<div class="mb-3" align="left">
						<label for="nickname">*이름 </label> <input type="text"
							class="form-control" id="name" name="name" placeholder="Name"
							onkeyup="this.value=this.value.replace(/[^0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]/g,''); javascript: nameCheck();">
						<div class="invalid-feedback">X</div>
						<div id="NameCheck"></div>
					</div>

					<div class="mb-3" align="left">
						<label for="nickname">*별명 </label> <input type="text"
							onkeyup="this.value=this.value.replace(/[^0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]/g,'');"
							class="form-control" id="nickname" name="nickname" maxlength="8"
							placeholder="NickName">
						<div class="invalid-feedback">X</div>
						<div id="NickCheck"></div>
					</div>

					<div class="mb-3" align="left">
						<label for="tel">전화번호 </label> <input type="text"
							class="form-control" id="tel" name="tel" maxlength="11"
							placeholder="'-' 없이 입력">
						<div class="invalid-feedback">X</div>
					</div>

					<div class="mb-3" align="left">
						<label for="address">주소 </label><br> <input type="button"
							onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" class="form-control" id="sample6_postcode"
							name="address_post" placeholder="우편번호"> <input
							type="text" class="form-control" id="sample6_address"
							name="address_basic" placeholder="주소"> <input type="text"
							class="form-control" id="sample6_detailAddress"
							name="address_detail" placeholder="상세주소"><br> <input
							type="text" class="form-control" id="sample6_extraAddress"
							placeholder="참고항목">
						<div class="invalid-feedback">X</div>
					</div>


					<!-- 주소검색 API -->
					<%-- 
				<input type="text" id="sample6_postcode" placeholder="우편번호">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample6_address" placeholder="주소"><br>
				<input type="text" id="sample6_detailAddress" placeholder="상세주소">
				<input type="text" id="sample6_extraAddress" placeholder="참고항목">
				 --%>

					<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
					<script>
				    function sample6_execDaumPostcode() {
				        new daum.Postcode({
				            oncomplete: function(data) {
				                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				
				                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
				                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				                var addr = ''; // 주소 변수
				                var extraAddr = ''; // 참고항목 변수
				
				                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				                    addr = data.roadAddress;
				                } else { // 사용자가 지번 주소를 선택했을 경우(J)
				                    addr = data.jibunAddress;
				                }
				
				                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				                if(data.userSelectedType === 'R'){
				                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
				                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				                        extraAddr += data.bname;
				                    }
				                    // 건물명이 있고, 공동주택일 경우 추가한다.
				                    if(data.buildingName !== '' && data.apartment === 'Y'){
				                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                    }
				                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				                    if(extraAddr !== ''){
				                        extraAddr = ' (' + extraAddr + ')';
				                    }
				                    // 조합된 참고항목을 해당 필드에 넣는다.
				                    document.getElementById("sample6_extraAddress").value = extraAddr;
				                
				                } else {
				                    document.getElementById("sample6_extraAddress").value = '';
				                }
				
				                // 우편번호와 주소 정보를 해당 필드에 넣는다.
				                document.getElementById('sample6_postcode').value = data.zonecode;
				                document.getElementById("sample6_address").value = addr;
				                // 커서를 상세주소 필드로 이동한다.
				                document.getElementById("sample6_detailAddress").focus();
				            }
				        }).open();
				    }
				</script>

					<div class="mb-3" align="left">
						<label for="interest">*관심분야 </label>
						<div class="row" align="left">
							<div class="col-md-5">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address1" value="운동"> <label
										class="custom-control-label" for="same-address1">운동</label>
								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address2" value="식이요법"> <label
										class="custom-control-label" for="same-address2">식이요법</label>
									<div class="invalid-feedback">X</div>

								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address3" value="운동기구"> <label
										class="custom-control-label" for="same-address3">운동기구</label>
									<div class="invalid-feedback">X</div>
								</div>
							</div>

							<div class="col-md-5">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address5" value="운동"> <label
										class="custom-control-label" for="same-address5">운동</label>
								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address6" value="식이요법"> <label
										class="custom-control-label" for="same-address6">식이요법</label>
									<div class="invalid-feedback">X</div>

								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address7" value="운동기구"> <label
										class="custom-control-label" for="same-address7">운동기구</label>
									<div class="invalid-feedback">X</div>
								</div>
							</div>
						</div>
					</div>
				</c:if>

				<c:if test="${ not r }">
					<h3>일반 회원가입</h3>
					<!-- --------------regist123------------- -->

					<input type="hidden" name="member_type" value="0">

					<div class="mb-3" align="left">
						<label for="member_id">*ID </label> <input type="text"
							onkeyup="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');"
							class="form-control" id="member_id" name="member_id"
							maxlength="20" placeholder="(영문과 숫자의 조합)">
						<div class="invalid-feedback">X</div>
						<div id="IDCheck"></div>

					</div>



					<div class="mb-3" align="left">
						<label for="password">*비밀번호 </label> <input type="password"
							class="form-control" name="password" id="password"
							onkeyup="passwordCheck()" maxlength="20"
							placeholder="대소문자 및 특수문자 조합하여 입력하세요">
						<div class="invalid-feedback">X</div>

					</div>

					<div class="mb-3" align="left">
						<label for="reenter_password">*비밀번호 확인 </label> <input
							type="password" class="form-control" id="reenter_password"
							name="reenter_password" maxlength="20" onkeyup="passwordCheck()"
							placeholder="위의 password와 같이 입력">
						<div class="invalid-feedback">X</div>
						<div id="passwordCheck"></div>
					</div>

					<div class="mb-3" align="left">
						<label for="name">*이름 </label> <input type="text" maxlength="8"
							class="form-control" id="name" name="name" onkeyup="nameCheck()"
							placeholder="Name">
						<div class="invalid-feedback">X</div>
						<div id="NameCheck"></div>
					</div>

					<div class="mb-3" align="left">
						<label for="nickname">*별명 </label> <input type="text"
							onkeyup="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');"
							class="form-control" id="nickname" name="nickname" maxlength="8"
							placeholder="NickName">
						<div class="invalid-feedback">X</div>
						<div id="NickCheck"></div>
					</div>

					<div class="mb-3" align="left">
						<label for="tel">전화번호 </label> <input type="text"
							onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"
							class="form-control" id="tel" name="tel" maxlength="11"
							placeholder="'-' 없이 입력">
						<div class="invalid-feedback">X</div>
					</div>

					<div class="mb-3" align="left">
						<label for="address">주소 </label><br> <input type="button"
							onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" class="form-control" id="sample6_postcode"
							name="address_post" placeholder="우편번호"> <input
							type="text" class="form-control" id="sample6_address"
							name="address_basic" placeholder="주소"> <input type="text"
							class="form-control" id="sample6_detailAddress"
							name="address_detail" placeholder="상세주소"><br> <input
							type="text" class="form-control" id="sample6_extraAddress"
							placeholder="참고항목">
						<div class="invalid-feedback">X</div>
					</div>


					<!-- 주소검색 API -->
					<%-- 
				<input type="text" id="sample6_postcode" placeholder="우편번호">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample6_address" placeholder="주소"><br>
				<input type="text" id="sample6_detailAddress" placeholder="상세주소">
				<input type="text" id="sample6_extraAddress" placeholder="참고항목">
				 --%>

					<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
					<script>
				    function sample6_execDaumPostcode() {
				        new daum.Postcode({
				            oncomplete: function(data) {
				                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				
				                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
				                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				                var addr = ''; // 주소 변수
				                var extraAddr = ''; // 참고항목 변수
				
				                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				                    addr = data.roadAddress;
				                } else { // 사용자가 지번 주소를 선택했을 경우(J)
				                    addr = data.jibunAddress;
				                }
				
				                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				                if(data.userSelectedType === 'R'){
				                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
				                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				                        extraAddr += data.bname;
				                    }
				                    // 건물명이 있고, 공동주택일 경우 추가한다.
				                    if(data.buildingName !== '' && data.apartment === 'Y'){
				                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                    }
				                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				                    if(extraAddr !== ''){
				                        extraAddr = ' (' + extraAddr + ')';
				                    }
				                    // 조합된 참고항목을 해당 필드에 넣는다.
				                    document.getElementById("sample6_extraAddress").value = extraAddr;
				                
				                } else {
				                    document.getElementById("sample6_extraAddress").value = '';
				                }
				
				                // 우편번호와 주소 정보를 해당 필드에 넣는다.
				                document.getElementById('sample6_postcode').value = data.zonecode;
				                document.getElementById("sample6_address").value = addr;
				                // 커서를 상세주소 필드로 이동한다.
				                document.getElementById("sample6_detailAddress").focus();
				            }
				        }).open();
				    }
				</script>




					<div class="mb-3" align="left">
						<label for="interest">*관심분야 </label>
						<div class="row" align="left">
							<div class="col-md-5">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address1" value="운동"> <label
										class="custom-control-label" for="same-address1">운동</label>
								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address2" value="식이요법"> <label
										class="custom-control-label" for="same-address2">식이요법</label>
									<div class="invalid-feedback">X</div>

								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address3" value="운동기구"> <label
										class="custom-control-label" for="same-address3">운동기구</label>
									<div class="invalid-feedback">X</div>
								</div>
							</div>

							<div class="col-md-5">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address5" value="운동"> <label
										class="custom-control-label" for="same-address5">운동</label>
								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address6" value="식이요법"> <label
										class="custom-control-label" for="same-address6">식이요법</label>
									<div class="invalid-feedback">X</div>

								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address7" value="운동기구"> <label
										class="custom-control-label" for="same-address7">운동기구</label>
									<div class="invalid-feedback">X</div>
								</div>
							</div>
						</div>
					</div>
				</c:if>

				<div class="text-center form-sm mt-2">
					<button class="btn btn-info" type="button" id="member_regist">
						작성완료 <i class="fas fa-sign-in ml-1"></i>
					</button>
					<button class="btn btn-info" type="reset" id="">
						초기화 <i class="fas fa-sign-in ml-1"></i>
					</button>
				</div>
			</form>

		</div>
		<!--Footer-->
		<div class="modal-footer">
			<div class="options text-right">
				<p class="pt-1">
					이미 아이디가 있습니까? <a href="#" class="blue-text">로그인 하기</a>
				</p>
			</div>
		</div>
	</div>
	<!-- 회원가입 -->

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>

	<script type="text/javascript">

		var isIdOk = false;
		var isPwOk = false;
		var isNickOk = false;
		var isNameOk = false
		var isInterestOk = false;
		var member_id = '';
		var nickname = '';
		
		var login_sns_member = '<%= session.getAttribute("login_sns_member") %>';
		
		if( login_sns_member != 'null' ) {
			isIdOk = true;
			isPwOk = true;
		}
		
		console.log($("input:checkbox[name='interest']").is(":checked"));

		
		// 아이디 체크
		$("#member_id").on("keyup", function() {
			member_id = $("#member_id").val().trim();
			if(member_id.length > 5){
				var params=$("#regist_form").serialize();
				$.ajax({
		            url: "<%=request.getContextPath()%>/idCheck",
		            type: "POST",
		            data: params,
		            success: function(data){
		            	if(data == 'idOk'){
			                $("#IDCheck").text('가입 가능한 아이디');
		            		isIdOk = true;
		            	} else if (data == 'idNo'){
			                $("#IDCheck").text('중복된 아이디');
			                isIdOk = false;
		            	} 
		            },
		            error: function(){
		            	console.log("오류 발생");
		            }
		        });
			} else {
				isIdOk = false;
				$("#IDCheck").text('아이디는 6자 이상 20자 미만입니다.');
			}
		});
		
		// 닉네임
		$("#nickname").on("keyup", function() {
			nickname = $("#nickname").val().trim();
			if(nickname.length > 2){
				var params=$("#regist_form").serialize();
				$.ajax({
		            url: "<%=request.getContextPath()%>/nickCheck",
		            type: "POST",
		            data: params,
		            success: function(data){
		            	if(data == 'nickOk'){
			                $("#NickCheck").text('가입 가능한 별명');
		            		isNickOk = true;
		            	} else if (data == 'nickNo'){
			                $("#NickCheck").text('중복된 별명');
			                isNickOk = false;
		            	} 
		            },
		            error: function(){
		            	console.log("오류 발생");
		            }
		        });
			} else {
				isNickOk = false;
				$("#NickCheck").text('별명은 3자 이상 10자 미만입니다.');
			}
			
			
		});
		
		// 비밀번호 일치
		function passwordCheck() {
			if( $("#password").val() == $("#reenter_password").val() ){
				 console.log("일치");
				 $("#passwordCheck").text("일치");
				 isPwOk=true;
			}else{
				 console.log("password 불일치");
				 $("#passwordCheck").text("불일치");
				 isPwOk=false;
			}
			
		};
		
		// 이름 입력
		function nameCheck() {
			console.log($("#name").val().trim().length);
			if( $("#name").val().trim().length > 0 && $("#name").val().trim().length < 9 ){
				 isNameOk=true;
				 $("#NameCheck").text("사용 가능한 이름입니다.");
			}else {
				 $("#NameCheck").text("이름은 8글자 이하입니다.");
				 isNameOk=false;
			}
		};
		
		
		// 회원가입버튼
		$("#member_regist").on("click", function() {
			if( $("input:checkbox[name='interest']").is(":checked") == false ){	
				alert("관심사는 최소 1개 이상 체크해야 합니다.");
				console.log($("input:checkbox[name='interest']").is(":checked"));
				isInterestOk = false;
			} else {
				isInterestOk = true;
				console.log($("input:checkbox[name='interest']").is(":checked"));
			}
			
			alert( "회원가입 검증 결과 : " + 
					"isIdOk : " + isIdOk + ", isPwOk : " + isPwOk + ", isNickOk : " + isNickOk + ", isNameOk : " + isNameOk + ", isInterestOk : " + isInterestOk);
			
			var result = isIdOk && isPwOk && isNickOk && isNameOk && isInterestOk; 
			console.log('result : ' + result);
			if( result == true ) {
				var params=$("#regist_form").serialize();
				$.ajax({
		            url: "<%=request.getContextPath()%>/regist",
		            type: "POST",
		            data: params,
		            success: function(data){
		                console.log(data);
		                alert(data);
						location.href='<%=request.getContextPath()%>/home'
						},
					error : function() {
								alert("err");
						}
				});
			}
		});
	</script>

</body>
</html>
<%--
			$("#member_regist").on("click", function() {
				var params=$("#regist_form").serialize();
				var member_id = $("#member_id").val().trim();
				var password = $("#password").val().trim();
				var interest = $("input:checkbox[name='interest']").is(":checked")
				console.log(params);
				console.log(interest);
				
				if( member_id.length > 5 && password.length > 5 ){
					//////// 아이디 password 체크구간
					$.ajax({
			            url: "<%=request.getContextPath()%>/registCheck",
			            type: "POST",
			            data: params,
			            success: function(data){
			                console.log(data);
			                alert(data);
							
			            },
			            error: function(){
			                alert("err");
			            }
			        });
				}
					
					if( interest == false ){	
						alert("관심사는 최소 1개 이상 체크해야 합니다.");
						
						} else {
						if( $("#password").val() == $("#reenter_password").val() ){
							$.ajax({
					            url: "<%=request.getContextPath()%>/regist",
					            type: "POST",
					            data: params,
					            success: function(data){
					                console.log(data);
					                alert(data);
					                location.href="<%=request.getContextPath()%>/index";
					            },
					            error: function(){
					                alert("다시 확인 해주세요");
					            }
					            
					        });
						} else {
							 alert("password가 틀림1");
							}
						}
						
						} else {
							 alert("ID 또는 password 6자 이상 입니다.");
						}
				
					////////////관심사 체크 구간
				--%>




<%-- 
	<script type="text/javascript">
		function passwordCheck() {
			if( $("#password").val() == $("#reenter_password").val() ){
				 console.log("일치");
				 $("#passwordCheck").text("일치");
			}else{
				 console.log("password 불일치");
				 $("#passwordCheck").text("불일치");
			}
			
		}
		$(document).ready(function() {
			$("#member_id").on("keyup", function() {
			
				var params=$("#regist_form").serialize();
				$.ajax({
		            url: "<%=request.getContextPath()%>/registCheck",
		            type: "POST",
		            data: params,
		            
		            success: function(data){
		            	
		            	if(data == '아이디 중복'){
			                $("#IDCheck").text(data);
		            	} 
		            	console.log(data);
		            	if( data == '닉네임 중복'){
			                $("#NickCheck").text(data);
		            	}
		                
						
		            },
		            error: function(){
		            	console.log("오류 발생");
		            }
		        });
		
			})
		})
		
		</script>
	--%>





















