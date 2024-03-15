package tudelft.wis.idm_tasks.boardGameTracker;

import java.sql.*;

public class TableCreator {
    private static Connection connection;

    public Connection getConnection()  {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/boardGames", "postgres", "Sarah1809");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return connection;
    }

    public void createDatabase() {
        createPlayer();
        createBoardGame();
        createPlayerToBoardGame();
    }

    public void createPlayer() {
        String setup = "CREATE TABLE IF NOT EXISTS players ( id SERIAL PRIMARY KEY, name VARCHAR(64) NOT NULL, nick_name VARCHAR(64) NOT NULL)";
        PreparedStatement myStmt = null;
        try {
            getConnection();
            myStmt = connection.prepareStatement(setup);
            myStmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createBoardGame() {
        String setup = "CREATE TABLE IF NOT EXISTS board_games ( id SERIAL PRIMARY KEY, name VARCHAR(64) NOT NULL, bggURL VARCHAR(64) NOT NULL)";
        PreparedStatement myStmt = null;
        try {
            getConnection();
            myStmt = connection.prepareStatement(setup);
            myStmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createPlayerToBoardGame() {
        String setup = "CREATE TABLE IF NOT EXISTS playerToBoardGame ( player_id INT NOT NULL, board_game_id INT NOT NULL, "
        + "PRIMARY KEY (player_id, board_game_id), FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE, "
        + "FOREIGN KEY (board_game_id) REFERENCES board_games ON DELETE CASCADE)";
        PreparedStatement myStmt = null;
        try {
            getConnection();
            myStmt = connection.prepareStatement(setup);
            myStmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
