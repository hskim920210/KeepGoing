<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="chattingModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog cascading-modal" role="document">
		<!--Content-->
		<div class="modal-content">

			<!--Modal cascading tabs-->
			<div class="modal-c-tabs">

				<!-- Nav tabs -->
				<ul class="nav nav-tabs md-tabs tabs-2 light-blue darken-3"
					role="tablist">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#panel6" role="tab"><i
							class="fas fa-user mr-1"></i> 관리자와 QnA</a></li>
					<%--
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#panel7" role="tab"><i
							class="fas fa-user mr-1"></i> 관심사별 그룹</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#panel9" role="tab"><i class="fas fa-user-plus mr-1"></i>
							판매자와 1:1</a></li>
					--%>
					<button type="button"
						class="btn btn-outline-info waves-effect ml-auto"
						data-dismiss="modal">X</button>
				</ul>

				<!-- Tab panels -->
				<div class="tab-content">
					<!--Panel 6-->
					<div class="tab-pane fade in show active" id="panel6"
						role="tabpanel">
						<!--Body-->
						<div class="modal-body">
							<div class="md-form form-sm mb-6">
								<i class="fas fa-envelope prefix"></i>
							</div>

							<div class="text-center mt-2">
								<textarea rows="20" cols="42" id="chatArea" name="chatArea" readonly="readonly" style="resize: none;"><c:if test="${ empty login_member }">로그인이 필요합니다.</c:if>
								</textarea>
							</div>

						</div>
						<!--Footer-->
						<div class="modal-footer">
							<div class="options text-center text-md-right mt-1"
								style="width: 90%;">
								<input type="text" id="nickname" name="nickname" readonly="readonly" value="${ login_member.nickname }">님의 문의
								<button type="button" id="connBtn" name="connBtn" style="visibility: visible;"
								class="btn btn-outline-info waves-effect ml-auto">연결</button>
								<button type="button" id="closeBtn" name="closeBtn" style="visibility: hidden;"
								class="btn btn-outline-info waves-effect ml-auto">연결해제</button>
								<textarea rows="4" cols="45" id="message" name="message" onkeyup="enterkey();" style="resize: none;"></textarea>
							</div>
							<button type="button" id="sendBtn" name="sendBtn" style="visibility: hidden;"
								class="btn btn-outline-info waves-effect ml-auto">전송</button>
						</div>
					</div>
					<!--/.Panel 6-->
				</div>
			</div>
		</div>
		<!--/.Content-->
	</div>
