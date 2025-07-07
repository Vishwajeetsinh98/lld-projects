package facade;

import appliances.Appliance;
import appliances.impl.*;

import java.util.HashMap;
import java.util.Map;

public class HomeAutomationSystem {

    private final Map<String, Appliance> appliancesMap;
    private boolean isHome;

    public HomeAutomationSystem() {
        appliancesMap = new HashMap<>();
        appliancesMap.put("Lights", new Lights());
        appliancesMap.put("Thermostat", new Thermostat());
        appliancesMap.put("DoorLock", new DoorLock());
        appliancesMap.put("Curtains", new Curtains());
        appliancesMap.put("SecurityCamera", new SecurityCamera());
        isHome = true;
    }

    public void turnOn(String appliance) {
        appliancesMap.get(appliance).turnOn();
    }

    public void turnOn(String [] appliances) {
        for (String appliance : appliances) {
            turnOn(appliance);
        }
    }

    public void turnOff(String appliance) {
        appliancesMap.get(appliance).turnOff();
    }

    public void turnOff(String[] appliances) {
        for (String appliance : appliances) {
            turnOff(appliance);
        }
    }

    public void leavingHome() {
        String [] toTurnOff = {"Lights", "Curtains", "Thermostat"};
        String [] toTurnOn = {"SecurityCamera", "DoorLock"};

        System.out.println("==================== Leaving Home ====================");
        if (!isHome) {
            System.out.println("You are already outside, but running the commands again for safety");
        }

        turnOn(toTurnOn);
        turnOff(toTurnOff);
        System.out.println("Have a great day!");
        isHome = false;
    }

    public void comingHome() {

        if (isHome) {
            System.out.println("Already home.");
            return;
        }

        String [] toTurnOn = {"Lights", "Curtains", "Thermostat"};
        String [] toTurnOff = {"DoorLock"};
        System.out.println("==================== Coming Home ====================");
        turnOn(toTurnOn);
        turnOff(toTurnOff);
        System.out.println("Welcome home!");
        isHome = true;
    }

    public void changeLightsColor(String color) {
        ((Lights) appliancesMap.get("Lights")).changeColor(color);
    }

    public void adjustThermostatTemp(double temp) {
        ((Thermostat) appliancesMap.get("Thermostat")).setTemperature(temp);
    }
}
