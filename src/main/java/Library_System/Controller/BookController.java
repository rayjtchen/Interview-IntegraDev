package Library_System.Controller;

import Library_System.Domain.*;
import Library_System.Mapper.*;
import Library_System.Utils.Type;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Book Page", value = "/book")
public class BookController extends HttpServlet {

    private ItemMapper itemMapper = new ItemMapper();
    private AuthorMapper authorMapper = new AuthorMapper();
    private BookMapper bookMapper = new BookMapper();
    private PublisherMapper publisherMapper = new PublisherMapper();

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //Pre setup
        RequestDispatcher rd;

        //get params
        String page = request.getParameter("page");

        try{

            switch (page) {

                //View all the books for a particular item
                case "viewItem":

                    String id = request.getParameter("id");

                    Item item = itemMapper.readOne(id);

                    ArrayList<Book> books = bookMapper.readListWithItemId(id);

                    ArrayList<Author> authors = authorMapper.readList();

                    ArrayList<Publisher> publishers = publisherMapper.readList();

                    request.setAttribute("item", item);
                    request.setAttribute("books", books);
                    request.setAttribute("authors", authors);
                    request.setAttribute("publishers", publishers);

                    rd=request.getRequestDispatcher("viewBooks.jsp");
                    rd.include(request, response);

                    break;

                default:
                    throw new ServletException("404 page not found");
            }

        }catch (Exception e){
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
                case "createBook":

                    String isbn = request.getParameter("isbn");
                    Integer version = Integer.valueOf(request.getParameter("version"));
                    String publisherId = request.getParameter("publisherId");
                    String itemId = request.getParameter("itemId");

                    //input validation
                    if (isbn == "" || (isbn.length() != 13 && isbn.length() != 10)) throw new IllegalArgumentException("please enter a valid isbn number");
                    if (publisherId == "") throw new IllegalArgumentException("input publisherId error");
                    if (itemId == "") throw new IllegalArgumentException("input itemId error");

                    Item item = itemMapper.readOne(itemId);
                    Publisher publisher = publisherMapper.readOne(publisherId);

                    Book book = new Book(item, isbn, version, publisher);
                    DataMapper.create(book);

                    response.sendRedirect(request.getContextPath() + "/" + item.getType() + "?page=viewItem&id=" + itemId);

                    break;

                default:
                    throw new ServletException("404 resource not found");
        }

        }catch(NumberFormatException e) {
            request.setAttribute("errorMessage", "please enter a valid version number");
            rd=request.getRequestDispatcher("error.jsp");
            rd.include(request, response);

        }catch(Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            rd=request.getRequestDispatcher("error.jsp");
            rd.include(request, response);
        }
    }
}
