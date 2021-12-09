<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ITZO</title>
	<link rel="stylesheet" href="/ex/resources/css/employee_registration.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index01.css">
	<link rel="stylesheet" type="text/css" media="screen" href="webjars/jqgrid/4.7.0/css/ui.jqgrid.css" />    
<style>
    #registrationform {
        font-size: 20px;
        text-align: center;
        padding: 200px 0px;
    }
</style>
</head>
<body class="chl-reg" data-spm="1">
<c:url value="/user/addUser.do" var="addUserUrl" />
<form:form name="frmMember" action="${addUserUrl}" method="POST" accept-charset="utf-8">

<div id="page" class="">
    <div id="header" class="clearfix">
        <div class="logo">
            <!-- <h1><a title="ITZO" href="#"><img src="image/logo.png" alt="" style="width: 80px; height: 45px; background:url(../image/logo.png)"></a></h1> -->
          <h1><a title="ITZO" href="/ex/login/loginForm.do"><img src="/ex/resources/image/logo.png" class="logo-link"></a></h1>
          <!-- <h1><a title="ITZO" class="logo-link" href="../login/loginForm.jsp"></a></h1> -->

        </div>           
    </div>

    <div id="registrationform">
        <div id="inputlbl">
            <div>사원등록이 완료되었습니다.</div><br>
            <div><a href="/ex/login/loginForm.do">[로그인창으로 이동]</a></div>
        </div>
    </div>
  </form:form>

    <!-- footer -->
    <footer id="footer" style="background: #fff;" >
        <div class="container">
            <address style="margin: 20px 0 20px 0;">
                Copyright © <a href="#"><strong>ITZO</strong></a> All Rights Reserved.
            </address>
        </div>
    </footer>
    <!-- //footer -->
</div>

</body>
</html>