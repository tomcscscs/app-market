<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찜리스트</title>
</head>
<body>
<div>
<c:forEach var="one" items="${pickList }">
<div onclick="location.href='${contextPath}/product/'${one.targetProductId}'"><img alt="productPic" src="${contextPath }${one.url}"> /${one.nickName }/${one.ownerAccountId }/${one.title }/${one.targetProductId} </div>


</c:forEach>
</div>

</body>
</html>