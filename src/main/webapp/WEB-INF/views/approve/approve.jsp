<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/approve.css">

    <script>  
        $(document).ready(function(){
            
            $(".folder>span").click(function() {
            $(".hidetab1").toggle();         
            
        });
        })
        $(document).ready(function(){
            
            $(".folder1>span").click(function() {
            $(".hidetab2").toggle();         
            
        });
        })
        </script>
        
</head>

<body>
<!-- 메뉴 -->
<%@include file="../include/menu.jsp"%>

	<!-- content -->

    <div id="wrap">


        <div id="contents">
            <div class="appr_top">
                <p>전자결재</p>
                <span class="smenu"> 
               <a href="/ex/approve/approve?eseq=${userName}">팀별결재현황</a>&nbsp;|&nbsp;
                <a href="/ex/approve/approve_write?eseq=${userName}">기안문작성</a>&nbsp;|&nbsp;
                <a href="/ex/approve/approve_decide?eseq=${userName}">미결함&nbsp;|&nbsp;
                <a href="/ex/approve/approve_cancle?eseq=${userName}">기결함</a>&nbsp;|&nbsp;
                <a href="/ex/approve/approve_each?eseq=${userName}">개인문서함</a>
                </span>
            </div>
            <br>

            <div class="appr_middle">
                <div class="appr_depart">    
                    <br>
                    <h2>팀별결재현황</h2>
                    <br>
                </div>    
            </div>
                
            <div class="appr_search">
                <img src="/ex/resources/image/search.png" alt="search" style="width: 13px" input type="text" placeholder="" class="search"><span>&nbsp;부서</span>&nbsp;
                <select name="department" class="department">
                    <option value="developer">개발팀</option>
                    <option value="account">기획팀</option>   
                </select>
                <span class="s0">기안자&nbsp;<input type="input">
                </span>
                <span class="s1"><input type="checkbox">&nbsp;취소문서포함</span>
                <button class="search_btn"> 조회</button>
            </div>

        <div class="table1">    
            <table style="width: 1000px; margin-left:100px; margin-right:100px; border:1px solid #c9c9c9;">
                <thead>
                <tr style="border-bottom: 1px solid #c9c9c9; background-color: #eeeeee;"  >
                    <th style="width:100px;">결재글번호</th>
                    <th style="width:180px;">제목</th>
                    <th style="width:180px;">결재내용</th>
                    <th style="width:70px;">결재자</th>
                    <th style="width:80px;">결재부서</th>
                    <th style="width:50px;">직급</th>
                    <th style="width:130px;">결재일</th>
                </tr>
            </thead>
         </table>
        </div>

        <div class="appr_bottom">
            <div class="appr_list">
                        <div class="folder">
                            <img src="/ex/resources/image/folder.png" width="20px" height="20px">
                            <span class="folder1">공통업무 - 기안지</span>
                                <div class="hidetab1"><br>    
                                    <table class="atable" style="width: 1000px; margin-left:0px; margin-right:100px; border:1px solid #fff; ">
                                        <c:forEach var="approve" items="${approve}">
                                        <input type="hidden" name="aseq" value="${approve.aseq}">
                                        <thead>
                                        <tr style="border-bottom: 1px solid #fff; background-color: #fff; text-align: center;">
                                            <th style="font-size: 12px; width:100px;">${approve.aseq}</th>
                                            <th style="font-size: 12px; width:180px"><a href="approve_content?aseq=${approve.aseq}&eseq=${userName}">${approve.title}</a></th>
                                            <th style="font-size: 12px; width:180px">${approve.content}</th>
                                            <th style="font-size: 12px; width:70px">${approve.approval}</th>
                                            <th style="font-size: 12px; width:80px">${approve.department}</th>
                                            <th style="font-size: 12px; width:50px">${approve.position}</th>
                                            <th style="font-size: 12px; width:130px"><fmt:formatDate value="${approve.indate}" pattern="yyyy-MM-dd" /></th>                                                                     
                                        </tr>
                                        </thead>
                                        </c:forEach>
                                        
                                    </table>
                                </div>
                                
</div>
        <div class="folder1">
            <img src="/ex/resources/image/folder.png" width="20px" height="20px">
                <span class="folder2">개인업무 - 기안지</span>
                    <div class="hidetab2"><br>
                        <table class="atable2" style="width: 1000px; margin-left:0px; margin-right:100px; border:1px solid #fff; ">
                            <c:forEach var="approvem" items="${approvem}">                   
                            
                            <thead>
                                <tr style="border-bottom: 1px solid #fff; background-color: #fff; text-align: center;">
  											<th style="font-size: 12px; width:100px;">${approvem.aseq}</th>
                                            <th style="font-size: 12px; width:180px;"><a href="approve_content?aseq=${approvem.aseq}&eseq=${userName}">${approvem.title}</a></th>
                                            <th style="font-size: 12px; width:180px;">${approvem.content}</th>
                                            <th style="font-size: 12px; width:70px;">${approvem.approval}</th>
                                            <th style="font-size: 12px; width:80px;">${approvem.department}</th>
                                            <th style="font-size: 12px; width:50px;">${approvem.position}</th>
                                            <th style="font-size: 12px; width:130px;"><fmt:formatDate value="${approvem.indate}" pattern="yyyy-MM-dd" /></th>                               
                                            </tr>                               
                            </thead>
                            </c:forEach>
                         </table>
                    </div>
                    
        </div>     
    </div>      
</div>
</div>
</div>

 <%@include file="../include/footer.jsp"%>


</body>
</html>