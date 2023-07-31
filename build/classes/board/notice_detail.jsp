<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%@ include file="/WEB-INF/views/inc/assetclient.jsp" %>
	
<style>

</style>
</head>
<body>
	<!--  -->
	<%@ include file="/WEB-INF/views/inc/headerclient.jsp" %>
	 <div class="introducetitle">공지사항</div>


    <div class="mainmenu text-center">
        <span class="sidebar">
            <div class="bg-light border-right">
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action active" style="background-color: #043886;">알림게시판</a>
                    <a href="/neulbom/client/board/notice.do" class="list-group-item list-group-item-action">공지사항</a>
                    <a href="/neulbom/client/board/food.do" class="list-group-item list-group-item-action">식단표</a>
                    <a href="#" class="list-group-item list-group-item-action">생활</a>
                </div>
            </div>
        </span>

        

        <div id="deepmenu">
            <nav class="navbar bg-light">
                <div class="container-fluid">
                    <h3 style="font-weight: 400;">공지사항</h3>
                    <div style="display: flex;">
                        <span class="material-symbols-outlined" style="display: block;">
                            home
                        </span>
                        > 알림게시판 > 공지사항
                    </div>
                </div>
            </nav>
            <hr>
            <table class="table table-bordered table-hover content-head_wj">
                <thead>
                    <tr>
                      <th>${dto.notice_seq }</th>
                      <th style="text-align: left;">${dto.title}</th>
                      <th>${fn:substring(dto.notice_date.toString(), 0, 10)}</th>
                      <th>${dto.read}</th>
                    </tr>
                </thead>
            </table>
            <div class="content_wj" style="text-align:left;">
              ${dto.content}
            </div>
            <hr>
            <button type="button" class="btn btn-primary btn-sm" style="width:100px; height:40px; margin-right:10px; font-size: 20px; float:left;" onclick="history.back()">돌아가기</button>
        </div>
        </div>
        
<%@ include file="/WEB-INF/views/inc/footerclient.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>

</script>
</body>
</html>






