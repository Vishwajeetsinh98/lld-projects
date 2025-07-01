package products.storagebucket.impl;

import products.storagebucket.StorageBucket;

public class AzureStorage implements StorageBucket {
    @Override
    public void storeFiles () {
        System.out.println("[AZURE] Storing files");
    }
}
