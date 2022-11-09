<%@ page import="Library_System.Domain.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Library_System.Domain.Item" %>
<%@ page import="Library_System.Domain.Author" %>
<%@ page import="Library_System.Domain.Publisher" %><%--
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

    <h1>Book Item Details</h1>
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

    <h1>Add author for Book Item</h1>
    <form action="items" method="post" name="addAuthor">
        <table style="width: 20%">
            <tr>
                <td>Author</td>
                <td>
                    <select name="authorId">
                        <% ArrayList<Author> authors = (ArrayList<Author>) request.getAttribute("authors"); %>
                        <%
                            for (Author author: authors) {
                        %>

                        <option value= <%=author.getId()%> ><%=author.getName()%></option>

                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
        </table>
        <input name="itemId" value=<%=item.getId()%> type="hidden">
        <input name="type" value=<%=item.getType()%> type="hidden">
        <input name="postType" value="addAuthor" type="hidden">
        <input type="submit" value="Submit" />
    </form>

    <h1>Corresponding Books</h1>
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
                <a href="${pageContext.request.contextPath}/bookCopy?page=viewCopies&id=<%=book.getBook_id()%>">
                    <button>view</button>
                </a>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <h1>Create Book</h1>
    <form action="book" method="post" name="createBook">
        <table style="width: 20%">
            <tr>
                <td>ISBN</td>
                <td><input type="text" name="isbn" /></td>
            </tr>
            <tr>
                <td>Version</td>
                <td><input type="text" name="version" /></td>
            </tr>
            <tr>
                <td>Publisher</td>
                <td>
                    <select name="publisherId">
                        <% ArrayList<Publisher> publishers = (ArrayList<Publisher>) request.getAttribute("publishers"); %>
                        <%
                            for (Publisher publisher: publishers) {
                        %>

                        <option value= <%=publisher.getId()%> ><%=publisher.getName()%></option>

                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
        </table>

        <input name="itemId" value=<%=item.getId()%> type="hidden">
        <input name="postType" value="createBook" type="hidden">
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
