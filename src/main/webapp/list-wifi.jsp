<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.*, java.io.*"%>
<!DOCTYPE html>
<html>
<%
try {
	//매개변수 포함하여 api 주소 만들기
	String authkey = "7a4c707a5878616938305264466d51";
	String bDate = "20190802";
	String apiType = "TbPublicWifiInfo";
	String address = "http://openapi.seoul.go.kr:8088/" + authkey + "/json/" + apiType + "/1/1";

	//객체 생성 및 url 연결
	URL url = new URL(address);
	URLConnection urlConn = url.openConnection();

	//데이터 받아오기
	InputStreamReader ir = new InputStreamReader(urlConn.getInputStream());
	BufferedReader br = new BufferedReader(ir);

	String line;
	line = br.readLine();

	//출력
	out.print(line);

	br.close();
	ir.close();
} catch (Exception e) {
	e.printStackTrace();
}
%>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<script type="text/javascript">
	var watchId;

	window.onload = function() {
		var startPos;

		watchId = navigator.geolocation.watchPosition(function(pos) {
			var lat = pos.coords.latitude;
			var lon = pos.coords.longitude;

			document.getElementById("currentLat").innerHTML = lat;
			document.getElementById("currentLon").innerHTML = lon;

		});
	}
</script>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="list-wifi.jsp">홈</a>
		<form action="">
			<a href="history.jsp">위치 히스토리 목록</a>
		</form>
		<form action="load-wifi.jsp" method="post">
		<input type="hidden" name ="command" value="wifidb">
			<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
		</form>
	</div>
	<form action="http://localhost:8080/wifi/list-wifi.jsp">
		<label for="">LAT: </label><input type="text" value="0.0"> <label
			for="">LON: </label><input type="text" value="0.0">
		<button>내 위치 가져오기</button>
		<button>근처 WIFI 정보 보기</button>
	</form>

	<span id="currentLat">?</span>
	<span id="currentLon">?</span>

	<table border=1px cellspacing=0pxtext-align:centerwidth:100%>
		<tr>
			<th>거리(Km)</th>
			<th>관리번호</th>
			<th>자치구</th>
			<th>와이파이명</th>
			<th>도로명주소</th>
			<th>상세주소</th>
			<th>설치위치(층)</th>
			<th>설치유형</th>
			<th>설치기관</th>
			<th>서비스구분</th>
			<th>망종류</th>
			<th>설치년도</th>
			<th>실내외구분</th>
			<th>Wifi접속환경</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>작업일자</th>
		</tr>
		</table>
</body>
</html>