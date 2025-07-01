package factories.impl;

import factories.CloudProviderFactory;
import products.database.Database;
import products.database.impl.AzureDB;
import products.storagebucket.StorageBucket;
import products.storagebucket.impl.AzureStorage;
import products.vm.VM;
import products.vm.impl.AzureVM;

public class AzureCloudFactory implements CloudProviderFactory {
    @Override
    public Database createDatabase() {
        return new AzureDB();
    }

    @Override
    public StorageBucket createStorageBucket() {
        return new AzureStorage();
    }

    @Override
    public VM createVM() {
        return new AzureVM();
    }
}
