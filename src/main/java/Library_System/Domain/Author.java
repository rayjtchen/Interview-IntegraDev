package Library_System.Domain;

import java.util.UUID;

public class Author extends DomainObject {

    private String name;

    //load a new author from existing data (database)
    public Author(String id, String name){
        super(id);
        this.name = name;
    }

    //create a new author
    public Author(String name){
        super(UUID.randomUUID().toString());
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
