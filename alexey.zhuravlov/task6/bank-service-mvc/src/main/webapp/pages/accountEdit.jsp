<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
   <title>Simple library app</title>
</head>
<body>
<form:form modelAttribute="accountAttribute" action="editAccount" method="post">
   <table>
       <tr>
            <td>
                  <form:input path="accountId" type="hidden"/>
            </td>
       </tr>
       <tr>
           <td>
               <form:label path="accountBalance">Account Balance</form:label>
           </td>
           <td>
               <form:input path="accountBalance"/>
           </td>
       </tr>
       <tr>
            <td>
                <form:input path="userId" type="hidden"/>
            </td>
       </tr>
   </table>
   <input type="submit" value="Save"/>
</form:form>
</body>
</html>