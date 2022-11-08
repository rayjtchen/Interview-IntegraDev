package Library_System.Mapper;

import Library_System.Domain.Author;
import Library_System.Domain.Publisher;
import Library_System.Utils.DBConnPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PublisherMapper extends Mapper<Publisher> {

    private DBConnPool dbConnPool = DBConnPool.getInstance();

    private String createStatement(){
        return "INSERT INTO publisher (id, name) VALUES (?, ?)";
    }

    private String readOneStatement() {
        return  "SELECT * FROM publisher WHERE id = ?";
    }

    private String readListStatement() {
        return  "SELECT * FROM publisher";
    }

    /**
     * Retrieve all the publishers from the publisher table
     **/
    @Override
    public ArrayList<Publisher> readList() {

        String sql = readListStatement();
        ArrayList<Publisher> result = new ArrayList<Publisher>();

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
     * Find and retrieve the publisher from the publisher table where publisher.id = input id
     **/
    @Override
    public Publisher readOne(String id) {

        String sql = readOneStatement();
        Publisher result = null;

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
    * Due to time limitations, the following create method does not prevent duplicated publisher names.
     **/
    @Override
    public void create(Publisher publisher) {

        String sql = createStatement();
        Connection conn = dbConnPool.getConnection();
        PreparedStatement sqlStatement = null;
        ResultSet rs = null;

        try {
            sqlStatement = conn.prepareStatement(sql);
            sqlStatement.setString(1, publisher.getId());
            sqlStatement.setString(2, publisher.getName());

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
    public void update(Publisher updatedDocument) {
        return;
    }

    /**
     * Retrieve the ResultSet from the database query and load it into the corresponding object
     **/
    public Publisher load(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String name = rs.getString("name");
        return new Publisher(id, name);
    }
}
