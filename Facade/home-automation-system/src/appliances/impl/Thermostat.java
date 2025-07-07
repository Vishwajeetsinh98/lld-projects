package appliances.impl;

import appliances.Appliance;

public class Thermostat extends Appliance {

    private double temperature = 21;

    @Override
    public void turnOn() {
        super.turnOn();
        System.out.println("[Thermostat] On");
    }

    @Override
    public void turnOff() {
        super.turnOff();
        System.out.println("[Thermostat] Off");
    }

    public double getTemperature() {
        System.out.println("[Thermostat] Temperature: " + temperature);
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        System.out.println("[Thermostat] Set Temperature: " + temperature);
    }
}
