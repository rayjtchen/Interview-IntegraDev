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

public class ItemMapper extends Mapper<Item>{

    private DBConnPool dbConnPool = DBConnPool.getInstance();
    private ItemAuthorMapper itemAuthorMapper = new ItemAuthorMapper();
    private CategoryMapper  categoryMapper = new CategoryMapper();

    private String createStatement(){
        return "INSERT INTO item (id, title, type, description, category_id) VALUES (?, ?, ?, ?, ?)";
    }

    private String readOneStatement() {
        return  "SELECT * FROM item WHERE  id = ?";
    }

    private String readListStatement() {
        return  "SELECT * FROM item";
    }

    /**
     * Retrieve all the items from the item table
     **/
    @Override
    public ArrayList<Item> readList() {

        String sql = readListStatement();
        ArrayList<Item> result = new ArrayList<Item>();

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
     * Find and retrieve a particular item from the item table based on the item id
     **/
    @Override
    public Item readOne(String id) {

        String sql = readOneStatement();
        Item result = null;

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
     * insert the item to the database
     **/
    @Override
    public void create(Item item) {

        String sql = createStatement();
        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, item.getId());
            sqlStatement.setString(2, item.getTitle());
            sqlStatement.setString(3, item.getType().toString());
            sqlStatement.setString(4, item.getDescription());
            sqlStatement.setString(5, item.getCategory().getId());

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
    public void update(Item updatedDocument) {
        return;
    }

    /**
     * Retrieve the ResultSet from the database query and load it into the corresponding object
     **/
    public Item load(ResultSet rs) throws SQLException{
        String id = rs.getString("id");
        String title = rs.getString("title");
        String type = rs.getString("type");
        String description = rs.getString("description");
        String categoryId = rs.getString("category_id");
        Category category = categoryMapper.readOne(categoryId);
        ArrayList<Author> authors = itemAuthorMapper.readAuthors(id);

        return new Item(id, title, type, description, category, authors);
    }
}
