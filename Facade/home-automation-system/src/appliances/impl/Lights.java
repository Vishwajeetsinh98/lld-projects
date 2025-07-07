package appliances.impl;

import appliances.Appliance;

public class Lights extends Appliance {
    private String color = "Yellow";

    @Override
    public void turnOn() {
        super.turnOn();
        System.out.println("[" + color + " Lights] On");
    }

    @Override
    public void turnOff() {
        super.turnOff();
        System.out.println("[" + color + " Lights] Off");
    }

    public void changeColor(String color) {
        this.color = color;
        System.out.println("[Lights] Color changed to " + color);
    }
}
