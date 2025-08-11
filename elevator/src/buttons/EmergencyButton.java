package buttons;

public class EmergencyButton extends Button {
    @Override
    public void pressDown(){
        System.out.println("[EmergencyButton] pressed");
        super.pressDown();
    }
}
