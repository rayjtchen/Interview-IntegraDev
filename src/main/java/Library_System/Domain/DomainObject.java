package Library_System.Domain;

public abstract class DomainObject {

    private final String id;

    public DomainObject(String id) {
        this.id = id;
    }

    public String getId() {return id;}
}
