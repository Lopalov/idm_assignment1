package tudelft.wis.idm_tasks;

import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCManager;
import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCTask2Interface;

import java.sql.*;
import java.util.Collection;

public class Imdb_JDBCManager implements JDBCTask2Interface {
    private static Connection connection;

    @Override
    public Connection getConnection()  {
        if (connection == null) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:duckdb:./DB/bggt.duckdb");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        };
        return connection;
    }

    @Override
    public Collection<String> getTitlesPerYear(int year)
    {
        String query = "SELECT primary_titles FROM titles WHERE start_year = ?";
        PreparedStatement myStmt = null;
        try {
            myStmt = connection.prepareStatement(query);
            myStmt.setInt(1, 1999);
            ResultSet myRs = null;
            myRs = myStmt.executeQuery();
            return (Collection<String>) myRs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<String> getJobCategoriesFromTitles(String searchString)
    {
        return null;
    }

    public double getAverageRuntimeOfGenre(String genre)
    {
        return 0;
    }

    public Collection<String> getPlayedCharacters(String actorFullname)
    {
        return null;
    }
}
