import facade.HomeAutomationSystem;

public class Main {
    public static void main(String[] args) {
        HomeAutomationSystem homeAutomationSystem = new HomeAutomationSystem();
        homeAutomationSystem.turnOn("Lights");
        homeAutomationSystem.turnOn("Thermostat");

        homeAutomationSystem.leavingHome();
        homeAutomationSystem.comingHome();
        homeAutomationSystem.comingHome();

        homeAutomationSystem.changeLightsColor("RED");
        homeAutomationSystem.adjustThermostatTemp(25.1);

        // Should turn off RED lights
        homeAutomationSystem.leavingHome();
        homeAutomationSystem.leavingHome();
    }
}