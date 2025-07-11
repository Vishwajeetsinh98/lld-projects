package command;

import editor.TextDocument;

public abstract class Command {
    protected final TextDocument receiver;
    protected String backup;

    public Command(TextDocument receiver) {
        this.receiver = receiver;
    }

    public void saveState() {
        this.backup = this.receiver.getText();
    }

    public void undo() {
        this.receiver.setText(backup);
    }

    public abstract void execute();

}
