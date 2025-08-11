package buttons;

public abstract class Button {
    protected boolean isPressed;
    public void pressDown() {
        this.isPressed = true;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public void reset() {
        this.isPressed = false;
    }
}
