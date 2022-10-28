package Library_System.Domain;

import java.util.ArrayList;
import java.util.UUID;

public class Book extends Item{

    private String book_id;
    private String isbn;
    private Integer version;
    private Publisher publisher;

    //load a new Item from existing data (database)
    public Book(Item item, String book_id, String isbn, Integer version, Publisher publisher) {
        super(item.getId(), item.getTitle(), item.getType().toString(), item.getDescription(), item.getCategory(), item.getAuthors());
        this.book_id = book_id;
        this.isbn = isbn;
        this.version = version;
        this.publisher = publisher;
    }

    //create a new Item
    public Book(Item item, String isbn, Integer version, Publisher publisher) {
        super(item.getId(), item.getTitle(), item.getType().toString(), item.getDescription(), item.getCategory(), item.getAuthors());
        this.book_id = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.version = version;
        this.publisher = publisher;
    }

    public String getBook_id() {
        return book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
