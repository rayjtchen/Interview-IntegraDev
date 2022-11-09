<%@ page import="Library_System.Domain.Book" %>
<%@ page import="Library_System.Domain.Author" %>
<%@ page import="Library_System.Domain.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Library_System.Domain.BookCopy" %><%--
  Created by IntelliJ IDEA.
  User: raychen
  Date: 9/11/2022
  Time: 4:07 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    table, th, td {
        border:1px solid black;
    }
</style>
<head>
    <title>Library System</title>
</head>
<body>
    <% Book book = (Book) request.getAttribute("book"); %>
    <% ArrayList<BookCopy> bookCopies = (ArrayList<BookCopy>) request.getAttribute("bookCopies"); %>

    <h1>Book Details</h1>
    <table style="width: 50%">
        <tr>
            <th>Item ID</th>
            <th>Title</th>
            <th>Type</th>
            <th>Discription</th>
            <th>Author(s)</th>
            <th>Category</th>
            <th>Book ID</th>
            <th>ISBN</th>
            <th>Version</th>
            <th>Publisher</th>
        </tr>

        <tr>
            <td><%=book.getId()%></td>
            <td><%=book.getTitle()%></td>
            <td><%=book.getType()%></td>
            <td><%=book.getDescription()%></td>
            <td>
                <%
                    for (Author author: book.getAuthors()) {
                %>
                <ul>
                    <li><%=author.getName()%></li>
                </ul>
                <%
                    }
                %>
            </td>
            <td><%=book.getCategory().getName()%></td>
            <td><%=book.getBook_id()%></td>
            <td><%=book.getIsbn()%></td>
            <td><%=book.getVersion()%></td>
            <td><%=book.getPublisher().getName()%></td>
        </tr>
    </table>
    <h1>Book Copies</h1>
    <table style="width: 50%">
        <tr>
            <th>Book Copy ID</th>
            <th>Create Date</th>
        </tr>

            <%
                for (BookCopy bookCopy: bookCopies) {
            %>
                <tr>
                    <td><%=bookCopy.getId()%></td>
                    <td><%=bookCopy.getCreateDate()%></td>
                </tr>
            <%
                }
            %>
    </table>


</body>
</html>
