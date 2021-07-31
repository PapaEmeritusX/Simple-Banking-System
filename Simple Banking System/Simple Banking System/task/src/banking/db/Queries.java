package banking.db;

public interface Queries {

    String TABLE_CREATE = ("CREATE TABLE IF NOT EXISTS card (" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " number  TEXT," +
            " pin TEXT," +
            " balance INTEGER DEFAULT 0)");
    String INSERT = ("INSERT INTO CARD " +
            " (number, pin, balance) " +
            " VALUES (?,?,?)");
    String VERIFY = ("SELECT * " +
            " FROM card " +
            " WHERE number = ? " +
            " AND pin = ? ");
    String READ_VALUE = ("SELECT balance " +
            " FROM card" +
            " WHERE number = ?");
    String ADD_INCOME = ("UPDATE card " +
            " SET balance = balance + ?" +
            " WHERE number = ?");
    String SUB_INCOME = ("UPDATE card " +
            " SET balance = balance - ?" +
            " WHERE number = ?");
    String CLOSE = ("DELETE " +
            " FROM card" +
            " WHERE number = ?");
}
