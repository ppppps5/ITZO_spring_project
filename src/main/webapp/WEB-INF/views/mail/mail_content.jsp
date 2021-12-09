<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/mail_content.css">

<script>
    $(document).ready(function(){
    		uploadListAll();
            $(".star").click(function(){
                if($(this).attr("src")==="/ex/resources/image/star.png"){
                    $(this).attr("src","/ex/resources/image/star_2.png");
                }else{
                    $(this).attr("src","/ex/resources/image/star.png");
                }
            });

        });

    
    $(document).ready(function(){
		var fObjsct=$(".form");
		
		$(".delete_btn").on("click",function(){
			fObjsct.attr("action","/ex/mail/receivedelete");
			fObjsct.submit();
		});
		
		var fObjsct=$(".form");
		
		$(".reply_btn").on("click",function(){
			fObjsct.attr("method","get");
			fObjsct.attr("action","/ex/mail/mail_reply?trans=${list.trans}&mseq=${list.mseq}&eseq=${list.eseq}");
			fObjsct.submit();
		});
	});
																																															// });
</script>
</head>

<body>
    <!-- 메뉴 -->
    <%@include file="../include/menu.jsp"%>

    <!-- contents -->
    <div id="wrap">
        
        <div id="contents">
            <div class="mail_top">
                <p>메일</p>
				<span class="smenu"> <a href="/ex/mail/mail_write?eseq=${userName}">메일쓰기</a>&nbsp;
					|&nbsp; <a href="/ex/mail/mail_receivelist?eseq=${userName}">받은메일</a>&nbsp;|&nbsp; <a
					href="/ex/mail/mail_sendlist?eseq=${userName}">보낸메일</a>&nbsp;|&nbsp; <a
					href="/ex/mail/mail_important?eseq=${userName}">중요메일</a>&nbsp;|&nbsp; <a
					href="/ex/mail/mail_deletelist?eseq=${userName}">휴지통</a>
				</span>
            </div>
            <div class="mail_bottom">
                <div class="btn"><button class="delete_btn"> 메일 삭제</button>
	                <c:forEach var="list" items="${content}">
	                <c:set var="trans" value="${list.trans}"/>
	                <c:choose>
						<c:when test="${trans eq '수신'}">
	                		<span><button class="reply_btn">답장</button></span>
                       	</c:when>
						<c:otherwise>
							
						</c:otherwise>
                    </c:choose>
	                </c:forEach>
                </div>
                
                <div class="mail_content">
                	<form class="form" method="post">
	                	<c:forEach var="list" items="${content}" >
	                		<input type="hidden" name="trans" value="${list.trans}">
	                		<input type="hidden" name="mseq" value="${list.mseq}">
	                		<input type="hidden" name="eseq" value="${list.eseq}">
	                		
		                    <hr>
		                    <p class="p1"><img src="/ex/resources/image/star.png" class="star" width="20px" height="20px"/>&nbsp;<b>${list.title}</b> <span class="p4"><fmt:formatDate value="${list.indate}" pattern="yyyy-MM-dd HH:mm" /></span></p>
		                    <p class="p2">보낸사람&emsp; ${list.sname} &lt; ${list.sender} &gt;</p>
		                    <p class="p2">받는사람&emsp; ${list.rname} &lt; ${list.receiver} &gt;</p>
		                    
		                    <div class='uploadedList'></div>
		                    
		                    <br>
		                    <hr>
		                    <div class="content_detail">
		                        <br>
		                        <p class="p3">${list.content}</p>
		                    </div>
		                    <hr>
	                    </c:forEach>
	            	</form>        
                </div>
            </div>

        </div>
    </div>    
            
<%@include file="../include/footer.jsp"%>

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
		   $.ajax({
			   url:"/ex/mail/allFile",
			   type:"post",
			   data: {"mseq":"${mseq}"},
			   dataType:"text",
			   success:function(result){
				   
				   result=eval(result);
				   /* alert(result); */
				   for(var i=0;i<result.length;i++){
					   var data=result[i];
					   var str ="";
						  
						  if(checkImageType(data)){
							  str ="<div><a href='/ex/mail/displayFile?fileName="+getImageLink(data)+"''>"
									  +"<img src='/ex/mail/displayFile?fileName="+data+"'/>"
									  +"</a><small data-src='"+data+"'></small></div>";
						  }else{
							  str = "<div><a href='/ex/mail/displayFile?fileName="+data+"'>" 
									  + getOriginalName(data)+"</a>"
									  +"<small data-src='"+data+"'></small></div></div>";
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

</body>
</html>