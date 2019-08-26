<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    new daum.Postcode({
        oncomplete: function(data) {
        	
            if(data.userSelectedType=="R"){
                // userSelectedType : 검색 결과에서 사용자가 선택한 주소의 타입
                // return type : R - roadAddress, J : jibunAddress
                window.pjt_android.setAddress(data.zonecode, data.roadAddress);
            }
            else{
                window.pjt_android.setAddress(data.zonecode, data.buildingName);
            }       
        }
    }).open();
</script>