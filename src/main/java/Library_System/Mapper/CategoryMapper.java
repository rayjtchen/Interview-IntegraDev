package Library_System.Mapper;

import Library_System.Domain.Category;
import Library_System.Utils.DBConnPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryMapper extends Mapper<Category>{

    private DBConnPool dbConnPool = DBConnPool.getInstance();

    private String createStatement(){
        return "INSERT INTO category (id, name) VALUES (?, ?)";
    }

    private String readOneStatement() {
        return  "SELECT * FROM category WHERE  id = ?";
    }

    private String readListStatement() {
        return  "SELECT * FROM category";
    }

    /**
     * Retrieve all the categories from the category table
     **/
    @Override
    public ArrayList<Category> readList(){

        String sql = readListStatement();
        ArrayList<Category> result = new ArrayList<Category>();

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
     * Find and retrieve the category from the category table where category.id = input id
     **/
    @Override
    public Category readOne(String id){

        String sql = readOneStatement();
        Category result = null;

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
     * Due to time limitations, the following create method does not prevent duplicated category names.
     **/
    @Override
    public void create(Category category){

        String sql = createStatement();
        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, category.getId());
            sqlStatement.setString(2, category.getName());

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
    public void update(Category updatedDocument) {
        return;
    }

    /**
     * Retrieve the ResultSet from the database query and load it into the corresponding object
     **/
    public Category load(ResultSet rs) throws SQLException{
        String id = rs.getString("id");
        String name = rs.getString("name");
        return new Category(id, name);
    }
}
