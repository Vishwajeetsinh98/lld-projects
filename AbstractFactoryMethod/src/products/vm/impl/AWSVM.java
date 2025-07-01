package products.vm.impl;

import products.vm.VM;

public class AWSVM implements VM {
    @Override
    public void runCode() {
        System.out.println("[AWS] Running code");
    }
}
