package products.database.impl;

import products.database.Database;

public class AWSDB implements Database {
    @Override
    public void storeData() {
        System.out.println("[AWS] Storing data");
    }
}
