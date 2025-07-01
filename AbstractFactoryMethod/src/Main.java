import factories.CloudProviderFactory;
import factories.impl.AWSCloudFactory;
import factories.impl.AzureCloudFactory;
import factories.impl.GCPCloudFactory;

public class Main {
    public static void main(String[] args) {
        CloudProviderFactory factory = new GCPCloudFactory();
        factory.createDatabase().storeData();
        factory.createVM().runCode();
        factory.createStorageBucket().storeFiles();
    }
}