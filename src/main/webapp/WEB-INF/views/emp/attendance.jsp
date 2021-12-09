<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/attendence.css">

</head>

<body>
	<!-- 메뉴 -->
    <%@include file="../include/menu.jsp"%>

    <!-- content -->
    <div id="wrap">
        
        <div id="contents">
            <div class="att_top">
                <p>근태확인</p>
            </div>
            <div class="att_bottom">
                <div>
                    <br>
                    <c:forEach var="emp" items="${emp}">
                    <h2>${emp.name } ${emp.position } | 근태현황</h2>
                    </c:forEach>
                </div>
                <div class="table1">
                    <b>-2021년 기준</b>
                    <table style="width: 1000px; margin-left:100px; margin-right:100px; border:1px solid #c9c9c9;">
                        <thead>
                            <tr style="border-bottom: 1px solid #c9c9c9; background-color: #eeeeee;" >
                                <th>결근</th>
                                <th>지각</th>
                                <th>병가</th>
                                <th>월차</th>
                                <th>잔여월차</th>
                            </tr>
                        </thead>
                        <c:forEach var="attcon" items="${attcon}">
                        <tbody>
                            <tr>
                                <td>${attcon.abs}일</td>
                                <td>${attcon.ldate}일</td>
                                <td>${attcon.sick}일</td>
                                <td>${attcon.vadate}일</td>
                                <td>잔여: ${attcon.reva}일</td>
                            </tr>
                        </tbody>
                        </c:forEach>

                    </table>
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
                        <option selected="selected">9월</option>
                        <option>10월</option>
                        <option>11월</option>
                        <option>12월</option>
                    </select>

                </div>
                <div class="table2">
                    <table style="width: 1000px; border:0;">
                        <thead>
                            <tr style="border-bottom: 1px solid #c9c9c9; border-top: 1px solid #c9c9c9;background-color: #eeeeee;">
                                <th>일자</th>
                                <th>출근</th>
                                <th>퇴근</th>
                                <th>지각</th>
                                <th>추가근무</th>
                                <th>총업무시간</th>
                                <th>상태</th>
                            </tr>
                        </thead>
                        <c:forEach var="att" items="${att}">
                        <tbody>
                            <tr>
                                <td>${att.comdate}</td>
                                <td><fmt:formatDate value="${att.come}" pattern="HH:mm"/></td>
                                <td><fmt:formatDate value="${att.leave}" pattern="HH:mm"/></td>
                                <td>${att.late}</td>
                                <td>${att.add1}시간</td>
                                <td>${att.total1}시간</td>
                                <td>${att.state}</td>
                            </tr>
                        </tbody>
                        </c:forEach>
                    </table>
                </div>
                
            </div>
   
        </div>
    </div>    
            

<%@include file="../include/footer.jsp"%>


</body>
</html>