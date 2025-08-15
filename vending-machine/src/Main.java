import machine.VendingMachine;
import machine.VendingMachineInterface;

public class Main {
    public static void main(String[] args) {
        VendingMachineInterface machine = new VendingMachine();
        machine.enterMoney(1.5);
        machine.selectSnack("Apple");
        machine.giveSnack();

        machine.enterMoney(1.5);
        machine.selectSnack("Apple");
        machine.giveSnack();
    }
}