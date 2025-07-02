import databaseconnection.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        // Fails:
        // DatabaseConnection conn = new DatabaseConnection();

        DatabaseConnection conn = DatabaseConnection.getInstance();
        System.out.println(conn.getDatabase().query("test query 1"));
        DatabaseConnection conn1 = DatabaseConnection.getInstance();
        System.out.println(conn1.getDatabase().query("test query 2"));
        System.out.println(conn == conn1);
    }
}