<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
       <title>Task 6 - Simple Bank Service</title>
    </head>
    <body>
       <style>
           DIV.table
           {
               display:table; width: 80%;
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
               display: table-cell;
               font-weight: bold;
           }
       </style>

       <div class="table">
           <div class="tr">
                <span class="th">Id</span>
                <span class="th">Name</span>
                <span class="th">Address</span>
           </div>
           <c:forEach items="${users}" var="user">
               <form:form class="tr" modelAttribute="userAttribute" action="" method="post">
               <span class="td"><c:out value="${user.id}"/></span>
                    <span class="td"><c:out value="${user.name}"/></span>
                    <span class="td"><c:out value="${user.address}"/></span>
                    <span class="td">
                        <input type="button" onclick="location.href='edit?id=${user.id}';" value="Edit user info"/>
                    </span>
                    <span class="td">
                        <input type="button" onclick="location.href='deleteUser?id=${user.id}';" value="Delete user"/>
                    </span>
                    <span class="td">
                        <input type="button" onclick="location.href='editAccount?id=${user.id}';" value="Edit account"/>
                    </span>
               </form:form>
            </c:forEach>
        </div>
        <input type="button" onclick="location.href='add';" value="Add new user"/>
        <p><a href="<c:url value="/" />">Main Page</a></p>
    </body>
</html>