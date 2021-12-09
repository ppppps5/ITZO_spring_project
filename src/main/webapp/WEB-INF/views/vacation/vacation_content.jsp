<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@include file="../include/header.jsp"%>
	
<link rel="stylesheet" href="/ex/resources/css/vacation_content2.css">

<script>
    $(document).ready(function(){
            $(".star").click(function(){
                if($(this).attr("src")==="/ex/resources/image/star.png"){
                    $(this).attr("src","/ex/resources/image/star_2.png");
                }else{
                    $(this).attr("src","/ex/resources/image/star.png");
                }
            });

        });

    $(document).ready(function(){
    	
	uploadListAll();

	var fObjsct=$(".form");
		
		$(".delete_btn").on("click",function(){
			fObjsct.attr("method", "post");
			fObjsct.attr("action","/ex/vacation/vacation_delete");
			fObjsct.submit();
		});
		
		var fObjsct=$(".form");
		
		$(".update_btn").on("click",function(){
			fObjsct.attr("method", "get");
			fObjsct.attr("action","/ex/vacation/vacation_update");
			fObjsct.submit();
		});
	});    
	</script>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>

<script>
	function uploadListAll(){
		
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
							  str ="<div class='p2'>파일첨부&emsp;<a href='/ex/vacation/displayFile?fileName="+getImageLink(data)+"''>"
									  +"<img src='/ex/vacation/displayFile?fileName="+data+"'/>"
									  +"</a><small data-src='"+data+"'></small></div>";
						  }else{
							  str = "<div class='p2'>파일첨부&emsp; <a href='/ex/vacation/displayFile?fileName="+data+"'>" 
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

</head>
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
				<div class="vacation_content">
					<c:forEach var="content" items="${vcontent}">
						<form class="form" method="post">
							<button type="submit" class="update_btn">수정</button>
							<button type="submit" class="delete_btn">삭제</button>
							<p>
							<input type="hidden" name="vseq" value="${content.vseq}">
							<input type="hidden" name="eseq" value="${content.eseq}">
							
							
							<p class="p1">&nbsp;
							<h2>&nbsp;${content.title}</h2>
							</p>
							<p class="p2">
								작성자&emsp; <b>${content.writer}</b><span class="p3"></span>
							<span class="p3"></span>
							<span class="p5">
							</p>
							
							<div class='uploadedList'></div>
							<br>
							<hr>
							<div class="content_detail" style="height: 300px;">
								<br>
								<p class="p3">${content.content}</p>


							</div>
					</c:forEach>

					</form>

				</div>
			</div>
		</div>
	</div>

		<%@include file="../include/footer.jsp"%>
</body>
</html>