package Library_System.Domain;

import java.sql.Timestamp;

public class BookCopy extends Copy {

    private Book book;

    //load a new book copy from existing data (database)
    public BookCopy(String id, Timestamp createDate, Book book) {
        super(id, createDate);
        this.book = book;
    }

    //create a book copy
    public BookCopy(Book book) {
        super(new Timestamp(System.currentTimeMillis()));
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
