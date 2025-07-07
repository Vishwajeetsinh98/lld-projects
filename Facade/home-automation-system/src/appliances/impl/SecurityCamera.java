package appliances.impl;

import appliances.Appliance;

public class SecurityCamera extends Appliance {
    @Override
    public void turnOn() {
        super.turnOn();
        System.out.println("[SecurityCamera] On");
    }

    @Override
    public void turnOff() {
        super.turnOff();
        System.out.println("[SecurityCamera] Off");
    }
}
