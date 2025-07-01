package products.storagebucket.impl;

import products.storagebucket.StorageBucket;

public class GCPStorage implements StorageBucket {
    @Override
    public void storeFiles() {
        System.out.println("[GCP] Storing files");
    }
}
