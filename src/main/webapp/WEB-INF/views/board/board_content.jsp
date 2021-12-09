<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/board_content.css">

<script>
var bseq="${dto.bseq}";
var page=1;
$(document).ready(function() {
	//리플작업시작
	//read페이지는 특정글을 읽을때 들어와서 bno정보를 가지고 있다.
	//처음 read페이지에 들어 왔을때 page는 1이 된다.
	//bno page

	getPageList(page);
	$("#replyModBtn").on("click",function(){
		  var rseq = $(".modal-title").html();
		  var replytext = $("#replytext").val();
		  $.ajax({
				type:'put',
				url:'/ex/replies/'+rseq,
				headers: { 
				      "Content-Type": "application/json"},
				data:JSON.stringify({replytext:replytext}), 
				dataType:'text', 
				success:function(result){
					console.log("result: " + result);
					if(result == 'SUCCESS'){
						alert("수정 되었습니다.");
						 $("#modDiv").hide("slow");
						 getPageList(page);
					}
			}});
	});
	
	$("#closeBtn").on("click", function() {
		$("#modDiv").hide("slow");
	});
	
	$("#replyDelBtn").on("click", function() {
		var rseq = $(".modal-title").html();
		$.ajax({
			type : 'delete',
			url : '/ex/replies/' + rseq,
			headers : {
				"Content-Type" : "application/json"
			},
			dataType : 'text',
			success : function(result) {
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					alert("삭제 되었습니다.");
					$("#modDiv").hide("slow");
					getPageList(page);
				}
			}
		});
	});
	
	$("#replies").on("click",".replyLi button",function(){
		var reply=$(this).parent();
		var rseq=reply.attr("data-rseq");
		var replytext=reply.text();
		$(".modal-title").html(rseq);
		$("#replytext").val(replytext);
		$("#modDiv").show("slow");
	})
	
	$(".pagination").on("click","a",function(event){
		event.preventDefault();
		page=$(this).attr("href");
		getPageList(page);
	})
	
	$("#replyAddBtn").on("click", function() {
		var replyer = $("#newReplyWriter").val();
		var replytext = $("#newReplyText").val();
		$.ajax({
			type : 'post',
			url : '/ex/replies',
			headers : {
				"Content-Type" : "application/json"
			},
			dataType : 'text',
			data : JSON.stringify({
				bseq : bseq,
				replyer : replyer,
				replytext : replytext
			}),
			success : function(result) {
				if (result == 'SUCCESS') {
					alert("등록 되었습니다.");
					//getAllList();
					getPageList(page);
				}
			}
		});
	});

	//리플작업종료
	var fObject = $(".form");

	$(".list_btn").on("click", function() {

		fObject.attr("method", "get");
		fObject.attr("action", "/ex/board/board_list");
		fObject.submit();

	})
	
	$(".delete_btn").on("click", function() {

		fObject.attr("action", "/ex/board/board_delete");
		fObject.submit();
	})
	
	$(".update_btn").on("click", function() {
		fObject.attr("method", "get");
		fObject.attr("action", "/ex/board/board_update");
		fObject.submit();
	})
	
});

	function getPageList(page) {
		$.getJSON("/ex/replies/" + bseq + "/" + page, function(data) {
			console.log(data.list.length);
			var str = "";
			$(data.list).each(
					function() {
						str += "<li data-rseq='"+this.rseq+"' class='replyLi'>"
								+ this.rseq + ":" + this.replytext
								+ " <button>수정</button></li>"
					})
			$("#replies").html(str);
			printPaging(data.pageMaker)
		})
	}

	function printPaging(pageMaker) {

		var str = "";

		if (pageMaker.prev) {
			str += "<a href='" + (pageMaker.startPage - 1) + "'> << </a>";
		}

		for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.page == i ? 'class=active' : '';
			str += "<a  "+strClass+" href='"+i+"'>" + i + "</a>";
		}

		if (pageMaker.next) {
			str += "<a href='" + (pageMaker.endPage + 1) + "'> >> </a>";
		}
		$('.pagination').html(str);

	}
</script>
</head>
<body>
	<!-- 메뉴 -->
    <%@include file="../include/menu.jsp"%>

	<!-- contents -->
	<div id="wrap">
		<div id="contents">
			<div class="board_top">
				<p>업무 게시판</p>
			</div>
			<div class="board_bottom">
				<div class="btn">
					<button type="button" class="delete_btn">삭제</button>
					<button type="button" class="update_btn">수정</button>
					<button type="button" class="list_btn">목록으로</button>
				</div>

				<div class="board_content">
					<form class="form" method="post">
						<%-- <input type='hidden' name='page' value="${pageMaker.page}">
						<input type='hidden' name='perPageNum' value="${pageMaker.perPageNum}"> 
						<input type='hidden' name='searchType' value="${pageMaker.searchType}"> 
						<input type='hidden' name='keyword' value="${pageMaker.keyword}"> --%>

					<c:forEach items="${content}" var="dto">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="bseq" value="${dto.bseq}">
						<input type="hidden" name="eseq" value="${userName}">
						<input type="hidden" name="bTitle" value="${dto.bTitle}">
						<input type="hidden" name="bContent" value="${dto.bContent}">
					<p class="p1">&nbsp;
					<h1>&nbsp;${dto.bTitle}</h1>

					<p class="p2">
					<b>${dto.bWriter}</b>
					<span class="p3"></span>
					<span class="p5">
					<fmt:formatDate	value="${dto.indate}" pattern="yyyy-MM-dd" /></span>
					</p>
					<br>
					<hr>
					<div class="content_detail" style="height: 400px;">
						<br>
						<p class="p3">${dto.bContent}</p>
					</div>
					</c:forEach>
					</form>

					<div id='modDiv' style="display: none;">
						<div class='modal-title'></div>
						<div>
							<input type='text' id='replytext'>
						</div>
						<div>
							<button type="button" id="replyModBtn">수정하기</button>
							<button type="button" id="replyDelBtn">삭제</button>
							<button type="button" id='closeBtn'>돌아가기</button>
						</div>
					</div>
					<br>
					<hr>
					<ul id="replies">
					
					</ul>
					<h3>댓글 </h3>
 					<div class="comments">
						<hr>
						<label for="replyer" class="blind">작성자</label> <input type="text"
							id="newReplyWriter" name="replyer"
							style="margin-top: 10px; margin-bottom: 10px; width: 90px; height: 20px;" >
						<textarea class="textarea" id="newReplyText"
							style="border: 1px solid #ccc; width: 900px; height: 49px; margin-top: 3px; margin-bottom: 5px;">&nbsp;</textarea>
						<button type="button" class="reply_btn" id="replyAddBtn">댓글 작성</button>
					</div>
					<!-- <div>
						<div>
							작성자 <input type='text' name='replyer' id='newReplyWriter'>
						</div>
						<div>
							댓글내용 <input type='text' name='replytext' id='newReplyText'>
						</div>
						<button id="replyAddBtn">댓글 작성</button>
					</div> -->
					
				</div>
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>