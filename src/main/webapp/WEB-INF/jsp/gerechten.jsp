<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@include file="head.jsp"%>
<%@include file="header.jsp"%>
<body>
<h1>Gerechten</h1>
<table>
    <thead>
    <th>Beschrijving</th>
    <th>Type</th>
    <th>prijs</th>
    </thead>
    <tbody>
    <c:forEach var="g" items="${gerechten}">
        <tr>
            <td>${g.beschrijving}</td>
            <td>${g.type}</td>
            <td>€   ${g.prijs}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>