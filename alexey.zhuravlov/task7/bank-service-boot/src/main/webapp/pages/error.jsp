<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
   <head>
       <title>Error Page</title>
   </head>
   <body>
   <h3>Ooops</h3>
          <h3>${error}</h3>
          <p>Status ${status}</p>
          <p>${message}</p>
          <p>${exception}</p>
                <p>Go to <a href="<c:url value="/users/list" />">Users list</a></p>
                <p>Go to <a href="<c:url value="/accounts/list" />">Accounts list</a></p>
          <p><b>Stack trace is</b><br>
              <pre>${trace}</pre>
          </p>
   </body>
</html>