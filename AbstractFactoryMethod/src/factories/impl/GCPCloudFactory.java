package factories.impl;

import factories.CloudProviderFactory;
import products.database.Database;
import products.database.impl.GCPDB;
import products.storagebucket.StorageBucket;
import products.storagebucket.impl.GCPStorage;
import products.vm.VM;
import products.vm.impl.GCPVM;

public class GCPCloudFactory implements CloudProviderFactory {
    @Override
    public Database createDatabase() {
        return new GCPDB();
    }

    @Override
    public StorageBucket createStorageBucket() {
        return new GCPStorage();
    }

    @Override
    public VM createVM() {
        return new GCPVM();
    }
}
