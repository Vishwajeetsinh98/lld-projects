## LLD Question — Cloud Service Provisioning System
### Problem Statement:

You are building a Cloud Service Provisioning System that supports multiple cloud providers like AWS, Azure, and Google Cloud Platform (GCP). The system should provision resources such as:

- Virtual Machines (VMs)
- Storage Buckets
- Databases

Each cloud provider has its own implementation details for these resources, but from the client's perspective, they should interact only with abstract interfaces.

**Requirements:**

You need to design a system where:
- There are abstract interfaces for VM, Storage, and Database.
- Each cloud provider has its own concrete implementations for these.
- An Abstract Factory provides a way to get the correct set of resource implementations based on the cloud provider.

The system should be designed so that:
- Adding a new cloud provider (e.g., Oracle Cloud) is easy and doesn't require changes in client code.
- The client code only depends on abstract interfaces, not on concrete implementations.
- At runtime, based on configuration, the correct factory is provided to the client.

**Deliverables:**
- Define the product interfaces for VM, Storage, and Database.
- Define concrete product classes like AWSVM, AzureVM, GCPVM, etc.
- Design the Abstract Factory (e.g., CloudResourceFactory) and concrete factories like AWSFactory, AzureFactory, GCPFactory.
- Show how the client code can provision resources for any cloud provider without knowing implementation details.