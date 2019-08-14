<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="chattingInterestModal" tabindex="-1" role="dialog"
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
						data-toggle="tab" href="#panel7" role="tab"><i
							class="fas fa-user mr-1"></i> 그룹채팅</a></li>
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
					<div class="tab-pane fade in show active" id="panel7"
						role="tabpanel">
						<!--Body-->
						<div class="modal-body">
							<div class="md-form form-sm mb-6">
								<i class="fas fa-envelope prefix"></i>
							</div>

							<div class="text-center mt-2">
								<textarea rows="20" cols="42" id="chatArea_Group" name="chatArea_Group" readonly="readonly" style="resize: none; overflow-x:hidden; overflow-y:auto;"><c:if test="${ empty login_member }">로그인이 필요합니다.</c:if>
								</textarea>
							</div>

						</div>
						<!--Footer-->
						<div class="modal-footer">
							<div class="options text-center text-md-right mt-1"
								style="width: 90%;">
								<input type="text" id="nicknameGroup" name="nicknameGroup" readonly="readonly" value="${ login_member.nickname }">님의 문의
								<button type="button" id="connGroupBtn" name="connGroupBtn" style="visibility: visible;"
								class="btn btn-outline-info waves-effect ml-auto">연결</button>
								<button type="button" id="closeGroupBtn" name="closeGroupBtn" style="visibility: hidden;"
								class="btn btn-outline-info waves-effect ml-auto">연결해제</button>
								<textarea rows="4" cols="45" id="messageGroup" name="messageGroup" onkeyup="enterkeyGroup();" style="resize: none;"></textarea>
							</div>
							<button type="button" id="sendGroupBtn" name="sendGroupBtn" style="visibility: hidden;"
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


	function enterkeyGroup() {
	    if (window.event.keyCode == 13) {
	         // 엔터키가 눌렸을 때 실행할 내용
	         if( $("#sendGroupBtn").css('visibility') == 'visible' ){
		         sendGroupMessage();
	         } else {
	        	 return;
	         }
	    }
	}

	// 웹 소켓 변수
	var wsocket_Group = null;
	
	// 메세지를 보내고자 하는 대상을 저장하고 있는 전역변수
	var sender_Group = "${login_member.member_id}";
	
	$(document).ready(function() {
		$('#connGroupBtn').click(function() { sockGroupConnect(); });
		$('#sendGroupBtn').click(function() { sendGroupMessage(); });
		$('#closeGroupBtn').click(function() { sockGroupClose(); });
	});
	
	function sockGroupConnect() {
		var nickname_Group = $("#nicknameGroup").val().trim();
		if( wsocket_Group != null ){
			alert("웹소켓이 not null입니다. 이미 연결되었습니다.");
			return;
		};
		if( nickname_Group.length == 0 ) {
			alert("로그인이 필요합니다.\n");	
			return;
		};
		wsocket_Group = 
			new WebSocket("ws://localhost:8080/webapp/chat_group");
		wsocket_Group.onmessage = onGroupMessage;
		wsocket_Group.onclose = onGroupClose;

		$("#sendGroupBtn").css('visibility', 'visible');
		$("#connGroupBtn").css('visibility', 'hidden');
		$("#closeGroupBtn").css('visibility', 'visible');
		var message_Group = $("#chatArea_Group").html("서버와 연결되었습니다.\n");	
		$("#chatArea_Group").scrollTop($(document).height());
	}

	
	function sockGroupClose() {
		if( wsocket_Group == null )
			return;
		$("#sendGroupBtn").css('visibility', 'hidden');
		$("#connGroupBtn").css('visibility', 'visible');
		$("#closeGroupBtn").css('visibility', 'hidden');
		wsocket_Group.close();
		wsocket_Group = null;
		var message_Group = $("#chatArea_Group").html("연결이 해제되었습니다.\n");	
		$("#chatArea_Group").scrollTop($(document).height());

	}
	
	function sendGroupMessage() {
		if( wsocket_Group == null ) {
			alert("웹 소켓이 연결되지 않았습니다.\n")
			return;
		}
		console.log($("#messageGroup").val());
		
		if( $("#messageGroup").val() == '/1' || $("#messageGroup").val() == '/2' || $("#messageGroup").val() == '/3' ||
				$("#messageGroup").val() == '/4' || $("#messageGroup").val() == '/5' || $("#messageGroup").val() == '/6' ||
				$("#messageGroup").val() == '/1\n'|| $("#messageGroup").val() == '/2\n'|| $("#messageGroup").val() == '/3\n'|| 
				$("#messageGroup").val() == '/4\n'|| $("#messageGroup").val() == '/5\n'|| $("#messageGroup").val() == '/6\n') {
			alert("이프문 같다");
			$("#chatArea_Group").html("선택한 관심사 채팅방에 입장합니다.");	
			$("#chatArea_Group").scrollTop($(document).height());
			$("#messageGroup").val('');
			return;
		} else {
			alert("이프문 다르다");
			$("#chatArea_Group").html("관심사에 해당하는 번호만 입력해주세요.\nex)/4");	
			$("#chatArea_Group").scrollTop($(document).height());
			$("#messageGroup").val('');
			return;
		}
		
		var msg_Group = sender_Group + " : " + $("#messageGroup").val() + "\n";
		var viewMsg_Group = $("#chatArea_Group").html();
		wsocket_Group.send(msg_Group);
		viewMsg_Group += msg_Group;
		$("#chatArea_Group").html(viewMsg_Group);	
		$("#chatArea_Group").scrollTop($(document).height());
		$("#messageGroup").val('');
	}
	
	function onGroupMessage(evt) {
		var data_Group = evt.data;
		/*
		if(data_Group == '대화시작'){
			$("#sendGroupBtn").css('visibility', 'visible');
		}
		if(data_Group == '대화종료'){
			$("#sendGroupBtn").css('visibility', 'hidden');
		}
		*/
		var message_Group = $("#chatArea_Group").html()
		message_Group += data_Group + "\n";
		$("#chatArea_Group").html(message_Group);	
		$("#chatArea_Group").scrollTop($(document).height());

	}
		
	function onGroupClose(evt) {
		var message_Group = $("#chatArea_Group").html()
		message_Group += "--연결종료--" + "\n";
		$("#chatArea_Group").html(message_Group);	
		$("#chatArea_Group").scrollTop($(document).height());
	}
</script>
