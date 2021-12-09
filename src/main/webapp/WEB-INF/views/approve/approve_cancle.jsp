<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

    <link rel="stylesheet" href="/ex/resources/css/approve_list.css">

	<script>
    //별//
    var aseq="${ApproveDto.aseq}";//전역변수로
	var myapprove="${ApproveDto.myapprove}";
	$(document).ready(function() {
	    $(".star").on("click",function(){
			var aseq = $(this).parent().siblings(".aseq").val();
			var myapprove = $(this).parent().siblings(".myapprove").val();
			
			$.ajax({
				type : 'put',
				url : '/ex/myapprove/'+aseq+'/'+myapprove,
				headers : {
					"Content-Type" : "application/json"
				},
				data : JSON.stringify({myapprove:myapprove}),
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

	$(document).ready(
			function() {
				$('.search2').on(
						"click",
						function(event) {

							self.location = "/ex/approve/approve_cancle"
									+ '${pageMaker.makePage(1)}'
									+ '&searchType='
									+ $("select option:selected").val()
									+ "&keyword=" + $('#keyword').val()
									+ "&eseq=${userName}";

						});

			});

	var result ='${msg}';
	if (result=='delete'){
		alert("삭제되었습니다.");
	}
</script>
</head>

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
            <div class="approve_middle">
               <select name="searchType" class="search">
		    		<option value="n" <c:out value="${pageMaker.searchType==null?'selected':''}" ></c:out> >----</option>
		    		<option value="제목" <c:out value="${pageMaker.searchType eq '제목'?'selected':''}" ></c:out> >제목</option>
		    	</select>
				<input type="text" placeholder="기결결재검색" class="search" id="keyword" name="keyword">&nbsp;
				<input type="submit" class="search2" value="검색">          
            </div>
            <hr class="line">
            <div class="approve_bottom">
                <div class="approve_list">
                    <c:forEach var="cancle" items="${cancle}" >
                    <div>
                    
                        <span class="c0"></span>
                        <c:set var="star" value="${cancle.myapprove}" />
								<c:choose>
									<c:when test="${star eq 'y'}">
										<span><img src="/ex/resources/image/star_2.png"
											class="star" width="20px" height="20px" /></span>
									</c:when>
									<c:otherwise>
										<span><img src="/ex/resources/image/star.png"
											class="star" width="20px" height="20px" /></span>
									</c:otherwise>
								</c:choose>
								<input type="hidden" name="aseq" value="${cancle.aseq}" class="aseq"> 
								<input type="hidden" name="myapprove" value="${cancle.myapprove}" class="myapprove">
                        <span class="c1"><div class="align1" >${cancle.aseq}</div></span> 
                        <span class="c2" id="name">
                            <div class="dropdown">
                                <div class="align1">${cancle.writer}</div>
                                <div class="dropdown_content">
                                                                  </div>
                            </div>
                        </span>
                        <span class="c3"><a href="approve_content?aseq=${cancle.aseq}&eseq=${userName}"><div class="align2"> ${cancle.title}</div></a></span>
                        <span class="c4"><div class="align3" > <fmt:formatDate value="${cancle.indate}" pattern="yyyy-MM-dd" /></div></span>
                        <hr class="line2">
                    </div>
                     </c:forEach>                   
                                        
                </div>
                <hr class="line3">
                <div class="approve_page">
			<!-- 페이지 -->
			<div class="pagination">
				<c:if test="${pageMaker.prev }">
					<a href="approve_cancle${pageMaker.makeSearch(pageMaker.startPage-1)}&eseq=${userName}">&lt;</a>
				</c:if>

				<c:forEach begin="${pageMaker.startPage }" 
				end="${ pageMaker.endPage}"	var="idx">
					<c:choose>
						<c:when test="${idx eq pageMaker.page }">
							<a href='approve_cancle${pageMaker.makeSearch(idx)}&eseq=${userName}'
							<c:out value="${pageMaker.page==idx?' class=active ':'' }"/>>
							 ${idx}</a>
						</c:when>
						<c:otherwise>
							<a href='approve_cancle${pageMaker.makeSearch(idx)}&eseq=${userName}'> 
							${idx}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageMaker.next }">
					<a href="approve_cancle${pageMaker.makeSearch(pageMaker.endPage+1)}&eseq=${userName}">&gt;</a>
				</c:if>
                      </div>
                </div>
            </div>
            


        </div>
    </div>    

<%@include file="../include/footer.jsp"%>
            
</body>
</html>