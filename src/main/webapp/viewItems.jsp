<%@ page import="Library_System.Domain.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Library_System.Domain.Author" %>
<%@ page import="Library_System.Domain.Category" %><%--
  Created by IntelliJ IDEA.
  User: raychen
  Date: 8/11/2022
  Time: 12:17 pm
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
    <h1>View all the items</h1>
    <table style="width: 50%">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Type</th>
            <th>Discription</th>
            <th>Author(s)</th>
            <th>Category</th>
            <th>View</th>
        </tr>

        <!-- items -->
        <% ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("items"); %>
        <%
            for (Item item: items) {
        %>
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
                <td>
                    <a href="${pageContext.request.contextPath}/<%=item.getType()%>?page=viewItem&id=<%=item.getId()%>">
                        <button>view</button>
                    </a>
                </td>
            </tr>
        <%
            }
        %>
    </table>

    <h1>Create item</h1>

    <form action="items" method="post" name="createItem">
        <table style="width: 20%">
            <tr>
                <td>Title</td>
                <td><input type="text" name="title" /></td>
            </tr>
            <tr>
                <td>Type</td>
                <td><select name="type">
                    <option value="book">Book</option>
                </select>
                </td>
            </tr>

            <tr>
                <td>Description</td>
                <td><textarea name="description" rows="10" cols="70"></textarea></td>
            </tr>

            <tr>
                <td>Category</td>
                <td>
                    <select name="categoryId">
                        <% ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories"); %>
                        <%
                            for (Category category: categories) {
                        %>

                        <option value= <%=category.getId()%> ><%=category.getName()%></option>

                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>

        </table>
        <input name="postType" value="createItem" type="hidden">
        <input type="submit" value="Submit" />
    </form>

</body>
</html>
