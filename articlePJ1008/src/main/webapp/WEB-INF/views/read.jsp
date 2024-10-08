<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 읽기</title>
</head>
<body>
<h2>게시글 읽기</h2>

<p>제목: ${article.title}</p>
<p>작성자: ${article.writerName}</p>
<p>작성일: ${article.postingDate}</p>
<p>조회수: ${article.readCount}</p>
<p>내용: ${article.content}</p>

<a href="${pageContext.request.contextPath}/list">목록으로 돌아가기</a>
</body>
</html>