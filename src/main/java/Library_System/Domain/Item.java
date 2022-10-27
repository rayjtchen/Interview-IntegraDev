package Library_System.Domain;

import Library_System.Utils.Type;

import java.util.ArrayList;
import java.util.UUID;

public class Item extends DomainObject {

    private String title;
    private Type type;
    private String description;
    private Category category;
    private ArrayList<Author> authors;

    //load a new Item from existing data (database)
    public Item(String id, String title, String type, String description, Category category, ArrayList<Author> authors) {
        super(id);
        this.title = title;
        this.type = Type.valueOf(type);
        this.description = description;
        this.category = category;
        this.authors = authors;
    }

    //create a new Item
    public Item(String title, String type, String description, Category category) {
        super(UUID.randomUUID().toString());
        this.title = title;
        this.type = Type.valueOf(type);
        this.description = description;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }
}
