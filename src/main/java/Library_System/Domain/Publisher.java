package Library_System.Domain;

import java.util.UUID;

public class Publisher extends DomainObject {

    private String name;

    //load a new publisher from existing data (database)
    public Publisher(String id, String name){
        super(id);
        this.name = name;
    }

    //create a new publisher
    public Publisher(String name){
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
