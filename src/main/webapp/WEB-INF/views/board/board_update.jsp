<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/board_update.css">

<script>
$(document).ready(function() {
	
	var fObject=$(".form");
	
	$(".list_btn").on("click",function(){

		fObject.attr("method","get");
		fObject.attr("action","/ex/board/board_list");
		fObject.submit();
		
	})
	
	$(".save_btn").on("click",function(){
		fObject.attr("method","post");
		fObject.attr("action","/ex/board/board_update");
		fObject.submit();								
	})
	
});
</script>
</head>
<body>
    <!-- 메뉴 -->
    <%@include file="../include/menu.jsp"%>

    <!-- contents -->
    <div id="wrap">
        <div id="contents">
            <div class="board_top">
                <p>업무 게시글</p>
            </div>
            <div class="board_bottom">
                <div class="write">
                	<form class="form" method="post">
                		<c:forEach items="${content}" var="dto">
                		<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		            <input type='hidden' name='bseq' value="${dto.bseq}"> --%> 
		            <input type='hidden' name='eseq' value="${userName}"> 
					<%-- <input type='hidden' name='page' value="${pageMaker.page}"> 
					<input type='hidden' name='perPageNum' value="${pageMaker.perPageNum}">
					<input type='hidden' name='searchType' value="${pageMaker.searchType}">
					<input type='hidden' name='keyword' value="${pageMaker.keyword}"> --%>
                    <p>제목<input type="text" name="bTitle" 
                    style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 83px;"
                    value="${dto.bTitle}"/></p>
                    <p>작성자<input type="text" name="bWriter" 
                    style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 70px;"
                    value="${dto.bWriter}"/></p>
                    <p>파일첨부<input type="file" name="bFile" 
                    style="margin-left: 55px;" value="bFile"/></p>
                    <textarea class="textarea" name="bContent" 
                    style="border:1px solid #ccc; width:1000px; height:500px; margin-top:5px;">&nbsp;${dto.bContent }</textarea>
                    <br><br>
                	</c:forEach>
                </form>
                    <button type="button" class="save_btn">저장하기</button>
                    <button type="button" class="list_btn">목록으로</button>
                </div>
            </div>
        </div>
    </div>    
<%@include file="../include/footer.jsp"%>
</body>
</html>