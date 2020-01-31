<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Simple library app</title>
</head>
<body>
<form:form modelAttribute="bankCard" action="addCard" method="post">
    <table>
        <tr>
            <td>
                <form:label path="accounts"/>Account:
            </td>
            <td>
                <form:select path="accounts" items="${accountsMap}"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>