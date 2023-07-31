<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMO</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<link rel="stylesheet" href="https://me2.do/5BvBFJ57">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>

	th{
		width:200px;
	}
	textarea{
		width:520px;
	}
	select{
		width: 520px;
	}

</style>
</head>
<body>
<h1>Memo add</h1>

<form method="POST" action="/memo2/edit1.do">
<table>
	<tr>
		<th>메모</th>
		<td><textarea name="memo" id="memo" required></textarea>></td>
	</tr>
	<tr>
		<th>카테고리</th>
		<td>
			<select name="category" id="category"></select>
			<c:forEach items="${clist}" var="cdto">
				<option value="${cdto.seq }">${cdto.name}</option>
			</c:forEach>
		</td>
	</tr>
</table>

	<div>
		<input type="button" value="돌아가기" onclick="location.href='/memo/list1.do';">
		<input type="submit" value="추가하기" class="primary">
	</div>
</form>


<script>

	alert(${dto.cseq});
	
	$("#caategory").val(${dto.cseq});
	
</script>
</body>
</html>
