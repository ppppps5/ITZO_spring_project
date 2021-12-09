<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
   <link rel="stylesheet" href="/ex/resources/css/approve_write.css">

</head>

<script>
$(document).ready(function(){
	var fObjsct=$(".form");
	
	$(".send_btn").on("click",function(){
		fObjsct.attr("method", "post");
		fObjsct.attr("action","/ex/approve/approve_decide?eseq=${userName}");
		fObjsct.submit();
	});
});
</script>

<body>
	<!-- 메뉴 -->
	<%@include file="../include/menu.jsp"%>

	<!-- content -->

   <div id="wrap">
 <div id="contents">
        <div class="approve_top">
            <p>전자결재</p>
             <span class="smenu"> 
              <a href="/ex/approve/approve?eseq=${userName}">팀별결재현황</a>&nbsp;|&nbsp;
                <a href="/ex/approve/approve_write?eseq=${userName}">기안문작성</a>&nbsp;|&nbsp;
                <a href="/ex/approve/approve_decide?eseq=${userName}">미결함&nbsp;|&nbsp;
                <a href="/ex/approve/approve_cancle?eseq=${userName}">기결함</a>&nbsp;|&nbsp;
                <a href="/ex/approve/approve_each?eseq=${userName}">개인문서함</a>
                </span>
        </div>
        <div class="approve_bottom">
            <div class="write">
                <form method="post" enctype="multipart/form-data">
                <p>제목<input name="title" type="text" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 70px;" value="&nbsp;"/></p>
                <p>결재자<input name="approval" type="text" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 55px;" value="&nbsp;"/></p>
                <p>파일첨부<input multiple="multiple" type="file" name="File" style="margin-left: 40px;"/></p>
                <textarea class="textarea" name="content" style="border:1px solid #ccc; width:1000px; height:430px; margin-top:5px;">&nbsp;                
                </textarea>
                <br><br>
                <button class="send_btn">등록</button>
                </form>
            </div>
        </div>

    </div>
</div>    
        
<%@include file="../include/footer.jsp"%>
 	
</body>
</html>