package products.storagebucket.impl;

import products.storagebucket.StorageBucket;

public class AWSStorage implements StorageBucket {
    @Override
    public void storeFiles() {
        System.out.println("[AWS] Storing files");
    }
}
