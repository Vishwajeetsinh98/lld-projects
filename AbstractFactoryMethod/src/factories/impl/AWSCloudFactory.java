package factories.impl;

import factories.CloudProviderFactory;
import products.database.Database;
import products.database.impl.AWSDB;
import products.storagebucket.StorageBucket;
import products.storagebucket.impl.AWSStorage;
import products.vm.VM;
import products.vm.impl.AWSVM;

public class AWSCloudFactory implements CloudProviderFactory {

    @Override
    public Database createDatabase() {
        return new AWSDB();
    }

    @Override
    public StorageBucket createStorageBucket() {
        return new AWSStorage();
    }

    @Override
    public VM createVM() {
        return new AWSVM();
    }
}
