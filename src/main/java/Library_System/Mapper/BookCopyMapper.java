package Library_System.Mapper;

import Library_System.Domain.*;
import Library_System.Utils.DBConnPool;

import java.sql.*;
import java.util.ArrayList;

public class BookCopyMapper extends Mapper<BookCopy>{

    private DBConnPool dbConnPool = DBConnPool.getInstance();

    private String createStatement(){
        return "INSERT INTO book_copy (id, create_date, book_id) VALUES (?, ?, ?)";
    }

    private String readOneStatement() {
        return  "SELECT * FROM book_copy WHERE  id = ?";
    }

    private String readListStatement() {
        return  "SELECT * FROM book_copy";
    }

    private String readListWithBookIdStatement() {
        return  "SELECT * FROM book_copy WHERE book_id = ?";
    }


    /**
     * Retrieve all the book copies from the item book_copy table
     **/
    @Override
    public ArrayList<BookCopy> readList() {

        String sql = readListStatement();
        ArrayList<BookCopy> result = new ArrayList<BookCopy>();

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
     * Find and retrieve a particular book copy from the book_copy table based on the book copy id
     **/
    @Override
    public BookCopy readOne(String id) {

        String sql = readOneStatement();
        BookCopy result = null;

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
     * insert the book copy to the book_copy table
     **/
    @Override
    public void create(BookCopy bookCopy) {

        String sql = createStatement();
        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, bookCopy.getId());
            sqlStatement.setTimestamp(2, bookCopy.getCreateDate());
            sqlStatement.setString(3, bookCopy.getBook().getBook_id());

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

    @Override
    public void update(BookCopy updatedDocument) {
        return;
    }

    /**
     * Given the book_id, retrieve all the book copies from the book_copy table
     **/
    public ArrayList<BookCopy> readListWithBookId(String id) {

        String sql = readListWithBookIdStatement();

        ArrayList<BookCopy> result = new ArrayList<BookCopy>();

        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, id);
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
     * Retrieve the ResultSet from the database query and load it into the corresponding object
     **/
    public BookCopy load(ResultSet rs) throws SQLException {
        BookMapper bookMapper = new BookMapper();

        String id = rs.getString("id");
        Timestamp creatDate = rs.getTimestamp("create_date");
        String bookId = rs.getString("book_id");

        Book book = bookMapper.readOne(bookId);

        return new BookCopy(id, creatDate, book);
    }
}