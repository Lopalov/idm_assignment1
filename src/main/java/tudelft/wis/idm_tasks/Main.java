package tudelft.wis.idm_tasks;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Imdb_JDBCManager imdbJdbcManager = new Imdb_JDBCManager();
        Scanner scanner = new Scanner(System.in);

        //Query 1
        System.out.println("Query-1: Please enter a year");
        int year = scanner.nextInt();
        ArrayList<String> titles = new ArrayList<>(imdbJdbcManager.getTitlesPerYear(year));
        for(int i = 0; i < 20; i++)
        {
            System.out.println(titles.get(i));
        }

        //Query 3
        System.out.println("Query-3: Please enter a genre");
        String genre = scanner.next();
        System.out.println(imdbJdbcManager.getAverageRuntimeOfGenre(genre));

        //Query 4
        System.out.println("Query-4: Please enter an actor's name");
        String actor = scanner.next();
        ArrayList<String> playedCharacters = new ArrayList<>(imdbJdbcManager.getPlayedCharacters(actor));
        for(int i = 0; i < 20; i++)
        {
            System.out.println(playedCharacters.get(i));
        }

    }
}
