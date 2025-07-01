package products.vm.impl;

import products.vm.VM;

public class AzureVM implements VM {
    @Override
    public void runCode() {
        System.out.println("[AZURE] Running code");
    }
}
