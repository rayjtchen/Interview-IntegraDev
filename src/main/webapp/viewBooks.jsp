<%@ page import="Library_System.Domain.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Library_System.Domain.Item" %>
<%@ page import="Library_System.Domain.Author" %><%--
  Created by IntelliJ IDEA.
  User: raychen
  Date: 8/11/2022
  Time: 9:47 pm
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
    <% Item item = (Item) request.getAttribute("item"); %>
    <% ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books"); %>

    <h1>Item Details</h1>
    <table style="width: 50%">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Type</th>
            <th>Discription</th>
            <th>Author(s)</th>
            <th>Category</th>
        </tr>

        <tr>
            <td><%=item.getId()%></td>
            <td><%=item.getTitle()%></td>
            <td><%=item.getType()%></td>
            <td><%=item.getDescription()%></td>
            <td>
                <%
                    for (Author author: item.getAuthors()) {
                %>
                <ul>
                    <li><%=author.getName()%></li>
                </ul>
                <%
                    }
                %>
            </td>
            <td><%=item.getCategory().getName()%></td>
        </tr>
    </table>

    <h1>View Books</h1>
    <table style="width: 50%">
        <tr>
            <th>Book ID</th>
            <th>ISBN</th>
            <th>Version</th>
            <th>Publisher</th>
            <th>View</th>
        </tr>

        <%
            for (Book book: books) {
        %>
        <tr>
            <td><%=book.getBook_id()%></td>
            <td><%=book.getIsbn()%></td>
            <td><%=book.getVersion()%></td>
            <td><%=book.getPublisher().getName()%></td>
            <td>
                <a href="${pageContext.request.contextPath}/copy?type=<%=book.getType()%>&id=<%=book.getId()%>">
                    <button>view</button>
                </a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
