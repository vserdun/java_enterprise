<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Bank service</title>
</head>
<body>
<form:form modelAttribute="clientAccountAttribute" action="addAccount" method="post">
    <table>


        <tr>
            <td>
                <form:label path="balance">Balance:</form:label>
            </td>
            <td>
                <form:input path="balance"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="currency">Currency:</form:label>
            </td>
            <td>
                <form:input path="currency"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>