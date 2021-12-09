<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/vacation_write.css">

<script>
$(document).ready(function(){
	var fObjsct=$(".form");
	
	$(".send_btn").on("click",function(){
		fObjsct.attr("method", "post");
		fObjsct.attr("action","/ex/vacation/vacation_decide?eseq=${userName}");
		fObjsct.submit();
	});
});
</script>


<body>
    <!-- 메뉴 -->
    <%@include file="../include/menu.jsp"%>

    <!-- contents -->

   <div id="wrap">
    <div id="contents">
        <div class="vacation_top">
            <p>휴가신청</p>
            <span class="smenu"> 
                  <a href="/ex/vacation/vacation_check?eseq=${userName}">휴가신청</a>&nbsp;|&nbsp;
         		 <a href="/ex/vacation/vacation_write?eseq=${userName}">휴가신청서작성</a>       
                </span>
        </div>
        <div class="vacation_bottom">
            <div class="write">
                <form method="post" enctype="multipart/form-data">
 			<p>휴가종류선택
                <select name="vkseq" style="border:1px solid #ccc; width:150px; height:30px; margin-bottom:5px; margin-left: 6px;" value="&nbsp;"/>
                <option value="1">반차</option>
                <option value="2">월차</option>
                <option value="3">병가</option>
                <option value="4">리프레쉬휴가</option>
                <option value="5">하계휴가</option>
                <option value="6">경조사휴가</option>
                </select>
                </p>
                <p>제목<input type="text" name="title" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 70px;" value=""/></p>
				<p>결재자<input name="approval" type="text" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 55px;" value="&nbsp;"/></p>
                <p>파일첨부<input type="file" name="File" style="margin-left: 40px;"/></p>
                <textarea class="textarea" name="content" style="border:1px solid #ccc; width:1000px; height:450px; margin-top:5px;">&nbsp;
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