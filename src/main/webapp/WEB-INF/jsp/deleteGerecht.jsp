<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="head.jsp"%>
<%@include file="header.jsp"%>
<body>
    <main>
        <p>You are about to delete Gerecht: "<c:out value="${ToBeDeletedGerecht.beschrijving}"/>". Are you sure?</p>
        <form action="/gerechten/delete?id=${ToBeDeletedGerecht.id}" method="post">
            <input id="deleteGerecht" type="submit" value="Yes"/>
            <a href="/gerechten">No</a>
        </form>
    </main>
</body>
</html>