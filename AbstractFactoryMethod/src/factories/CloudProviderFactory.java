package factories;

import products.database.Database;
import products.storagebucket.StorageBucket;
import products.vm.VM;

public interface CloudProviderFactory {
    public Database createDatabase();
    public StorageBucket createStorageBucket();
    public VM createVM();
}
