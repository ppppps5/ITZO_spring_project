<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/vacation_check.css">
   

     <script>

        
        $(document).ready(function(){
            $(".star").click(function(){
                if($(this).attr("src")==="/ex/resources/image/star.png"){
                    $(this).attr("src","/ex/resources/image/star_2.png");
                }else{
                    $(this).attr("src","/ex/resources/image/star.png");
                }
            });


            $(".dropdown").click(function(){
                if ($(this).find(".dropdown_content").css("display")==="block"){
                    $(this).find(".dropdown_content").css("display","none");
                } else{
                    $(this).find(".dropdown_content").css("display","block");
                }
            });
        });
        
    	var result ='${msg}';
    	if (result=='send'){
    		alert("등록되었습니다.");
    	}
    	
    	var result ='${msg}';
    	if (result=='update'){
    		alert("수정되었습니다.");
    	} 
    	
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
        <div class="vacation_top">
            <p>휴가신청</p>
            <span class="smenu"> 
                  <a href="/ex/vacation/vacation_check?eseq=${userName}">휴가신청</a>&nbsp;|&nbsp;
         		 <a href="/ex/vacation/vacation_write?eseq=${userName}">휴가신청서작성</a>       
                </span>
        </div>
        <div class="vcheck_bottom">
            <div>
                <br>
                <h2></h2>
            </div>

                <div class="select">
                    <select>
                        <option>2021</option>
                        <option>2020</option>
                        <option>2019</option>
                    </select>
                    <select>
                        <option>1월</option>
                        <option>2월</option>
                        <option>3월</option>
                        <option>4월</option>
                        <option>5월</option>
                        <option>6월</option>
                        <option>7월</option>
                        <option>8월</option>
                        <option>9월</option>
                        <option>10월</option>
                        <option>11월</option>
                        <option>12월</option>
                    </select>
                </div>
                <div class="table1">
                    <table style="width: 1000px; border:0;">
                        <thead>
                            <tr style="border-bottom: 1px solid #c9c9c9; border-top: 1px solid #c9c9c9;background-color: #eeeeee;">
                                <th>번호</th>
                                <th>제목</th>
                                <th>휴가일</th>
                                <th>허용일수</th>
                                <th>제출일</th>
                                <th>검토결과</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="check" items="${check}" >
                            	<tr>
                                	<td>${check.vseq}<td><a href="vacation_content?vseq=${check.vseq}&eseq=${userName}">${check.title}</a></td><td><fmt:formatDate value="${check.start1}" pattern="yyyy-MM-dd" />~<fmt:formatDate value="${check.end1}" pattern="yyyy-MM-dd" /></td><td>${check.maxdate}</td>
                                	<td><fmt:formatDate value="${check.cdate}" pattern="yyyy-MM-dd" /></td><td>${check.result}</td>
                            	</tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
            </div>
  

        </div>
    </div>  
<%@include file="../include/footer.jsp"%>

</body>
</html>