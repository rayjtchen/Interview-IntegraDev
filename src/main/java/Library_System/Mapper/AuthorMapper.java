package Library_System.Mapper;

import Library_System.Domain.Author;
import Library_System.Domain.Category;
import Library_System.Utils.DBConnPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorMapper extends Mapper<Author> {

    private DBConnPool dbConnPool = DBConnPool.getInstance();

    private String createStatement(){
        return "INSERT INTO author (id, name) VALUES (?, ?)";
    }

    private String readOneStatement() {
        return  "SELECT * FROM author WHERE id = ?";
    }

    private String readListStatement() {
        return  "SELECT * FROM author";
    }

    /**
     * Retrieve all the authors from the author table
     **/
    @Override
    public ArrayList<Author> readList() {

        String sql = readListStatement();
        ArrayList<Author> result = new ArrayList<Author>();

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
     * Find and retrieve the author from the author table where author.id = input id
     **/
    @Override
    public Author readOne(String id) {

        String sql = readOneStatement();
        Author result = null;

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
    * Due to time limitations, the following create method does not prevent duplicated author names.
    * In fact, the author table should have contained more attributes (e.g., first name, last name...)
    * than it is right now.
     **/
    @Override
    public void create(Author author) {

        String sql = createStatement();
        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, author.getId());
            sqlStatement.setString(2, author.getName());

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
    public Author load(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String name = rs.getString("name");
        return new Author(id, name);
    }
}
