<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
   <title>Add user</title>
</head>
<body>
<form:form modelAttribute="userAttribute" action="addUser" method="post">
   <table>
       <tr>
           <td>
               <form:label path="userFirstName">User First Name</form:label>
           </td>
           <td>
               <form:input path="userFirstName"/>
           </td>
       </tr>
       <tr>
           <td>
               <form:label path="userLastName">User Last Name</form:label>
           </td>
           <td>
               <form:input path="userLastName"/>
           </td>
       </tr>
   </table>
   <input type="submit" value="Save"/>
</form:form>
</body>
</html>