</div>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function enterkey() {
    if (window.event.keyCode == 13) {
         // 엔터키가 눌렸을 때 실행할 내용
         sendMessage();
    }
}


	// 웹 소켓 변수
	var wsocket = null;
	
	// 메세지를 보내고자 하는 대상을 저장하고 있는 전역변수
	var messageTarget = "admin";
	if( '${ login_member.member_id }' == 'admin' ){
		messageTarget = "client";
	}
	
	var receiver = "admin";
	if( '${login_member.member_id}' == 'admin' ){
		receiver = "client";
	}
	
	var sender = "${login_member.member_id}";
	
	$(document).ready(function() {
		$('#connBtn').click(function() { sockConnect(); });
		$('#sendBtn').click(function() { sendMessage(); });
		$('#closeBtn').click(function() { sockClose(); });
	});
	
	function sockConnect() {
		var nickname = $("#nickname").val().trim();
		if( wsocket != null )
			return;
		if( nickname.length == 0 ) {
			alert("로그인이 필요합니다.\n");	
			return;
		};
		wsocket = 
			new WebSocket("ws://localhost:8080/webapp/chat_admin");
		// wsocket.onopen = function() {wsocket.getConn();};
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;

		$("#sendBtn").css('visibility', 'visible');
		$("#connBtn").css('visibility', 'hidden');
		$("#closeBtn").css('visibility', 'visible');
		var message = $("#chatArea").html("서버와 연결되었습니다.\n");	
	}

	
	function sockClose() {
		if( wsocket == null )
			return;
		$("#sendBtn").css('visibility', 'hidden');
		$("#connBtn").css('visibility', 'visible');
		$("#closeBtn").css('visibility', 'hidden');
		wsocket.close();
		wsocket = null;
		var message = $("#chatArea").html("관리자와 연결이 해제되었습니다.\n");	
	}
	
	function sendMessage() {
		if( wsocket == null ) {
			alert("웹 소켓이 연결되지 않았습니다.\n")
			return;
		}
		/*//
		var selected = $("input[name=client]").is(':checked');
		if( selected ){
			var receiver = $("input[name=client]").val();
		}
		//
		*/
		//var msg = "to:" + messageTarget + "@" + $("#message").val() + "\n";
		var msg = sender + " : " + $("#message").val() + "\n";
		var viewMsg = $("#chatArea").html();
		wsocket.send(msg);
		viewMsg += msg;
		$("#chatArea").html(viewMsg);	
		$("#message").val('');
	}
	
	function onMessage(evt) {
		var data = evt.data;
		var message = $("#chatArea").html()
		message += data + "\n";
		$("#chatArea").html(message);	
	}
		
	function onClose(evt) {
		var message = $("#chatArea").html()
		message += "--연결종료--" + "\n";
		$("#chatArea").html(message);	
	}
</script>



<%--
					<!--Panel 7-->
					<div class="tab-pane fade in show active" id="panel7"
						role="tabpanel">
						<!--Body-->
						<div class="modal-body">
							<div class="md-form form-sm mb-5" align="center">
								<i class="fas fa-envelope prefix"></i> <select
									name="chatCategory" style="display: inline;">
									<option value="1" selected="selected">웨이트 트레이닝</option>
									<option value="2">다이어트</option>
									<option value="3">요가</option>
									<option value="4">필라테스</option>
									<option value="5">스피닝</option>
									<option value="6">식이요법</option>
								</select>
							</div>

							<div class="md-form form-sm mb-5">
								<i class="fas fa-lock prefix"></i>
								<textarea rows="4" cols="45">검색 결과</textarea>
							</div>
							<!-- 
              <div class="md-form form-sm mb-4">
                <i class="fas fa-lock prefix"></i>
                <input type="password" id="modalLRInput14" class="form-control form-control-sm validate">
                <label data-error="wrong" data-success="right" for="modalLRInput14">Repeat password</label>
              </div>

              <div class="text-center form-sm mt-2">
                <button class="btn btn-info">Sign up <i class="fas fa-sign-in ml-1"></i></button>
              </div>
				 -->

							<div class="text-center mt-2">
								<textarea rows="20" cols="42"></textarea>
							</div>
						</div>
						<!--Footer-->
						<div class="modal-footer">
							<div class="options text-center text-md-right mt-1"
								style="width: 90%;">
								<textarea rows="4" cols="45"></textarea>
							</div>
							<button type="button"
								class="btn btn-outline-info waves-effect ml-auto"
								data-dismiss="modal">전송</button>
						</div>
					</div>
					<!--/.Panel 7-->

					<!--Panel 9-->
					<div class="tab-pane fade" id="panel9" role="tabpanel">
						<!--Body-->
						<div class="modal-body mb-1">
							<div class="md-form form-sm mb-5">
								<i class="fas fa-envelope prefix"></i> <select
									name="chatCategory" style="display: inline; height: 42px;">
									<option value="1" selected="selected">상품번호</option>
									<option value="2">판매자</option>
								</select> <input type="text" class="form-control" name="keyword"
									placeholder="대화할 판매자를 검색하세요."
									style="width: 60%; display: inline;">
								<button class="btn btn-info" type="submit" id="review_search"
									style="display: inline;">
									검색 <i class="fas fa-sign-in ml-1"></i>
								</button>
							</div>
							<div class="md-form form-sm mb-4" align="center">
								<i class="fas fa-lock prefix"></i>
								<textarea rows="4" cols="45">검색 결과가 올 자리</textarea>
							</div>
							<div class="text-center mt-2">
								<textarea rows="15" cols="42">대화내용이 올 자리</textarea>
							</div>
						</div>
						<!--Footer-->
						<div class="modal-footer">
							<div class="options text-center text-md-right mt-1"
								style="width: 90%;">
								<textarea rows="4" cols="45">메세지를 작성할 자리</textarea>
							</div>
							<button type="button"
								class="btn btn-outline-info waves-effect ml-auto"
								data-dismiss="modal">전송</button>
						</div>
					</div>
					<!--/.Panel 9-->
				</div>
			</div>
		</div>
		<!--/.Content-->
	</div>
