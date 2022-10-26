package Library_System.Mapper;

import java.util.ArrayList;

public abstract class Mapper<T> {
    public abstract ArrayList<T> readList();
    public abstract T readOne(String id);
    public abstract void create(T newDocument);
    //public abstract void update(T updatedDocument);
}
