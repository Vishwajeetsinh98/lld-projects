package appliances.impl;

import appliances.Appliance;

public class DoorLock extends Appliance {
    @Override
    public void turnOn() {
        super.turnOn();
        System.out.println("[DoorLock] Locked");
    }

    @Override
    public void turnOff() {
        super.turnOff();
        System.out.println("[DoorLock] Unlocked");
    }
}
