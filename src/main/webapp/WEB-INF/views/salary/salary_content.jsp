<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/salary_content.css">

<script>

$(".list_btn").on("click", function() {

	fObject.attr("method", "get");
	fObject.attr("action", "/ex/board/board_list");
	fObject.submit();

})

</script>
</head>

<body>
	<!-- 메뉴 -->
    <%@include file="../include/menu.jsp"%>

    <!-- contents -->
    <div id="wrap">
        <div id="contents">
            <div class="salary_top">
                <p>내 급여 확인</p>
            </div>
            <div class="salary_bottom">
                <div class="btn"><button type="button" class="list_btn">목록으로</button></span>
            </div>

            <div class="salary_content">
            	 <c:forEach var="salarycontent" items="${salarycontent}" >
                    <hr>
                    <p class="p1"><h2>&nbsp; ${salarycontent.stitle}</h2></p>
                    <p class="p2">받는 분&emsp; <b>${salarycontent.name}</b><span class="p3"></span><span class="p4">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<fmt:formatDate value="${salarycontent.indate}" pattern="yyyy-MM-dd HH:mm" /></span></p>
                    <br>
                    <hr>
                    <div class="content_detail">
                    <br>
                        <h2>귀하의 노고에 감사드립니다</h2>
                        <img src="/ex/resources/image/logo.png" width="80px" height="45px">
                        <table class="salary">
                            <thead>
                                <tr>
                                    <th scope="cols">지급내역</th>
                                    <th scope="cols">지급액(원)</th>
                                    <th scope="cols">지급내역</th>
                                    <th scope="cols">지급액(원)</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                                <tr>
                                    <th scope="row">기본급</th>
                                    <td>${salarycontent.bsalary}</td>
                                    <th scope="row">국민연금</th>
                                    <td>${salarycontent.pension}</td>
                                </tr>

                                <tr>
                                    <th scope="row">연차수당</th>
                                    <td>${salarycontent.annual}</td>
                                    <th scope="row">건강보험료</th>
                                    <td>${salarycontent.health}</td>
                                </tr>

                                <tr>
                                    <th scope="row">직책수당</th>
                                    <td>${salarycontent.position}</td>
                                    <th scope="row">고용보험료</th>
                                    <td>${salarycontent.emp}</td>
                                </tr>

                                <tr>
                                    <th scope="row">특근수당</th>
                                    <td>${salarycontent.holiday}</td>
                                    <th scope="row">소득세</th>
                                    <td>${salarycontent.income}</td>
                                </tr>

                                <tr>
                                    <th scope="row">잔업수당</th>
                                    <td>${salarycontent.over}</td>
                                    <th scope="row">주민세</th>
                                    <td>${salarycontent.resident}</td>
                                </tr>

                                <tr>
                                    <th scope="row">복리후생비</th>
                                    <td>${salarycontent.welfare}</td>
                                    <th scope="row">조합비</th>
                                    <td>${salarycontent.labor}</td>
                                </tr>

                                <tr>
                                    <th scope="row">상여금</th>
                                    <td>${salarycontent.bonus}</td>
                                    <th scope="row">장기요양보험료</th>
                                    <td>${salarycontent.longcare}</td>
                                </tr>

                                <tr>
                                    <th scope="row"></th>
                                    <td></td>
                                    <th scope="row">공제액계</th>
                                    <td>196,990</td>
                                </tr>

                                <tr>
                                    <th scope="row">지급액계</th>
                                    <td>1,800,000</td>
                                    <th scope="row">실수령금</th>
                                    <td>1,603,010</td>
                                </tr>
                            </tbody>
                        </table>
						</c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
<%@include file="../include/footer.jsp"%>
</body>
</html>