package databaseconnection;

public class Database {

    public void connect() {
        System.out.println("DB connection established");
    }

    public String query (String query) {
        return "Returning response for " + query + " -> 200";
    }
}
