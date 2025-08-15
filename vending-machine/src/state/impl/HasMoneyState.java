package state.impl;

import machine.VendingMachine;
import snack.Snack;
import state.State;

import java.util.Map;

public class HasMoneyState extends State {
    public HasMoneyState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void getMoney(double money) {
        System.out.println("[HasMoneyState] Already have money: " + machine.getMoney() + ", adding this extra amount as well");
        machine.setMoney(machine.getMoney() + money);
    }

    @Override
    public void chooseSnack(String snackName) {
        Map<Snack, Integer> snackCount = machine.getSnackCount();
        for (Snack snack : snackCount.keySet()) {
            if (snack.getName().equalsIgnoreCase(snackName)) {
                if (machine.getMoney() >= snack.getPrice()) {
                    if (snackCount.get(snack) > 0) {
                        machine.setSnackToDispense(snack);
                        machine.setMoney(machine.getMoney() - snack.getPrice());
                        System.out.println("[HasMoneyState] Selected snack: " + snack.getName());
                        machine.changeState(new DispensingState(machine));
                    } else {
                        machine.changeState(new OutOfStockState(machine));
                    }
                    return;
                } else {
                    System.out.println("[HasMoneyState] Insufficient money or snack inventory over! Returning your money and resetting..");
                    machine.returnMoney();
                    machine.changeState(new IdleState(machine));
                    return;
                }
            }
        }
    }

    @Override
    public void dispense() {
        System.out.println("[HasMoneyState] Can only select a snack not dispense it right now.");
    }

    @Override
    public void refill() {
        System.out.println("[HasMoneyState] Refilling can only be done in IdleState");
    }

    public String toString() {return "HasMoneyState";}

}
