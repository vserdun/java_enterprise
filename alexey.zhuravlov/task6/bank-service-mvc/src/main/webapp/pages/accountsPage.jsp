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
         <span class="th">Title</span>
         <span class="th">Author</span>
         <span class="th">Holder</span>
         <span class="th">Status</span>
         <span class="th">Action</span>
     </div>
 <c:forEach items="${books}" var="book">
     <form:form class="tr" modelAttribute="bookAttribute" action="takeBook" method="post">
         <span class="td"><c:out value="${book.bookId}"/></span>
         <span class="td"><c:out value="${book.bookTitle}"/></span>
         <span class="td"><c:out value="${book.bookAuthor}"/></span>
         <span class="td"><input id="bookHolder" name="bookHolder" type="text" value="${book.bookHolder}"/></span>
         <span class="td"><c:out value="${book.bookStatus}"/></span>
         <span class="td">
             <input id="bookId" name="bookId" type="hidden" value="${book.bookId}"/>
             <input type="submit" id="${book.bookId}"  value="${book.bookStatus eq 'AVAILABLE' ? 'take': 'return'}"/>
         </span>
         <span class="td">
            <input type="button" onclick="location.href='edit?bookId=${book.bookId}';" value="Edit book"/>
         </span>
         <span class="td">
                     <input type="button" onclick="location.href='deleteBook?bookId=${book.bookId}';" value="Delete book"/>
                  </span>
     </form:form>
 </c:forEach>
 </div>
 <input type="button" onclick="location.href='add';" value="Add new book"/>
</body>
</html>