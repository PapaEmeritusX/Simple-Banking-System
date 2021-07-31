package banking.db;

import org.sqlite.SQLiteDataSource;
import java.sql.*;

public class DataBase implements Queries {
    private String url;
    private static SQLiteDataSource dataSource;

    public DataBase(String url, SQLiteDataSource dataSource) {
        this.url = url;
        this.dataSource = dataSource;
        createDataBase();
    }

    public void createDataBase() {
        dataSource.setUrl(this.url);

        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement()) {

            statement.executeUpdate(TABLE_CREATE);
        } catch (SQLException throwable) { throwable.printStackTrace();}
    }

    public static void insert(String cardNumber, String pin, int balance) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT)) {

            stmt.setString(1, cardNumber);
            stmt.setString(2, pin);
            stmt.setInt(3, balance);

            stmt.executeUpdate();

        } catch (SQLException throwable) { throwable.printStackTrace();}
    }
    public static boolean verifyAccount(String inputCardNumber, String inputPIN) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(VERIFY)) {
            stmt.setString(1, inputCardNumber);
            stmt.setString(2, inputPIN);
            try (ResultSet resultSets = stmt.executeQuery()) {
                return resultSets.next();
            } catch (SQLException throwable) { throwable.printStackTrace();}
        } catch (SQLException throwable) { throwable.printStackTrace();}

        return false;
    }
    public static boolean exists(String transferCardNumber) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(READ_VALUE)) {
            stmt.setString(1, transferCardNumber);
            try (ResultSet resultSets = stmt.executeQuery()) {
                return resultSets.next();
            } catch (SQLException throwable) { throwable.printStackTrace();}
        } catch (SQLException throwable) { throwable.printStackTrace();}

        return false;
    }


    public static int read(String cardNumber) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(READ_VALUE)) {

            stmt.setString(1, cardNumber);
            try (ResultSet resultSet = stmt.executeQuery() ) {
             if (resultSet.next()) {
                 return resultSet.getInt("balance");
             }
         } catch (SQLException throwable) { throwable.printStackTrace();}
        } catch (SQLException throwable) { throwable.printStackTrace();}

        return 0;
    }

    public static int addBalance(String cardNumber, int money) {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(ADD_INCOME)) {
            stmt.setInt(1, money);
            stmt.setString(2,cardNumber);

            return stmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }

    public static void closeAccount(String inputCardNumber) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CLOSE)) {
            stmt.setString(1, inputCardNumber);
            stmt.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    public static void makeTransaction(String inputCardNumber,
                                       String transferCardNumber,
                                       int money) {

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement subTransfer = conn.prepareStatement(SUB_INCOME);
                 PreparedStatement addTransfer = conn.prepareStatement(ADD_INCOME)) {

                subTransfer.setInt(1, money);
                subTransfer.setString(2, inputCardNumber);
                subTransfer.executeUpdate();

                addTransfer.setInt(1, money);
                addTransfer.setString(2, transferCardNumber);
                addTransfer.executeUpdate();

                conn.commit();
            } catch (SQLException throwable) { throwable.printStackTrace();}
        } catch (SQLException throwable) { throwable.printStackTrace();}
    }
}
