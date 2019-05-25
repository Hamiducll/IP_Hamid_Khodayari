<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">
<%@include file="head.jsp"%>
<%@include file="header.jsp"%>
<body>
<h1>Add gerecht</h1>
<c:if test="${errors!=null}">
    <div>
        <c:forEach items="${errors}" var="error">
            <p>${error.defaultMessage}</p>
        </c:forEach>
    </div>
</c:if>

<form method="post" action="/gerechten/add">
    <p>
        <label>Beschrijving: </label>
        <input type="text" name="beschrijving"/>
    </p>
    <p>
        <label>Prijs: </label>
        <input type="number" step="0.1" name="prijs" />
    </p>
   <p>
       <label class="titel" for="type">Type: </label><br>
       <select id="type" name="type" style="width: 245px">
           <option selected value> -- select a type -- </option>
           <c:forEach var="type" items="${types}">
               <option value="${type}">${type.type}</option>
           </c:forEach>
       </select>
   </p>

    <p>
        <input type="submit" value="Add" />
        <input type="reset" value="Reset" />
    </p>
</form>
</body>
</html>