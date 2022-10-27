package Library_System.Mapper;

import Library_System.Domain.Author;
import Library_System.Domain.Category;
import Library_System.Domain.Item;
import Library_System.Utils.DBConnPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * I have decided to create a separate mapper class for the item_author table to move some
 * responsibility out of AuthorMapper class. This could increase the cohesion of
 * mapper classes as AuthorMapper could purely focus on author table while
 * ItemAuthorMpaaer will focus entirely on item_author table.
 **/
public class ItemAuthorMapper{

    private DBConnPool dbConnPool = DBConnPool.getInstance();

    private String createStatement(){
        return "INSERT INTO item_author (item_id, author_id) VALUES (?, ?)";
    }

    private String readAuthorsStatement() {
        return  "SELECT * FROM item_author WHERE item_id = ?";
    }

    private String readItemsStatement() {
        return  "SELECT * FROM item_author WHERE author_id = ?";
    }


    /**
     * Retrieve all authors from a given item
     **/
    public ArrayList<Author> readAuthors(String itemId) {

        String sql = readAuthorsStatement();
        ArrayList<Author> result = new ArrayList<Author>();

        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, itemId);
            sqlStatement.execute();

            rs = sqlStatement.getResultSet();
            while(rs.next()) {
                result.add(loadAuthor(rs));
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
     * Retrieve all items from a given author
     **/
    public ArrayList<Item> readItems(String authorId) {

        String sql = readItemsStatement();
        ArrayList<Item> result = new ArrayList<Item>();

        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, authorId);
            sqlStatement.execute();

            rs = sqlStatement.getResultSet();
            while(rs.next()) {
                result.add(loadItem(rs));
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
     * Add the author to the item
     **/
    public void create(String itemId, String authorId) {

        String sql = createStatement();
        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, itemId);
            sqlStatement.setString(2, authorId);

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
     * Retrieve the ResultSet from the database query and load it into the corresponding object (for author)
     **/
    private Author loadAuthor(ResultSet rs) throws SQLException{
        AuthorMapper authorMapper = new AuthorMapper();
        String id = rs.getString("author_id");
        return authorMapper.readOne(id);
    }

    /**
     * Retrieve the ResultSet from the database query and load it into the corresponding object (for item)
     **/
    private Item loadItem(ResultSet rs) throws SQLException{
        ItemMapper itemMapper = new ItemMapper();
        String id = rs.getString("item_id");
        return itemMapper.readOne(id);
    }
}
