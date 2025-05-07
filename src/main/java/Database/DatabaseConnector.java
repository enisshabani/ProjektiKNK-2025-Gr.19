package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    public static Connection databaseLink;

    public static Connection getDatabaseLink() {
        String databaseName = "JavaFX";
        String databaseUser = "Grupi 22";
        String databasePassword = "mirdita123";
        String url = "jdbc:postgresql://localhost:5432/" + databaseName;

        try {
            Class.forName("org.postgresql.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
