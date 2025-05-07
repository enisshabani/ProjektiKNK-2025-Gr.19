package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    public static Connection databaseLink;

    public static Connection getDatabaseLink() {
        String databaseName = "KNK";
        String databaseUser = "Grupi 22";
        String databasePassword = "mirdita123";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
