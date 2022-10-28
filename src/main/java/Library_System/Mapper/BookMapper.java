package Library_System.Mapper;

import Library_System.Domain.*;
import Library_System.Utils.DBConnPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookMapper extends Mapper<Book> {

    private DBConnPool dbConnPool = DBConnPool.getInstance();

    private String createStatement(){
        return "INSERT INTO book (id, isbn, version, item_id, publisher_id) VALUES (?, ?, ?, ?, ?)";
    }

    private String readOneStatement() {
        return  "SELECT * FROM book WHERE id = ?";
    }

    private String readListStatement() {
        return  "SELECT * FROM book";
    }


    /**
     * Retrieve all the books from the book table
     **/
    @Override
    public ArrayList<Book> readList() {

        String sql = readListStatement();
        ArrayList<Book> result = new ArrayList<Book>();

        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.execute();

            rs = sqlStatement.getResultSet();
            while(rs.next()) {
                result.add(load(rs));
            }

        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sqlStatement != null) {
                    sqlStatement.close();
                }
                if (conn != null) {
                    dbConnPool.releaseConnection(conn);
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }


    /**
     * Find and retrieve a particular book from the book table based on the book id
     **/
    @Override
    public Book readOne(String id) {

        String sql = readOneStatement();
        Book result = null;

        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, id);
            sqlStatement.execute();

            rs = sqlStatement.getResultSet();
            if (rs.next()) {
                result = load(rs);
            }

        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sqlStatement != null) {
                    sqlStatement.close();
                }
                if (conn != null) {
                    dbConnPool.releaseConnection(conn);
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * insert the book to the database
     **/
    @Override
    public void create(Book book) {

        String sql = createStatement();
        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, book.getBook_id());
            sqlStatement.setString(2, book.getIsbn());
            sqlStatement.setInt(3, book.getVersion());
            sqlStatement.setString(4, book.getId());
            sqlStatement.setString(5, book.getPublisher().getId());

            if(sqlStatement.executeUpdate() > 0) {
                //placeholder: do something if needed
            }

        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sqlStatement != null) {
                    sqlStatement.close();
                }
                if (conn != null) {
                    dbConnPool.releaseConnection(conn);
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Retrieve the ResultSet from the database query and load it into the corresponding object
     **/
    public Book load(ResultSet rs) throws SQLException {

        ItemMapper itemAuthorMapper = new ItemMapper();
        PublisherMapper publisherMapper = new PublisherMapper();

        String book_id = rs.getString("id");
        String title = rs.getString("isbn");
        Integer version = rs.getInt("version");
        String item_id = rs.getString("item_id");
        String publisher_id = rs.getString("publisher_id");

        Item item = itemAuthorMapper.readOne(item_id);
        Publisher publisher = publisherMapper.readOne(publisher_id);

        return new Book(item, book_id, title, version, publisher);
    }
}
