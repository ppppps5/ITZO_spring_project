<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/board_write.css">

<script>
$(document).ready(function() {

	var fObject=$(".form");

	$(".list_btn").on("click",function(){
	
		fObject.attr("method","get");
		fObject.attr("action","/ex/board/board_list");
		fObject.submit();
		
	})

	$(".write_btn").on("click",function(){
		fObject.attr("method","post");
		fObject.attr("action","/ex/board/board_write");
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
                		<form class="form" method="post" enctype="multipart/form-data">
		               <input type="hidden" name="eseq" value="${userName}">
                   
	                    <p>제목<input type="text" name="bTitle" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 83px;"/></p>
	                    <p>작성자<input type="text" name="bWriter" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 70px;"/></p>
	                    <p>파일첨부<input multiple="multiple" type="file" name="bFile" style="margin-left: 57px;"/>
	                    </p>	 
	                    <textarea class="textarea" name="bContent" style="border:1px solid #ccc; width:1000px; height:500px; margin-top:5px;">&nbsp;</textarea>
	               		
	                    <br><br>
                 	</form>
	                    <button type="submit" class="write_btn">등록</button>
	                    <button type="button" class="list_btn">목록으로</button>
                </div>
            </div>
        </div>
    </div>    
<%@include file="../include/footer.jsp"%>
</body>
</html>