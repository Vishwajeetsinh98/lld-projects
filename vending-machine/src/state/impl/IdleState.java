package state.impl;

import machine.VendingMachine;
import state.State;

public class IdleState extends State {
    public IdleState(VendingMachine machine) {
        super(machine);
        machine.setMoney(0.0);
        machine.setSnackToDispense(null);
    }

    @Override
    public void getMoney(double money) {
        machine.setMoney(money);
        machine.changeState(new HasMoneyState(machine));
    }

    @Override
    public void chooseSnack(String snackName) {
        System.out.println("[IdleState] Enter money first!");
    }

    @Override
    public void dispense() {
        System.out.println("[IdleState] Enter money and choose snack first!");
    }

    @Override
    public void refill() {
        machine.refillMachine();
    }

    public String toString() {return "IdleState";}

}
