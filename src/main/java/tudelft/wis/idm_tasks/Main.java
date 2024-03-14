package tudelft.wis.idm_tasks;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Imdb_JDBCManager imdbJdbcManager = new Imdb_JDBCManager();
  //      Connection connection = imdbJdbcManager.getConnection();

        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        ArrayList<String> titles = new ArrayList<>(imdbJdbcManager.getTitlesPerYear(year));
        for(int i = 0; i < 20; i++)
        {
            System.out.println(titles.get(i));
        }


    }
}
