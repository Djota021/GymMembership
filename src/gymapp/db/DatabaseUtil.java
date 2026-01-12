package gymapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseUtil {

    private static final String DB_URL = "jdbc:sqlite:gym.db";

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS members (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "first_name TEXT," +
                    "last_name TEXT," +
                    "gender TEXT," +
                    "membership_type TEXT," +
                    "months INTEGER," +
                    "total_price INTEGER," +
                    "birth_date TEXT," +
                    "note TEXT" +
                    ")";

            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL);
    }
}
