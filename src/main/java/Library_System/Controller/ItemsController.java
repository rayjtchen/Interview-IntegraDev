package Library_System.Controller;

import Library_System.Domain.Category;
import Library_System.Domain.Item;
import Library_System.Mapper.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Items Page", value = "/items")
public class ItemsController extends HttpServlet{

    private ItemMapper itemMapper = new ItemMapper();
    private CategoryMapper categoryMapper = new CategoryMapper();

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        //Pre setup
        RequestDispatcher rd;

        //get params
        String page = request.getParameter("page");

        try {
            switch (page) {
                case "viewItems":

                    ArrayList<Item> items = itemMapper.readList();
                    ArrayList<Category> categories = categoryMapper.readList();

                    request.setAttribute("items", items);
                    request.setAttribute("categories", categories);
                    rd=request.getRequestDispatcher("viewItems.jsp");
                    rd.include(request, response);

                    break;

                default:
                    throw new ServletException("404 page not found");


            }
        }catch(Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            rd=request.getRequestDispatcher("error.jsp");
            rd.include(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //Request setup
        RequestDispatcher rd;

        //Post setup
        String postType = request.getParameter("postType");

        try {
            switch(postType) {
                case "createItem":

                    String title = request.getParameter("title");
                    String type = request.getParameter("type");
                    String description = request.getParameter("description");
                    String categoryId = request.getParameter("categoryId");

                    //input validation
                    if (title == "" || categoryId == "" || type == "") throw new IllegalArgumentException("input error");

                    Category category = categoryMapper.readOne(categoryId);
                    Item item = new Item(title, type, description, category);

                    DataMapper.create(item);

                    response.sendRedirect(request.getContextPath() + "/items?page=viewItems");

                    break;

                case "addAuthor":

                    ItemAuthorMapper itemAuthorMapper = new ItemAuthorMapper();

                    String itemId = request.getParameter("itemId");
                    String authorId = request.getParameter("authorId");
                    type = request.getParameter("type");

                    itemAuthorMapper.create(itemId, authorId);

                    response.sendRedirect(request.getContextPath() + "/" + type + "?page=viewItem&id=" + itemId);

                    break;

                default:
                    throw new ServletException("404 resource not found");
            }

        }catch(Exception e) {
            request.setAttribute("errorMessage", e.getMessage().);
            rd=request.getRequestDispatcher("error.jsp");
            rd.include(request, response);
        }
    }







}
