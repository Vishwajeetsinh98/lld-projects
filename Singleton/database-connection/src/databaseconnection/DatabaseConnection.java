package databaseconnection;

public class DatabaseConnection {

    private static DatabaseConnection INSTANCE;
    private Database database;

    private DatabaseConnection() {
        database = new Database();
        database.connect();
    }

    public static DatabaseConnection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseConnection();
        }
        return INSTANCE;
    }

    public Database getDatabase() {
        return database;
    }
}
