<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
   <link rel="stylesheet" href="/ex/resources/css/vacation_write.css">
	
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/ex/resources/js/main.js"></script>
</head>

<script>
$(document).ready(function() {
	
	var fObject=$(".form");
	
	$(".list_btn").on("click",function(){

		fObject.attr("method","get");
		fObject.attr("action","/ex/vacation/vacation_decide");
		fObject.submit();
		
	})
	
	$(".save_btn").on("click",function(){
		fObject.attr("method","post");
		fObject.attr("action","/ex/vacation/vacation_update");
		fObject.submit();
	})
	
});
</script>

<script>
	function uploadListAll(){
		/* alert("${mseq}"); */
		/*  var formData = new FormData();
		formData.append("mseq","${MailDto.mseq}")
			alert("ehodfkdkfajsdlfj");
			alert(formData.append("mseq",6));  */
		/* $(".uploadedList").on("click", "small", function(event){
			
			 var that = $(this);
		 */
		 alert("${vseq}");
		   $.ajax({
			   url:"/ex/vacation/allFile",
			   type:"post",
			   data: {"vseq":"${vseq}"},
			   dataType:"text",
			   success:function(result){
				   
				   result=eval(result);
				   /* alert(result); */
				   for(var i=0;i<result.length;i++){
					   var data=result[i];
					   var str ="";
						  
						  if(checkImageType(data)){
							  str ="<div><a href='/ex/vacation/displayFile?fileName="+getImageLink(data)+"''>"
									  +"<img src='/ex/vacation/displayFile?fileName="+data+"'/>"
									  +"</a><small data-src='"+data+"'>X</small></div>";
						  }else{
							  str = "<div><a href='/ex/vacation/displayFile?fileName="+data+"'>" 
									  + getOriginalName(data)+"</a>"
									  +"<small data-src='"+data+"'>X</small></div></div>";
						  }
						  
						  $(".uploadedList").append(str);
				  } 
			   }
		   });
		/* }); */
	}	
	
	$(".uploadedList").on("click", "small", function(event){//delete
		
		 var that = $(this);
	
	   $.ajax({
		   url:"/ex/deleteFile",
		   type:"post",
		   data: {fileName:$(this).attr("data-src")},
		   dataType:"text",
		   success:function(result){
			   if(result == 'deleted'){
				   that.parent("div").remove();
			   }
		   }
	   });
});

function getOriginalName(fileName){	

	if(checkImageType(fileName)){
		return;
	}
	
	var idx = fileName.indexOf("_") + 1 ;
	return fileName.substr(idx);
	
}


function getImageLink(fileName){
	
	if(!checkImageType(fileName)){
		return;
	}

	
	return fileName.replace("s_","");
	
}

function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);
	
}
</script>		
<body>
	<!-- 메뉴 -->
	<%@include file="../include/menu.jsp"%>

	<!-- content -->
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
                <form class=form method="post" enctype="multipart/form-data">
                 <c:forEach var="update" items="${vcontent}">
                <input type='hidden' name='vseq' value="${update.vseq}">
				<input type="hidden" name="eseq" value="${update.eseq}">                                
 			<p>휴가종류선택&nbsp;
                <select name="vkseq" style="border:1px solid #ccc; width:150px; height:30px; margin-bottom:5px;" value="&nbsp;"/>
                <option value="1">반차</option>
                <option value="2">월차</option>
                <option value="3">병가</option>
                <option value="4">리프레쉬휴가</option>
                <option value="5">하계휴가</option>
                <option value="6">경조사휴가</option>
                </select>
                </p>
                <p>제목<input type="text" name="title" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 70px;" value="${update.title}"/></p>
				<p>작성자<input name="writer" type="text" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 55px;" value="${update.writer}"/></p>
                <p>파일첨부<input type="file" name="File" style="margin-left: 40px;"/></p>
                <textarea class="textarea" name="content" style="border:1px solid #ccc; width:1000px; height:450px; margin-top:5px;">&nbsp;${update.content}
                </textarea>
                <br><br>
                 <button type="button" class="save_btn">저장</button>
                    <button type="button" class="list_btn">목록가기</button>
                
           		</form>
           		</c:forEach>
            </div>
        </div>
        
        
    </div>
</div>    

 <%@include file="../include/footer.jsp"%>

</body>
</html>