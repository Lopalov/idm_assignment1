package tudelft.wis.idm_tasks;

import tudelft.wis.idm_tasks.basicJDBC.interfaces.JDBCTask2Interface;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Imdb_JDBCManager implements JDBCTask2Interface {
    private static Connection connection;

    @Override
    public Connection getConnection()  {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/imdb", "postgres", "Sarah1809");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        };

        return connection;
    }

    @Override
    public Collection<String> getTitlesPerYear(int year)
    {
        String query = "SELECT primary_title FROM titles WHERE start_year = ?";
        PreparedStatement myStmt = null;
        try {
            getConnection();
            myStmt = connection.prepareStatement(query);
            myStmt.setInt(1, year);
            ResultSet myRs = null;
            myRs = myStmt.executeQuery();

            Collection<String> titles = new ArrayList<String>();
            while (myRs.next()) {
                String title = myRs.getString("primary_title");
                titles.add(title);
            }

            return titles;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<String> getJobCategoriesFromTitles(String searchString)
    {
        String query = "SELECT DISTINCT ci.job_category FROM cast_info ci " +
                "JOIN titles AS t ON t.title_id = ci.title_id " +
                "WHERE t.primary_title LIKE CONCAT('%', ?, '%')";
        PreparedStatement myStmt = null;
        try {
            getConnection();
            myStmt = connection.prepareStatement(query);
            myStmt.setString(1, searchString);
            ResultSet myRs = null;
            myRs = myStmt.executeQuery();

            Collection<String> jobs = new ArrayList<String>();
            while (myRs.next()) {
                String job = myRs.getString("job_category");
                jobs.add(job);
            }

            return jobs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public double getAverageRuntimeOfGenre(String genre)
    {

        String query = "SELECT AVG(t.runtime) AS avg_runtime FROM titles_genres AS tg JOIN titles AS t ON t.title_id = tg.title_id WHERE tg.genre = ? GROUP BY tg.genre";
        PreparedStatement myStmt = null;
        try {
            getConnection();
            myStmt = connection.prepareStatement(query);
            myStmt.setString(1, genre);
            ResultSet myRs = null;
            myRs = myStmt.executeQuery();
            myRs.next();
            double avg_rating = myRs.getDouble("avg_runtime");
            return avg_rating;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Collection<String> getPlayedCharacters(String actorFullname)
    {
        String query = "SELECT DISTINCT tpc.character_name \n" +
                "FROM persons AS p\n" +
                "JOIN title_person_character AS tpc ON tpc.person_id = p.person_id\n" +
                "WHERE p.full_name = ?";

        PreparedStatement myStmt = null;
        try {
            getConnection();
            myStmt = connection.prepareStatement(query);
            myStmt.setString(1, actorFullname);
            ResultSet myRs = null;
            myRs = myStmt.executeQuery();

            Collection<String> playedCharacters = new ArrayList<String>();
            while (myRs.next()) {
                String character = myRs.getString("character_name");
                playedCharacters.add(character);
            }

            return playedCharacters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
