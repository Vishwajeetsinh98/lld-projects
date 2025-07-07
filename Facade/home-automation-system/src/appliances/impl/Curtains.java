package appliances.impl;

import appliances.Appliance;

public class Curtains extends Appliance {
    @Override
    public void turnOn() {
        super.turnOn();
        System.out.println("[Curtains] Opened");
    }

    @Override
    public void turnOff() {
        super.turnOff();
        System.out.println("[Curtains] Closed");
    }
}
