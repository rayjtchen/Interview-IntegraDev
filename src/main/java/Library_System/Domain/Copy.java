package Library_System.Domain;

import java.sql.Timestamp;
import java.util.UUID;

public abstract class Copy extends DomainObject{

    private Timestamp createDate;

    //load a new Copy from existing data (database)
    public Copy(String id, Timestamp createDate) {
        super(id);

        this.createDate = createDate;
    }

    //create a new copy
    protected Copy(Timestamp createDate) {
        super(UUID.randomUUID().toString());
        this.createDate = createDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
