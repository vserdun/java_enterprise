<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Task 6 - Simple Bank Service</title>
</head>
<body>
    <form:form modelAttribute="accountAttribute" action="editAccount" method="post">
       <table>
           <tr>
               <td>
                   <form:input path="id" type="hidden"/>
               </td>
           </tr>
           <tr>
               <td>
                   <form:label path="amount">Amount</form:label>
               </td>
               <td>
                   <form:input path="amount"/>
               </td>
           </tr>
       </table>
       <div>
            <spacer height="50"></spacer>
       </div>
       <div>
            <input type="submit" value="Save"/>
       </div>
    </form:form>
</body>
</html>