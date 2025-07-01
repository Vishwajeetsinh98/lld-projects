package products.database.impl;

import products.database.Database;

public class GCPDB implements Database {
    @Override
    public void storeData() {
        System.out.println("[GCP] Storing data");
    }
}
