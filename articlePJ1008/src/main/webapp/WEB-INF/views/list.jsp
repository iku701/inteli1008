<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 1L);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 목록</title>

</head>
<body>

<h2>게시글 목록</h2>

<table border="1">
	<thead>
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="article" items="${listModel.articleList}">
		<tr>
			<td>${article.id}</td>
			<td><a href="${pageContext.request.contextPath}/read?id=${article.id}">${article.title}</a></td>
			<td>${article.writerName}</td>
			<td>${article.postingDate}</td>
			<td>${article.readCount}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<div class="pagination">
	<c:forEach var="i" begin="${beginPage}" end="${endPage}">
		<a href="${pageContext.request.contextPath}/list?p=${i}">${i}</a>
	</c:forEach>
</div>
<tr>
	<td colspan="5">
		<a href="writeForm">글쓰기</a>
	</td>
</tr>
</body>
</html>