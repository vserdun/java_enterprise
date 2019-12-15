<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
   <head>
       <title>Simple bank service</title>
   </head>
   <body>
       <p align="center">Sorry, this operation is unavailable in the test mode</p>
       <p align="center"><a href="<c:url value="/users/list" />">Users</a></p>
       <p align="center"><a href="<c:url value="/accounts/list" />">Account list</a></p>
   </body>
</html>