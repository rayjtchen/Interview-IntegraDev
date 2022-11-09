package Library_System.Controller;

import Library_System.Domain.*;
import Library_System.Mapper.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "Book Copy Page", value = "/bookCopy")
public class BookCopyController extends HttpServlet {

    private BookMapper bookMapper = new BookMapper();
    private BookCopyMapper bookCopyMapper = new BookCopyMapper();

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //Pre setup
        RequestDispatcher rd;

        //get params
        String page = request.getParameter("page");

        try{

            switch (page) {

                //View all the book copies for a particular book
                case "viewCopies":

                    String id = request.getParameter("id");

                    Book book = bookMapper.readOne(id);

                    //input validation check
                    if(book == null) throw new IllegalArgumentException("404 Resource not found");

                    ArrayList<BookCopy> bookCopies = bookCopyMapper.readListWithBookId(id);

                    request.setAttribute("bookCopies", bookCopies);
                    request.setAttribute("book", book);

                    rd=request.getRequestDispatcher("viewBookCopies.jsp");
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
                case "createBookCopy":

                    String bookId = request.getParameter("bookId");

                    Book book = bookMapper.readOne(bookId);

                    BookCopy bookCopy = new BookCopy(book);

                    DataMapper.create(bookCopy);

                    response.sendRedirect(request.getContextPath() + "/bookCopy?page=viewCopies&id=" + bookId);

                    break;

                default:
                    throw new ServletException("404 resource not found");
            }

        }catch(Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            rd=request.getRequestDispatcher("error.jsp");
            rd.include(request, response);
        }
    }






}
