<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
   <head>
       <title>Simple bank service</title>
   </head>
   <body>
   <p>Go to <a href="<c:url value="/users/list" />">Users list</a></p>
   <p>Go to <a href="<c:url value="/accounts/list" />">Accounts list</a></p>
   </body>
</html>