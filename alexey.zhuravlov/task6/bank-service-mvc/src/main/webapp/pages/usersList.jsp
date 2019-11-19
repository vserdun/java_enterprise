<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
   <title>Simple library app</title>
</head>
<body>
<style>
   DIV.table
   {
       display:table; width: 100%;;
   }
   FORM.tr, DIV.tr
   {
       display:table-row;
       padding: 10px;
       margin: 10px;
   }
   SPAN.td
   {
       display:table-cell;
       padding: 10px;
       margin: 10px;
   }
   SPAN.th
   {
       display:table-cell;
       font-weight: bold;
   }
</style>
 <div class="table">
     <div class="tr">
         <span class="th">Id</span>
         <span class="th">First Name</span>
         <span class="th">Last Name</span>
     </div>
 <c:forEach items="${users}" var="user">
     <form:form class="tr" modelAttribute="userAttribute" action="" method="post">
         <span class="td"><c:out value="${user.userId}"/></span>
         <span class="td"><c:out value="${user.userFirstName}"/></span>
         <span class="td"><c:out value="${user.userLastName}"/></span>
         <span class="td">
            <input type="button" onclick="location.href='edit?userId=${user.userId}';" value="Edit user"/>
         </span>
         <span class="td">
             <input type="button" onclick="location.href='deleteUser?userId=${user.userId}';" value="Delete user"/>
         </span>
         <span class="td">
             <input type="button" onclick="location.href='addAccount?userId=${user.userId}';" value="Add account"/>
          </span>
     </form:form>
 </c:forEach>
 </div>
 <input type="button" onclick="location.href='add';" value="Add new user"/>
</body>
</html>