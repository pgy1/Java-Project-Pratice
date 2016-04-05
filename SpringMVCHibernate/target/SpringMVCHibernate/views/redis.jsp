<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/1/25 0025
  Time: 下午 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Redis Test</title>
</head>
<body>
what about pgy ?
<br />
<c:forEach items="${pgy}" var="name">
  I think he is a ${name}<br />
</c:forEach>
</body>
</html>
