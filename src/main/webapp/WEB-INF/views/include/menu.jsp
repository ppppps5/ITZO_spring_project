<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Navigation -->
<header class="header">
	<a href="/ex/"><img src="/ex/resources/image/logo.png" width="120px"
		height="65px" class="logo"></a> <input class="menu-btn"
		type="checkbox" id="menu-btn" /> <label class="menu-icon"
		for="menu-btn"><span class="navicon"></span></label>
	<ul class="menu">
		<li><a href="/ex/notice/notice_list?eseq=${userName}">공지사항</a></li>
		<li><a href="/ex/emp/scheduleCheck.do">일정확인</a></li>
		<li><a href="/ex/attendance?eseq=${userName}">근태확인</a></li>
		<li><a href="/ex/salary/salary_list?eseq=${userName}">급여확인</a></li>
		<li><a href="/ex/mail/mail_receivelist?eseq=${userName}">메일함</a></li>
		<li><a href="/ex/approve/approve_each?eseq=${userName}">전자결재</a></li>
		<li><a href="/ex/board/board_list?eseq=${userName}">업무게시판</a></li>
		<li><a href="/ex/vacation/vacation_check?eseq=${userName}">휴가신청</a></li>
	</ul>
</header>