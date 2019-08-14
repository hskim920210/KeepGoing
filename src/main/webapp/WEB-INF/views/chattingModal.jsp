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
			new WebSocket("ws://192.168.0.26:8080/webapp/chat_admin");
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
		var message = $("#chatArea").html("연결이 해제되었습니다.\n");	

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
