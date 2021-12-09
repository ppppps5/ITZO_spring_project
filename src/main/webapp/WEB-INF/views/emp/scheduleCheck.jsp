<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/schedule_check.css">

<link href='${pageContext.request.contextPath}/resources/fullcalendar-5.9.0/lib/main.css' rel='stylesheet' />
<script src='${pageContext.request.contextPath}/resources/fullcalendar-5.9.0/lib/main.js'></script>
<%-- <script src='${pageContext.request.contextPath}/resources/fullcalendar-5.9.0/lib/locales/ko.js'></script> --%>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script> -->

<style>
  body {
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  }

  #calendar {
    width: 1100px;
    height: 670px;
    margin: 0 auto;
    margin-top: 10px
  }

</style>
</head>
<body>
    <!-- 메뉴 -->
    <%@include file="../include/menu.jsp"%>

    <!-- contents -->
    <div id="wrap">
        <div id="contents">
            <div class="schedule_top">
                <p>일정 확인</p>
            </div>
            
            <!-- calendar -->
            <div id='calendar' style="position : relative;"></div>
           
        </div>
    </div>
    
    <%@include file="../include/footer.jsp"%>


<script type='text/javascript'>
document.addEventListener('DOMContentLoaded', function() {
	  var calendarEl = document.getElementById('calendar');

	  var calendar = new FullCalendar.Calendar(calendarEl, {
		dayMaxEventRows: true,
	    views: {
	    timeGrid: {
		dayMaxEventRows: 6
	    }},
	    googleCalendarApiKey: 'AIzaSyD3CsBi9wC2kU5KM_DzXHbgrLFXg9r6NwY',
	    eventSources: [
	      {
	          googleCalendarId: '3dujtduiqbv0n0a1qlan49p8hc@group.calendar.google.com',
	          className: '회사일정',
	          color: '#4285F4',
	          //textColor: 'black' 
	        },
	      {
	          googleCalendarId: '0ci4h95d55f60c13k5q7f00l7k@group.calendar.google.com',
	          className: '개인일정',
	          color: '#E4C441',
	        },
	      {
	          googleCalendarId: 'iir405vpo10ba4kudtpugrdjvo@group.calendar.google.com',
	          className: '생일',
	          color: '#D50000',
	        },
	      {
	          googleCalendarId: 'ko.south_korea#holiday@group.v.calendar.google.com',
	          className: '대한민국 휴일',
	          color: '#797979',
	        }
	    ]
	  });
	  calendar.render();
	});
</script>

</body>
</html>