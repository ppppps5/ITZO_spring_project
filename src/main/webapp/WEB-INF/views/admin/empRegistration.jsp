<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 등록</title>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/employee_registration.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index01.css">
    <link rel="stylesheet" type="text/css" media="screen" href="webjars/jqgrid/4.7.0/css/ui.jqgrid.css" />

</head>
<body class="chl-reg" data-spm="1">
<c:url value="/user/addUser.do" var="addUserUrl" />
<form:form name="frmMember" action="${addUserUrl}" method="POST" accept-charset="utf-8">

<div id="page" class="">
    <div id="header" class="clearfix">
        <div class="logo">
            <!-- <h1><a title="ITZO" href="#"><img src="image/logo.png" alt="" style="width: 80px; height: 45px; background:url(../image/logo.png)"></a></h1> -->
          <h1><a title="ITZO" href="/ex/login/loginForm.do"><img src="/ex/resources/image/logo.png" class="logo-link"></a></h1>
          <!-- <h1><a title="ITZO" class="logo-link" href="/ex/login/loginForm.do"></a></h1> -->

        </div>           
    </div>

    <div id="registrationform">
        <h1 class="registration"><strong>사원등록</strong></h1>
        <div id="inputlbl">
            <div class="inputsnlables"><label>이름</label><input type="text" placeholder="" name="name" /></div>
            <div class="inputsnlables"><label>주민번호</label><input type="text" placeholder="" name="resinum" /></div>
            <div class="inputsnlables"><label>사원번호</label><input type="text" placeholder="" name="eseq" /></div>
            <div class="inputsnlables"><label>비밀번호</label><input type="password" placeholder="" name="pwd" /></div>
            <div class="inputsnlables"><label>이메일</label><input type="text" placeholder="" name="email" /></div>
            <div class="inputsnlables"><label>핸드폰 번호</label><input type="text" placeholder="" name="phone" /></div>
            <div class="inputsnlables"><label>주소</label><input type="text" placeholder="" name="address" /></div>
            <div class="inputsnlables"><label>부서번호</label><input type="text" placeholder="" name="dseq" /></div>
            <div class="inputsnlables"><label>직급</label><input type="text" placeholder="" name="position" /></div>
            <div class="inputsnlables"><label>연봉</label><input type="text" placeholder="" name="salary" /></div>
        </div>
        <button type="submit">등록</button>
    </div>
  </form:form>

    <!-- footer -->
    <footer id="footer" style="background: #fff;" >
        <div class="container">
            <address style="margin: 20px 0 20px 0;">
                Copyright ©
                <a href="#"><strong>ITZO</strong></a>
                All Rights Reserved.
            </address>
        </div>
    </footer>
    <!-- //footer -->
</div>


</body>
</html>