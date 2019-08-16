<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="chattingGroupModal_1.jsp" flush="false"></jsp:include>
<%--
<jsp:include page="chattingGroupModal_2.jsp" flush="false"></jsp:include>
<jsp:include page="chattingGroupModal_3.jsp" flush="false"></jsp:include>
<jsp:include page="chattingGroupModal_4.jsp" flush="false"></jsp:include>
<jsp:include page="chattingGroupModal_5.jsp" flush="false"></jsp:include>
<jsp:include page="chattingGroupModal_6.jsp" flush="false"></jsp:include>
--%>

<div class="modal fade" id="chattingGroupSelectModal" tabindex="-1" role="dialog"
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
							<p>원하는 그룹을 선택하세요.</p>
								<select style="visibility: visible;" id="selectedGroup" name="selectedGroup" onclick="selectGroup();"
								class="btn btn-outline-info waves-effect ml-auto">
									<option value="1">관심사 1</option>
									<option value="2">관심사 2</option>
									<option value="3">관심사 3</option>
									<option value="4">관심사 4</option>
									<option value="5">관심사 5</option>
									<option value="6">관심사 6</option>
								</select>
								<a href="" id="enterGroupModal" data-toggle="modal" onclick="enterGroup();" data-target="#chattingGroupModal_1" style="visibility: visible;"
								class="btn btn-outline-info waves-effect ml-auto">입장하기</a>
							</div>

						</div>
						<!--Footer-->
						<div class="modal-footer">
							<div class="options text-center text-md-right mt-1"
								style="width: 90%;">
								<!-- 푸터의 내용 -->
							</div>
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
var selected = null;
	function enterGroup() {
		$('#chattingGroupSelectModal').modal('toggle');
	}
	function selectGroup() {
		selected = $("#selectedGroup").val();
		$("#enterGroupModal").attr("data-target", "#chattingGroupModal_" + selected);
	}
</script>

