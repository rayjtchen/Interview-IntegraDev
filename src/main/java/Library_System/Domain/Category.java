package Library_System.Domain;

import java.util.UUID;

public class Category extends DomainObject {

    private String name;

    //load a new category from existing data (database)
    public Category(String id, String name){
        super(id);
        this.name = name;
    }

    //create a new category
    public Category(String name){
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
