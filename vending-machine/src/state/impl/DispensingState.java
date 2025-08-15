package state.impl;

import machine.VendingMachine;
import state.State;

public class DispensingState extends State {
    public DispensingState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void getMoney(double money) {
        System.out.println("[DispensingState] Cannot add money when dispensing");
    }

    @Override
    public void chooseSnack(String snackName) {
        System.out.println("[DispensingState] Cannot choose snack when dispensing");
    }

    @Override
    public void dispense() {
        machine.dispenseSnack();
        machine.returnMoney();
        machine.changeState(new IdleState(machine));
    }

    @Override
    public void refill() {
        System.out.println("[DispensingState] Cannot refill when dispensing");
    }

    public String toString() {return "DispensingState";}

}
