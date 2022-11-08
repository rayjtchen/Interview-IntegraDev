package Library_System.Controller;

import Library_System.Domain.Author;
import Library_System.Domain.Book;
import Library_System.Domain.Item;
import Library_System.Mapper.AuthorMapper;
import Library_System.Mapper.BookMapper;
import Library_System.Mapper.ItemMapper;
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

                    request.setAttribute("item", item);
                    request.setAttribute("books", books);
                    request.setAttribute("authors", authors);
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
}