</div>
<!--Modal: Login / Register Form-->
 --%>









<%--
<!--Modal: Login / Register Form-->
<div class="modal fade" id="chattingModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog cascading-modal" role="document">
		<!--Content-->
		<div class="modal-content">

			<!--Modal cascading tabs-->
			<div class="modal-c-tabs">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs md-tabs tabs-2 light-blue darken-3"
					role="tablist">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="1#panel7" role="tab"><i
							class="fas fa-user mr-1"></i> 판매자와 1:1 </a></li>
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="1#panel8" role="tab"><i
							class="fas fa-user mr-1"></i> 관심사 그룹 </a></li>
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="1#panel9" role="tab"><i
							class="fas fa-user mr-1"></i> 관리자와 QnA </a></li>
				</ul>

				<!-- Tab panels -->
				<div class="tab-content">
					<!--Panel 7-->
					<div class="tab-pane fade in show active" id="panel7"
						role="tabpanel">
						<!--Body-->
						<div class="modal-body mb-1">
							<form action="<%=request.getContextPath()%>/review/search"
								method="post" id="searchForm">
								<div class="mb-3">
									<select name="chatCategory"  style="display: inline;">
										<option value="1" selected="selected">전체</option>
										<option value="2">상품 번호</option>
										<option value="3">판매자</option>
									</select> 
									<input type="text" class="form-control" name="keyword"
										placeholder="검색어를 입력해주세요."  style="width: 60%; display: inline;">
									<button class="btn btn-info" type="submit" id="review_search"  style="display: inline;">
										검색 <i class="fas fa-sign-in ml-1"></i>
									</button>
								</div>
								
								
								<div class="mb-3">
								
								</div>
								<div class="text-center mt-2">
									

								</div>
							</form>
						</div>
					</div>
					<!--/.Panel 7-->
					
					<!--Panel 8-->
					<div class="tab-pane fade in show active" id="panel8"
						role="tabpanel">
						<!--Body-->
						<div class="modal-body mb-1">
							<form action="<%=request.getContextPath()%>/review/search"
								method="post" id="searchForm">
								<div class="mb-3">
									<select name="chatCategory"  style="display: inline;">
										<option value="1" selected="selected">웨이트 트레이닝</option>
										<option value="2">다이어트</option>
										<option value="3">요가</option>
										<option value="4">필라테스</option>
										<option value="5">스피닝</option>
										<option value="6">식이요법</option>
									</select> 
									<input type="text" class="form-control" name="keyword"
										placeholder="검색어를 입력해주세요."  style="width: 60%; display: inline;">
									<button class="btn btn-info" type="submit" id="review_search"  style="display: inline;">
										검색 <i class="fas fa-sign-in ml-1"></i>
									</button>
								</div>
								
								
								<div class="mb-3">
								
								</div>
								<div class="text-center mt-2">
									

								</div>
							</form>
						</div>
					</div>
					<!--/.Panel 8-->
				</div>
			</div>
		</div>
		<!--/.Content-->
	</div>
</div>
 --%>