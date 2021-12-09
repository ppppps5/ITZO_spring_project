<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/approve_content.css">

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
			fObjsct.attr("action","/ex/approve/approve_delete");
			fObjsct.submit();
		});
		
		var fObjsct=$(".form");
		
		$(".update_btn").on("click",function(){
			fObjsct.attr("method", "get");
			fObjsct.attr("action","/ex/approve/approve_update");
			fObjsct.submit();
		});
	});    
	</script>

<script>
	function uploadListAll(){

		   $.ajax({
			   url:"/ex/approve/allFile",
			   type:"post",
			   data: {"aseq":"${aseq}"},
			   dataType:"text",
			   success:function(result){
				   
				   result=eval(result);
				   /* alert(result); */
				   for(var i=0;i<result.length;i++){
					   var data=result[i];
					   var str ="";
						  
						  if(checkImageType(data)){
							  str ="<div class='p2'>파일첨부&emsp;<a href='/ex/approve/displayFile?fileName="+getImageLink(data)+"''>"
									  +"<img src='/ex/approve/displayFile?fileName="+data+"'/>"
									  +"</a><small data-src='"+data+"'></small></div>";
						  }else{
							  str = "<div class='p2'>파일첨부&emsp;<a href='/ex/approve/displayFile?fileName="+data+"'>" 
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

	<!-- contents -->
	<div id="wrap">
		<div id="contents">
			<div class="approve_top">
				<p>전자결재</p>
				<span class="smenu"> <a
					href="/ex/approve/approve?eseq=${userName}">팀별결재현황</a>&nbsp;|&nbsp;
					<a href="/ex/approve/approve_write?eseq=${userName}">기안문작성</a>&nbsp;|&nbsp;
					<a href="/ex/approve/approve_decide?eseq=${userName}">미결함&nbsp;|&nbsp;
						<a href="/ex/approve/approve_cancle?eseq=${userName}">기결함</a>&nbsp;|&nbsp;
						<a href="/ex/approve/approve_each?eseq=${userName}">개인문서함</a></span>
			</div>
			<div class="approve_bottom">
				<div class="approve_content">
					<c:forEach var="content" items="${acontent}">
						<form class="form" method="post">
							<button type="submit" class="update_btn">수정</button>
							<button type="submit" class="delete_btn">삭제</button>
							<br> <input type="hidden" name="aseq"
								value="${content.aseq}"> <input type="hidden"
								name="eseq" value="${content.eseq}">

							<div class="">
								<br>
								<br>
								<hr>
								<p class="p1">


						<h2 style="display: noen;">${content.title}</h2>
                        <fmt:formatDate value="${content.indate}" pattern="yyyy-MM-dd HH:mm" />

                        <p class="p2">
						 작성자&emsp; <b>${content.writer}</b>
                        <p class="p2">
                           결재자&emsp; <b>${content.approval}</b>
                        </p>
                        <div class='uploadedList'></div>
					<span class="p4">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
										<br>
							
							</div>
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