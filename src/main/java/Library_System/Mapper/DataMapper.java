package Library_System.Mapper;

import Library_System.Domain.*;
import Library_System.Utils.Type;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A Mapper class to manage create and update functions without the need to specified with mapper class to use.
 * This could be use for unit of work in the future.
 **/
public class DataMapper {

    public static <T extends Mapper<U>, U extends DomainObject> T getMapper(Class<U> c ) throws IllegalArgumentException {
        if (c == Item.class) return (T) new ItemMapper();
        else if (c == Author.class) return (T) new AuthorMapper();
        else if (c == Book.class) return (T) new BookMapper();
        else if (c == BookCopy.class) return (T) new BookCopyMapper();
        else if (c == Category.class) return (T) new CategoryMapper();
        else if (c == Publisher.class) return (T) new PublisherMapper();
        throw new IllegalArgumentException("Mapper class not found");
    }

    public static <T extends DomainObject> void create(T obj) throws SQLException {
        Mapper<T> mapper = getMapper((Class<T>) obj.getClass());
        mapper.create(obj);
    }

    public static <T extends DomainObject> void update(T obj) throws SQLException {
        Mapper<T> mapper = getMapper((Class<T>) obj.getClass());
        mapper.update(obj);
    }
}
