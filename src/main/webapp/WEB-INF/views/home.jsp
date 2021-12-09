<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="./include/header.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">

</head>

<body>
	<!-- 메뉴 -->
	<%@include file="./include/menu.jsp"%>

	<!-- content -->
<div id="wrap">

		<div id="contents">


			<!-- Slideshow container -->
			<div class="slideshow-container">

				<!-- Full-width images with number and caption text -->
				<div class="mySlides fade">
					<div class="numbertext">1 / 3</div>
					<img src="/ex/resources/image/test.jpg" style="width: 100%">
					<div class="text">
						<strong>2021년 하반기 신입사원대상 직무기본교육 실시</strong>
					</div>
				</div>

				<div class="mySlides fade">
					<div class="numbertext">2 / 3</div>
					<img src="/ex/resources/image/test1.jpg" style="width: 100%">
					<div class="text">
						<strong>2021년 하반기 신입사원 워크숍 진행</strong>
					</div>
				</div>

				<div class="mySlides fade">
					<div class="numbertext">3 / 3</div>
					<img src="/ex/resources/image/test.jpg" style="width: 100%">
					<div class="text">
						<strong>2021년 하반기 신입사원대상 직무기본교육 실시</strong>
					</div>
				</div>

				<!-- Next and previous buttons -->
				<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a
					class="next" onclick="plusSlides(1)">&#10095;</a>
			</div>
			<br>

			<!-- The dots/circles -->
			<div style="text-align: center">
				<span class="dot" onclick="currentSlide(1)"></span> <span
					class="dot" onclick="currentSlide(2)"></span> <span class="dot"
					onclick="currentSlide(3)"></span>
			</div>

			<div class="board1">
				<div class="board_wrap">
					<h4>공지사항</h4>
					<hr class="board_hr">
					<c:forEach var="noticelist" items="${noticelist}">	
					<ul>
						<li><img src="/ex/resources/image/board.png" width="5px" height="6px">
							<a href="notice/notice_content?nseq=${noticelist.nseq} ">&nbsp;${noticelist.nTitle}
						</li>
						<hr class="board_hr2">
					</ul>
					</c:forEach>
					<a href="/ex/notice/notice_list" class="more ir_pm" title="더보기">
						<img src="/ex/resources/image/more.png">
					</a>
				</div>
			</div>
			
			<div class="board2">
				<h4>업무게시판</h4>
				<hr class="board_hr">
				<c:forEach var="boardlist" items="${boardlist}">
					<input type="hidden" value="${userName}" name="eseq"/>
					<ul>
						<li>
							<img src="/ex/resources/image/board.png" width="5px" height="6px">
							<a href="board/board_content?bseq=${boardlist.bseq}&eseq=${userName}">&nbsp;${boardlist.bTitle}</a>
							
						</li>
						<hr class="board_hr2">
					</ul>
					</c:forEach>
				<a href="/ex/board/board_list?eseq=${userName}" class="more ir_pm" title="더보기"><img
					src="/ex/resources/image/more.png"></a>
			</div>

		</div>
		<div id="side_right">
			<sec:authorize access="isAuthenticated()">
				<form:form>
					<input type="submit" value="✕" class=logout onclick="return logout(this.form)"/>
				</form:form>
			</sec:authorize>
			<c:forEach var="emp" items="${emp}">
				<div class="profile">
					<p style="font-size: 18px;">
						<img src="${emp.img}" alt="프로필 사진" 
							style="width: 150px; height: 180px; margin-top: 35px;"><br>
						${emp.name} ${emp.position}
					</p>
				</div>
			</c:forEach>

			<div class="attendance">
			<c:if test="${admin eq 'true'}">
				<form method="get">
					<input type="hidden" value="${userName}" name="eseq" /> <input
						type="submit" value="사원등록" class="on"
						onclick="return admin(this.form);" /> 
				</form>
			</c:if>
			<c:if test="${admin eq 'false'}">
				<form method="get">
					<input type="hidden" value="${userName}" name="eseq" /> <input
						type="submit" value="출근" class="on"
						onclick="return come(this.form);" /> 
						<input type="submit" value="퇴근" class="off" onclick="return leave(this.form);" />
				</form>
			</c:if>
			</div>
		</div>

	<div id="side_right2">
		<div class="alertBox">
			<span onclick="this.parentElement.style.display='none'"
				class="boxButton"><strong>✕</strong> </span>
			<p>
				<strong><br />회의시작이 얼마남지 않았습니다!</strong>
			</p>
			<br />
			<p>새로운 프로젝트 기획 회의가 14:00부터 시작합니다.</p>
		</div>
		<div class="alertBox2">
			<span onclick="this.parentElement.style.display='none'"
				class="boxButton"><strong>✕</strong> </span>
			<p>
				<strong><br />내일은 공휴일입니다!</strong>
			</p>
			<br />
			<p>푹 쉬시고 다음주에 기분좋게 출근합시다!</p>
		</div>

		<div id="output"></div>
	</div>
</div>

<%@include file="./include/footer.jsp"%>

<script>
function come(frm) {
	frm.action = '/ex/attcome';
	var del = confirm("출근하시겠습니까?")
	if (del == true) {
		return true;
	} else {
		location.reload();
		return false;
	}
}

function leave(frm) {
	frm.action = '/ex/attleave';
	var del = confirm("퇴근하시겠습니까?")
	if (del == true) {
		return true;
	} else {
		location.reload();
		return false;
	}
}

function logout(frm) {
	frm.action = '/ex/logout';
	var del = confirm("로그아웃하시겠습니까?")
	if (del == true) {
		return true;
	} else {
		location.reload();
		return false;
	}
}

function admin(frm) {
	frm.action = '/ex/admin/empRegistration.do';
	var del = confirm("사원을 등록하시겠습니까?")
	if (del == true) {
		return true;
	} else {
		location.reload();
		return false;
	}
}

/* 슬라이드쇼 */
var slideIndex = 1;
showSlides(slideIndex);

// Next/previous controls
function plusSlides(n) {
	showSlides(slideIndex += n);
}

// Thumbnail image controls
function currentSlide(n) {
	showSlides(slideIndex = n);
}

function showSlides(n) {
	var i;
	var slides = document.getElementsByClassName("mySlides");
	var dots = document.getElementsByClassName("dot");
	if (n > slides.length) {
		slideIndex = 1
	}
	if (n < 1) {
		slideIndex = slides.length
	}
	for (i = 0; i < slides.length; i++) {
		slides[i].style.display = "none";
	}
	for (i = 0; i < dots.length; i++) {
		dots[i].className = dots[i].className.replace(" active", "");
	}
	slides[slideIndex - 1].style.display = "block";
	dots[slideIndex - 1].className += " active";
}

/* 시계 */
$(document).ready(function(){
    $(".menu li").click(function() {
    $(this).find(".hidetab").toggle();
    if ($(this).find(".hidetab").style.display === "block") {
        $(this).find(".hidetab").css("display", "none");
    } else {
        $(this).find(".hidetab").css("display", "block");
    }

});
})

setInterval(function(){
    var date = new Date();
    var format = [
        ("0" + date.getHours()).substr(-2)
      , ("0" + date.getMinutes()).substr(-2)
      , ("0" + date.getSeconds()).substr(-2)
    ].join(":");
    document.getElementById("output").innerHTML = format;
  }, 500);
</script>
</body>
</html>