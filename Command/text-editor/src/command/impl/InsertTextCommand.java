package command.impl;

import command.Command;
import editor.TextDocument;
import editor.TextEditor;

public class InsertTextCommand extends Command {
    private final int textPos;
    private final String newText;
    public InsertTextCommand(TextDocument receiver, String newText, int textPos) {
        super(receiver);
        this.newText = newText;
        this.textPos = textPos;
    }

    @Override
    public void execute() {
        saveState();
        receiver.addText(newText, textPos);
    }
}
