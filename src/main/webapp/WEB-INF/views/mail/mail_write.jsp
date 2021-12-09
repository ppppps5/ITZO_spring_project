<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="/ex/resources/css/mail_write.css">
    
<script type="text/javascript"> 
	var curr_url = document.URL;
	var new_curr_url = new URL(curr_url);

	//url 내 type이라는 파라미터의 값을 param에 넣어줌
	var param = new_curr_url.searchParams.get("sender");
	if(param!=null){
		$(document).ready(function(){
			$('#receiver').val(param);
		});
		
		/* alert(param); */
	}
	
	// Get the modal
	var modal = document.getElementById('addressModal');

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}	
/* 	function setChildText(){
        openWin.document.getElementById("receiver").value = document.getElementById("receiver").value;
    }

	function getAddress(){
		document.getElementById("selectReceiver").value=opener.document.getElementById("receiver").value;
		if (event.target == modal) {
		    modal.style.display = "none";
		  }
	} */
	
	function getAddress(){
		var address=document.getElementById('selectReceiver').value;
		document.getElementById("receiver").value=address;
		document.getElementById('addressModal').style.display='none';
	}
		
		
</script>
    
    <style>
.wrap{ width:100%;height:100%; position:relative;}
#addressModal{width: 550px; height:550px;z-index:3;display:none;position:fixed;left:0;top:0;overflow:auto;
    background-color:rgb(0, 0, 0);margin:0 auto;position:absolute;border-radius: 15px 15px 15px 15px/15px 15px 15px 15px;
    left:50%;
    top:50%;
    margin-left:-275px;
    margin-top:-275px;}
.content{background-color: #fff;}

.address {
	border: 2px solid rgb(23, 22, 82);
	padding: 10px 10px 10px 20px;
	font-size: 15px;
	border-radius: 0px 0px 15px 15px/0px 0px 15px 15px;
	height: 500px;
	overflow: scroll;
	-ms-overflow-style: none;
	overflow-x: hidden;
}

.address::-webkit-scrollbar {
	display: none;
}

.title {
	border-radius: 15px 15px 0px 0px/15px 15px 0px 0px;
	color: #fff;
	background-color: rgb(23, 22, 82);
	height: 50px;
	padding-left: 20px;
	padding-top: 12px;
}

.title_con {
	font-size: 20px;
}

.select_btn {
	margin-left: 350px;
	border: none;
	background-color: #f4f4f4;
	color: rgb(23, 22, 82);
	font-size: 16px;
	padding: 5px 10px 5px 10px;
	border-radius: 5px 5px 5px 5px/5px 5px 5px 5px;
}

.select_btn:hover{cursor:pointer;}

.list:hover {
	text-decoration: underline #c1bed3;
	cursor: pointer;
	font-weight: bold;
}

.check:hover {
	cursor: pointer;
}
.close_btn{padding-left: 20px; font-size: 20px;}
.close_btn:hover{cursor: pointer;}
    </style>

	
</head>

<body>
    <!-- 메뉴 -->
    <%@include file="../include/menu.jsp"%>

    <!-- contents -->
    <div id="wrap">
 
        <div id="contents">
            <div class="mail_top">
                <p>메일</p>
                <span class="smenu"> <a href="/ex/mail/mail_write?eseq=${userName}">메일쓰기</a>&nbsp;
					|&nbsp; <a href="/ex/mail/mail_receivelist?eseq=${userName}">받은메일</a>&nbsp;|&nbsp; <a
					href="/ex/mail/mail_sendlist?eseq=${userName}">보낸메일</a>&nbsp;|&nbsp; <a
					href="/ex/mail/mail_important?eseq=${userName}">중요메일</a>&nbsp;|&nbsp; <a
					href="/ex/mail/mail_deletelist?eseq=${userName}">휴지통</a>
				</span>
            </div>
            <div class="mail_bottom">
                <div class="write">
                	<form method="post" enctype="multipart/form-data" >
                		<input type="hidden" name="eseq" value="${userName}">
	                    <p>받는사람<input type="text" id="receiver" name="receiver" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 40px;" value=""/>
	                    <button type="button" class="address_btn"  onclick="document.getElementById('addressModal').style.display='block'">주소록</button></p>
	                    <p>제목<input type="text" name="title" style="border:1px solid #ccc; width:600px; height:30px; margin-bottom:5px;margin-left: 70px;"/></p>
	                    <p>파일첨부<input multiple="multiple" type="file" name="File" style="margin-left: 40px;"/>
	                    </p>	                    
	                    <textarea class="textarea" name="content" style="border:1px solid #ccc; width:1000px; height:500px; margin-top:10px;">&nbsp;</textarea>
	                    <br><br>
	                    <button type="submit" class="send_btn">보내기</button>
	                </form>
	                <div id="addressModal">
                        <div class="content" >
                            <div class="title">
                                <span class="title_con">주소록</span> <span><button class="select_btn"  onclick="getAddress()"><b>선택</b></button></span>
                                <span onclick="document.getElementById('addressModal').style.display='none'" class="close_btn">&times;</span>

                            </div>
                            <div class="address">
                                <form class="form" method="post">
									<c:forEach var="list" items="${addressbook}">
										<div>
											<span><input type="checkbox" id="selectReceiver" class="check" value="${list.email}"></span> 
											<span class="list">${list.name} <b>${list.position}</b> ( ${list.email} ) </span>
										</div>
									</c:forEach>
								</form>
                            </div>
                        </div>    
					</div>
                </div>
            </div>

        </div>
    </div>    
    
<%@include file="../include/footer.jsp"%>

</body>
</html>