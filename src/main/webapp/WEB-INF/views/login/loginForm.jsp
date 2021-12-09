<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta  charset="UTF-8" />
    <title>로그인 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index01.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index02.css">

</head>


<!--  -->
<body class="chl-reg" data-spm="1" onload="document.frmLogin.eseq.focus();">
<c:url value="/login" var="loginUrl" />
<form:form name="frmLogin" action="${loginUrl}" method="POST">

<div id="page" class="">
    <div id="header" class="clearfix">
        <div class="logo">
            <h1><a title="ITZO" class="logo-link" href="#"></a></h1>
        </div>           
    </div>
    <div id="content">
        <div class="login-newbg" style="background-image: url(/ex/resources/image/bg.png);">
            <input type="hidden" id="J_adUrl" name="adUrl" value="">
            <input type="hidden" id="J_adImage" name="adImage" value="">
            <input type="hidden" id="J_adText" name="adText" value="">
            <input type="hidden" id="J_viewFd4PC" name="viewFd4PC" value="">
            <input type="hidden" id="J_viewFd4Mobile" name="viewFd4Mobile" value="">
        </div>
        <div class="login-adlink">
            <a href="#" target="_blank"></a>
        </div>
        <div class="content-layout">
            <div class="login-box-warp">
                <div id="container" class="login-box loading">
                    
                    <!-- id password 로그인 -->
                    <div id="login1" class="width-vertical login-label-icon login-view-password taobao_pc">
                        <div class="corner-icon-view view-type-qrcode">
                            <i class="iconfont icon-qrcode" onclick="changeLogin()"></i>
                            <div class="login-tip">
                                <div class="poptip">
                                    <div class="poptip-arrow">
                                        <em></em>
                                        <span></span>
                                    </div>
                                    <div class="poptip-content">QR코드</div>
                                </div>
                            </div>
                        </div>
                        <div class="login-content nc-outer-box">
                            <div class="login-password">
                                <div class="login-blocks login-switch-tab">
                                    <a href="javascript:void(0);" target="_self" class="password-login-tab-item">로그인</a>
                                </div>
                                <c:if test="${param.error != null}">
                                <div id="login-error" class="login-error">
                                    <i class="iconfont icon-warning"></i>
                                    <div class="login-error-msg">아이디 또는 비밀번호를 다시 확인해주세요.</div>
                                </div>
                                </c:if>
                                <%-- <form id="login-form" class="login-form"> --%>
                                    <div class="fm-field">
                                        <div>
                                            <label class="fm-label-icon">
                                                <i class="icon-user iconfont" title="Id"></i>
                                            </label>
                                        </div>   
                                        <div class="input-plain-wrap input-wrap-loginid">
                                            <input type="text" name="eseq" class="fm-text" id="fm-login-id" tabindex="1" aria-label="Id" placeholder="사원번호" autocapitalize="off">
                                        </div>
                                    </div> 
                                    <div class="fm-field">
                                        <div>
                                            <label class="fm-label-icon">
                                                <i class="icon-pwd iconfont" title="Password"></i>
                                            </label>
                                        </div>
                                        <div class="input-plain-wrap input-wrap-password">
                                            <input type="password" name="pwd" class="fm-text" id="fm-login-password" tabindex="2" aria-label="Password" placeholder="비밀번호" maxlength="40" autocapitalize="off">
                                        </div>
                                    </div>    
                                    <div class="fm-field baxia-container-wrapper">
                                        <div class="baxia-container tb-login">
                                            <div id="baxia-password" style="display: block;"></div>
                                        </div>
                                        <div id="nocaptcha-password" class="nc-container tb-login" style="display: none;" data-nc-idx="1">
                                            <div id="nc_1_wrapper" class="nc_wrapper">
                                                <div id="nc_1_n1t" class="nc_scale">
                                                    <div id="nc_1__bg" class="nc_bg"></div>
                                                    <span id="nc_1_n1z" class="nc_iconfont btn_slide"></span>
                                                    <div id="nc_1_scale_text" class="scale_text slidetounlock">
                                                        <span class="nc_lang-cnt" data-nc-lang="_startTEXT">드래그</span>
                                                    </div>
                                                    <div id="nc_1_clickCaptcha" class="clickCaptcha">
                                                        <div class="clickCaptcha_text">
                                                            <b id="nc_1__captcha_text" class="nc_captch_text"></b>
                                                            <i id="nc_1__btn_2" class="nc_iconfont nc_btn_2 btn_refresh"></i>
                                                        </div>
                                                        <div class="clickCaptcha_img"></div>
                                                        <div class="clickCaptcha_btn"></div>
                                                    </div>
                                                    <div id="nc_1_imgCaptcha" class="imgCaptcha">
                                                        <div class="imgCaptcha_text">
                                                            <input type="text" id="nc_1_captcha_input" maxlength="6" style="ime-mode:disabled">
                                                        </div>
                                                        <div class="imgCaptcha_img" id="nc_1__imgCaptcha_img"></div>
                                                        <i id="nc_1__btn_1" class="nc_iconfont nc_btn_1 btn_refresh" onclick="document.getElementById('nc_1__imgCaptcha_img').children[0].click()"></i>
                                                        <div class="imgCaptcha_btn">
                                                            <div id="nc_1__captcha_img_text" class="nc_captcha_img_text"></div>
                                                            <div id="nc_1_scale_submit" class="nc_scale_submit"></div>
                                                        </div>
                                                    </div>
                                                    <div id="nc_1_cc" class="nc-cc"></div>
                                                    <i id="nc_1__voicebtn" tabindex="0" role="button" class="nc_voicebtn nc_iconfont" style="display: none;"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fm-btn">
                                        <button type="submit" tabindex="3" class="fm-button fm-submit password-login">로그인</button>
                                    </div>
                                    <div class="login-blocks login-links">
                                        <!-- <a href="../admin/adminMain.do" style="float: left;">사원등록</a> -->
                                        <a href="#">Id 찾기</a>
                                        <a href="#">Password 찾기</a>
                                    </div>
                                <%-- </form> --%>
                            </div>
                        </div>
                        <div class="extra-login-content"></div>
                    </div>
                    </form:form>
                    <!-- //id password 로그인 -->

                    <!-- qr코드 로그인 -->
                    <div id="login2" class="width-vertical login-label-icon login-view-qrcode" style="display: none;">
                        <div class="corner-icon-view view-type-password">
                            <div class="master-login-title">QR코드 스캔</div>
                            <i class="iconfont icon-password" onclick="changeLogin2()"></i>
                        </div>
                        <div class="login-content nc-outer-box">
                            <div class="qrcode-login">
                                <div>
                                    <div class="qrcode-img">
                                        <undefined>
                                            <%-- <canvas height="162" width="162; > --%>
                                               <img src="/ex/resources/image/qrCode.png" width="140px; height:140px;">
                                            <%-- </canvas> --%>
                                        </undefined>
                                        <div class="qrcode-error" style="display: none;">
                                            <p>유효시간 만료</p>
                                            <button class="refresh" type="button">새로고침</button>
                                        </div>
                                    </div>
                                    <div class="qrcode-desc">
                                        <i class="iconfont icon-scan"></i>
                                        <p>
                                            <font class="ft-gray">
                                                ITZO<br>
                                                QR코드를 스캔해주세요.
                                            </font>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="extra-login-content"></div>
                    </div>
                    <!-- //qr코드 로그인 -->
                </div>
            </div>
        </div>
    </div>

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

<script>
    function changeLogin() {
        var login1 = document.getElementById("login1");
        var login2 = document.getElementById("login2");
        if(login1.style.display='none') {
            login2.style.display='block';
        }
    }
    
    function changeLogin2() {
        var login1 = document.getElementById("login1");
        var login2 = document.getElementById("login2");
        if(login2.style.display='none') {
            login1.style.display='block';
        }
    }
</script>

</body>
</html>
