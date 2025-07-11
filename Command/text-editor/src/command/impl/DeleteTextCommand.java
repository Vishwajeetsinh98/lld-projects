package command.impl;

import command.Command;
import editor.TextDocument;
import editor.TextEditor;

public class DeleteTextCommand extends Command {

    private final int indexToDelete;
    private final int lengthToDelete;

    public DeleteTextCommand(TextDocument receiver, int indexToDelete, int lengthToDelete) {
        super(receiver);
        this.indexToDelete = indexToDelete;
        this.lengthToDelete = lengthToDelete;
    }

    @Override
    public void execute() {
        saveState();
        receiver.deleteText(indexToDelete, lengthToDelete);
    }
}
