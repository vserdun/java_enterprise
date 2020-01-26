<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
   <head>
       <title>Simple bank service</title>
      <link rel="stylesheet"
      		 href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   </head>
   <body>
   <jsp:include page="navbar.jsp"/>
        <p><h5>Welcome, ${pageContext.request.userPrincipal.name}</h5>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
            <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
   </body>
</html>