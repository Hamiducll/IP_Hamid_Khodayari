<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@include file="head.jsp"%>
<%@include file="header.jsp"%>
<body>
<h1>Change Gerechten</h1>
    <c:if test="${empty gerechten}">
        <p>Er staan geen gerechten op het menu.</p>
    </c:if>
    <table>
        <thead>
        <th>Beschrijving</th>
        <th>Update</th>
        <th>Delete</th>
        </thead>
        <tbody>
        <c:forEach var="g" items="${gerechten}">
            <tr>
                <td>${g.beschrijving}</td>
                <td><a href="/gerechten/update?id=${g.id}">Update</a></td>
                <td><a href="/gerechten/delete?id=${g.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <table>
        <tr>
            <td></td>
            <td><a href="/gerechten/add">Add Gerecht</a></td>
        </tr>
    </table>
</body>
</html>