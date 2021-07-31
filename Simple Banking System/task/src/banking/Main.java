package banking;


import banking.db.DataBase;
import org.sqlite.SQLiteDataSource;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SQLiteDataSource dataSource = new SQLiteDataSource();
        if (args[0].equals("-fileName")) {
            String url = "jdbc:sqlite:" + args[1];
            new DataBase(url, dataSource);
            UserTextInterface UI = new UserTextInterface(scanner);
            UI.start();


        } else {
            System.out.println("Failed to set URL for a database! \nBye!!!");
        }

//
    }

}
