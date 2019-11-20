<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
   <title>Bank</title>
</head>
<body>
<form:form modelAttribute="userAttribute" action="editUser" method="post">
   <table>
        <tr>
          <td>
              <form:label path="id">User id</form:label>
          </td>
          <td>
              <form:label path="id" value="${user.id}"/>
          </td>
      </tr>
       <tr>
           <td>
               <form:label path="name">User name</form:label>
           </td>
           <td>
               <form:input path="name" />
           </td>
       </tr>
   </table>
   <input type="submit" value="Edit"/>
</form:form>
</body>
</html>