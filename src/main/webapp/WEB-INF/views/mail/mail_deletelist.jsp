<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/mail_sendlist.css">


<script>
 //별//
var mseq="${MailDto.mseq}";//전역변수로
var imp="${MailDto.imp}";
$(document).ready(function() {
    $(".star").on("click",function(){
		var mseq = $(this).parent().siblings(".mseq").val();
		var imp = $(this).parent().siblings(".imp").val();
		
		$.ajax({
			type : 'put',
			url : '/ex/imp/'+mseq+'/'+imp,
			headers : {
				"Content-Type" : "application/json"
			},
			data : JSON.stringify({imp:imp}),
			dataType : 'text',
			success : function(result){
				console.log("result: "+result);
				
				}
			});
		if($(this).attr("src")==="/ex/resources/image/star.png"){
               $(this).attr("src","/ex/resources/image/star_2.png");
               
           }else{
               $(this).attr("src","/ex/resources/image/star.png");
              
           }
		});
});

$(document).ready(function() {
    $(".add_address").on("click",function(){
		var sender = $(this).parent().siblings(".sender").val();
		var eseq = $(this).parent().siblings(".eseq").val();
		alert("주소록 추가 완료");

		
		$.ajax({
			type : 'post',
			url : '/ex/imp/'+eseq+'/'+sender,
			headers : {
				"Content-Type" : "application/json"
			},
			data : JSON.stringify({sender:sender}),
			dataType : 'text',
			success : function(result){
				console.log("result: "+result);
				
				}
			});
		});
    $('.mail_write').on("click",function(event){
 			
 			self.location="/ex/mail/mail_write"+"?sender="+$(".sender").val()+"&eseq="+${userName};
 			
 		});
});
var result ='${msg}';
if (result=='add'){
	alert("주소록에 추가되었습니다.");
}

       $(document).ready(function(){
           $(".dropdown").click(function(){
               if ($(this).find(".dropdown_content").css("display")==="block"){
                   $(this).find(".dropdown_content").css("display","none");
               } else{
                   $(this).find(".dropdown_content").css("display","block");
               }
           });
       });
       
       $(document).ready(function(){
   		var fObjsct=$(".form");
   		
   		$(".delete_btn").on("click",function(){
   			fObjsct.attr("action","/ex/mail/realdelete");
   			fObjsct.submit();
   		});
   	});
       
       var result ='${msg}';
   	if (result=='realdelete'){
   		alert("영구 삭제 되었습니다.");
   	}
       
   	$(document).ready(function(){
     		$('.search2').on("click",function(event){
     			
     			self.location="/ex/mail/mail_deletelist"+'${pageMaker.makePage(1)}'
     			+'&searchType='+$("select option:selected").val()
     			+"&keyword="+$('#keyword').val()
     			+"&eseq=${userName}";
     			
     		});
     		
     	});
   	
</script>
</head>

<body>
    <!-- 메뉴 -->
    <%@include file="../include/menu.jsp"%>

    <!-- contents -->
    <div id="wrap">
      
        <div id="contents">
            <div class="mail_top">
                <p>휴지통</p>
                <span class="smenu"> <a href="/ex/mail/mail_write?eseq=${userName}">메일쓰기</a>&nbsp;
					|&nbsp; <a href="/ex/mail/mail_receivelist?eseq=${userName}">받은메일</a>&nbsp;|&nbsp; <a
					href="/ex/mail/mail_sendlist?eseq=${userName}">보낸메일</a>&nbsp;|&nbsp; <a
					href="/ex/mail/mail_important?eseq=${userName}">중요메일</a>&nbsp;|&nbsp; <a
					href="/ex/mail/mail_deletelist?eseq=${userName}">휴지통</a>
				</span>
            </div>
            <div class="mail_middle">
                <select name="searchType" class="search">
		    		<option value="n" <c:out value="${pageMaker.searchType==null?'selected':''}" ></c:out> >----</option>
		    		<option value="제목" <c:out value="${pageMaker.searchType eq '제목'?'selected':''}" ></c:out> >제목</option>
		    	</select>
				<input type="text" placeholder="메일검색" class="search" id="keyword" name="keyword">&nbsp;<input
					type="submit" class="search2" value="검색">
				<button class="delete_btn">메일 삭제</button>
            </div>
            <hr class="line">
            <div class="mail_bottom">
                <div class="mail_list">
                	<form class="form" method="post">
	                	<c:forEach var="list" items="${deletelist}" >
		                    <div>
		                        <span class="c0"><input type="checkbox" name="mseq" value="${list.mseq}"></span>
	                        	<c:set var="star" value="${list.imp}"/>
	                        	<c:choose>
	                        		<c:when test="${star eq 'y'} ">
	                        			<span><img src="/ex/resources/image/star_2.png" class="star" width="20px" height="20px"/></span>
	                        		</c:when>
									<c:otherwise>
										<span><img src="/ex/resources/image/star.png" class="star" width="20px" height="20px"/></span>
									</c:otherwise>
	                        	</c:choose>
	                        	<input type="hidden" name="mseq" value="${list.mseq}" class="mseq">
                        		<input type="hidden" name="imp" value="${list.imp}" class="imp">
	                        
		                        <!-- <span class="c1"><div class="align1" >103</div></span> --> 
		                        <span class="c2" id="name">
		                            <div class="dropdown">
		                            	<input type="hidden" class="eseq" name="eseq" value="${list.eseq}">
										<input type="hidden" class="sender" name="sender" value="${list.sender}">
		                                <div class="align1">${list.sname} (${list.sender})</div>
		                                <div class="dropdown_content">
		                                    <a class="mail_write">메일쓰기</a> 
											<a class="add_address">주소록추가</a>
		                                </div>
		                            </div>
		                        </span>
		                        <span class="c3"><a href="mail_content?eseq=${userName}&mseq=${list.mseq}"><div class="align2"> ${list.title}</div></a></span>
		                        <span class="c4"><div class="align3" > <fmt:formatDate value="${list.indate}" pattern="yyyy-MM-dd" /></div></span>
		                        <hr class="line2">
		                    </div>
						</c:forEach>
                    </form>
                </div>
                <hr class="line3">
                <div class="mail_page">
<div class="pagination">
				<c:if test="${pageMaker.prev }">
					<a href="mail_deletelist${pageMaker.makeSearch(pageMaker.startPage-1)}&eseq=${userName}">&lt;</a>
				</c:if>

				<c:forEach begin="${pageMaker.startPage }" end="${ pageMaker.endPage}" 
					var="idx">
				
					<c:choose>
						<c:when test="${idx eq pageMaker.page }">
							<a href='mail_deletelist${pageMaker.makeSearch(idx)}&eseq=${userName}'
							<c:out value="${pageMaker.page==idx?' class=active ':'' }"/>>
							 ${idx}</a>
						</c:when>
						<c:otherwise>
							<a href='mail_deletelist${pageMaker.makeSearch(idx)}&eseq=${userName}'> ${idx}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next }">
					<a href="mail_deletelist${pageMaker.makeSearch(pageMaker.endPage+1)}&eseq=${userName}">&gt;</a>
				</c:if>
			</div>
                </div>
            </div>
            


        </div>
    </div>    
            

<%@include file="../include/footer.jsp"%>


</body>
</html>