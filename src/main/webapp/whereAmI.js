/**
 * 현재 위도 경도 구하기 
 */

 function whereAmI(){
	 if(navigator.geolocation) {
		 navigator.geolocation.getCurrentPosition(success, error);
		 } else {
			 alert("위치를 얻을 수 없습니다.");
		 }
 }
		
function success(position) {
	var lat = position.coords.latitude;
	var lnt = position.coords.longitude;
	
	document.getElementById('targetLat').value = lat;
	document.getElementById('targetLnt').value = lnt;
}

function error() {
	switch(error.code) {
		case error.PERMISSION_DENIED:
			alert("사용자 거부");
		case error.POSITION_UNAVAILABLE:
			alert("지리정보 없음");
		case error.TIMEOUT:
			alert("시간 초과");
		case error.UNKNOWN_ERROR:
			alert("알 수 없은 에러");
	}
}