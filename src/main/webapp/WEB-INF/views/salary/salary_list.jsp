<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/salary_list.css">

<script>
    
    var result ='${msg}';
	if (result=='delete'){
		alert("삭제 완료 되었습니다.");
	}
    
</script>
</head>

<body>
    <!-- 메뉴 -->
    <%@include file="../include/menu.jsp"%>

    <!-- contents -->
    <div id="wrap">
        <div id="contents">
            <div class="salary_top">
                <p>내 급여 확인</p>
            </div>
            <div class="salary_middle">
                <input type="text" placeholder="해당 월 검색" class="search">&nbsp;<input type="submit" class="search2" value="검색">
            </div>
            <hr class="line">
            <div class="salary_bottom">
                <div class="salary_list">
                	<form class="form" method="post">
	                    <c:forEach var="list" items="${salarylist}" >
		                    <div>
		                        <span class="c0"><img src="/ex/resources/image/logo.png" style="width: 40px; height: 20px;"></span>
		                        <input type="hidden" name="sseq" value="${list.sseq}">
 		                        <span class="c2" id="name"><div class="align1">${list.name}</div></span>
		                        <span class="c3"><a href="salary_content?sseq=${list.sseq}&eseq=${userName}"><div class="align2"> <b>${list.stitle}</b></div></a></span>
		                        <span class="c4"><div class="align3" > <fmt:formatDate value="${list.indate}" pattern="yyyy-MM-dd" /></div></span>
		                        <hr class="line2">
		                    </div>
		                 </c:forEach>
					</form>
                    
                </div>
                <hr class="line3">
                <div class="salary_page">
                   <div class="pagination">
				<c:if test="${pageMaker.prev }">
					<a href="salary_list${pageMaker.makeSearch(pageMaker.startPage-1)}&eseq=${userName}">&lt;</a>
				</c:if>

				<c:forEach begin="${pageMaker.startPage }" 
				end="${ pageMaker.endPage}"	var="idx">
					<c:choose>
						<c:when test="${idx eq pageMaker.page }">
							<a href='salary_list${pageMaker.makeSearch(idx)}&eseq=${userName}'
							<c:out value="${pageMaker.page==idx?' class=active ':'' }"/>>
							 ${idx}</a>
						</c:when>
						<c:otherwise>
							<a href='salary_list${pageMaker.makeSearch(idx)}&eseq=${userName}'> 
							${idx}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next }">
					<a href="salary_list${pageMaker.makeSearch(pageMaker.endPage+1)}&eseq=${userName}">&gt;</a>
				</c:if>
			</div>
                </div>
            </div>
        </div>
    </div>    

    <%@include file="../include/footer.jsp"%>

</body>
</html>