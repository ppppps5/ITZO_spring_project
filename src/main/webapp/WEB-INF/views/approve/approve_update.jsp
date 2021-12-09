<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@include file="../include/header.jsp"%>

 <link rel="stylesheet" href="/ex/resources/css/approve_write.css">

<script>
$(document).ready(function() {
	
	var fObject=$(".form");
	
	$(".list_btn").on("click",function(){

		fObject.attr("method","get");
		fObject.attr("action","/ex/approve/approve_decide");
		fObject.submit();
		
	})
	
	$(".save_btn").on("click",function(){
		fObject.attr("method","post");
		fObject.attr("action","/ex/approve/approve_update");
		fObject.submit();
	})
	
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
                <form class="form" method="post">
                <c:forEach var="update" items="${decideupdate}">
                <input type='hidden' name='aseq' value="${update.aseq}">
				<input type="hidden" name="eseq" value="${update.eseq}">                    
 			      <p>제목<input type="text" name="title" value="${update.title}" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 84px;"/></p>
                    <p>작성자<input type="text" name="writer" value="${update.writer}" readonly="readonly" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 70px;"/></p>
                    <p>결재자<input type="text" name="approval" value="${update.approval}" readonly="readonly" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 70px;"/></p>                   
                    <p>파일첨부<input type="file" style="margin-left: 55px;"/></p>
                    <textarea class="textarea" name="content" style="border:1px solid #ccc; width:1000px; height:430px; margin-top:5px;">&nbsp;${update.content}</textarea>
                </c:forEach> 
                </form>   
                    <br><br>
                    <button type="button" class="save_btn">저장</button>
                    <button type="button" class="list_btn">목록가기</button>
                </div>
            </div>
        </div>
    </div>    

 	<%@include file="../include/footer.jsp"%>

</body>
</html>