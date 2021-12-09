<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/notice_list.css">
<link rel="stylesheet" href="/ex/resources/css/notice_list2.css">

<script>
	var result = '${msg}';

	if (result == 'success') {
		alert("처리가 완료되었습니다.");
	}
	
	$(document).ready(
			function() {
				$('#search_btn').on(
						"click",
						function(event){
							self.location="board_list"
							+ '${pageMaker.makePage(1)}'
							+ '&searchType='
							+$("select option:selected").val()
							+"&keyword=" + $('#keywordInput').val()
							+"&eseq=${userName}";
			
		});
 		
	  $('.write_btn').on("click",function(event){
			self.location="/ex/board/board_write"+"?eseq=${userName}";
		});
	 });
</script>
</head>
<body>
	<!-- 메뉴 -->
	<%@include file="../include/menu.jsp"%>

	<!-- content -->
	<div id="wrap">
		<div id="contents">
			<div class="notice_top">
				<p>업무 게시판</p>
			</div>
			<div class=notice_middle>
				<select name="searchType" class="search">
					<option value="n"
						<c:out value="${pageMaker.searchType==null?'selected':''}" ></c:out>>----</option>
					<option value="t"
						<c:out value="${pageMaker.searchType eq 't'?'selected':''}" />>제목</option>
					<option value="c"
						<c:out value="${pageMaker.searchType eq 'c'?'selected':'' }"/>>내용</option>
				</select> 
				<input type="text" class="search" name="keyword" id="keywordInput" value="${pageMaker.keyword}">&nbsp;
				<button class="search_btn" id="search_btn">검색</button>
				<button class="write_btn">게시글 작성</button>
			</div>
			<hr class="line">
			
			<!-- 테이블 -->
			<div class="w3-container">
			  <table class="w3-table">
			    <tr>
			      <th>글번호</th>
			      <th>작성자</th>
			      <th>제목</th>
			      <th>작성일자</th>
			    </tr>
			    <c:forEach var="dto" items="${listSearchCriteria}">
			    <tr>
			      <td>${dto.bseq}</td>
			      <td>${dto.bWriter}</td>
			      <td>
			      	<a href="/ex/board/board_content?bseq=${dto.bseq}&eseq=${userName}"><div class="align2">${dto.bTitle}</div></a>
				  </td>
			      <td><fmt:formatDate value="${dto.indate}" pattern="yyyy-MM-dd" /></td>
			    </tr>
			    </c:forEach>
			  </table>
			</div>
			<hr class="line">
			
			<!-- 페이지 -->
			<div class="pagination">
				<c:if test="${pageMaker.prev }">
					<a href="board_list${pageMaker.makeSearch(pageMaker.startPage-1)}&eseq=${userName}">&lt;</a>
				</c:if>

				<c:forEach begin="${pageMaker.startPage }" 
				end="${ pageMaker.endPage}"	var="idx">
					<c:choose>
						<c:when test="${idx eq pageMaker.page }">
							<a href='board_list${pageMaker.makeSearch(idx)}&eseq=${userName}'
							<c:out value="${pageMaker.page==idx?' class=active ':'' }"/>>
							 ${idx}</a>
						</c:when>
						<c:otherwise>
							<a href='board_list${pageMaker.makeSearch(idx)}&eseq=${userName}'> 
							${idx}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next }">
					<a href="board_list${pageMaker.makeSearch(pageMaker.endPage+1)}&eseq=${userName}">&gt;</a>
				</c:if>
			</div>
	
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>