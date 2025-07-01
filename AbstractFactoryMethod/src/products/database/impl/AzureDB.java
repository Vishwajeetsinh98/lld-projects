package products.database.impl;

import products.database.Database;

public class AzureDB implements Database {
    @Override
    public void storeData() {
        System.out.println("[AZURE] Storing data");
    }
}
