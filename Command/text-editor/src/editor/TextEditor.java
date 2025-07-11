package editor;

import command.Command;
import command.CommandHistory;
import command.impl.DeleteTextCommand;
import command.impl.InsertTextCommand;

public class TextEditor {

    private final TextDocument textDocument;
    private final CommandHistory commandHistory;

    public TextEditor() {
        textDocument = new TextDocument();
        commandHistory = new CommandHistory();
    }

    public void insert(String word, int pos) {
        InsertTextCommand command = new InsertTextCommand(textDocument, word, pos);
        command.execute();
        commandHistory.pushUndo(command);
    }

    public void delete(int start, int length) {
        DeleteTextCommand command = new DeleteTextCommand(textDocument, start, length);
        command.execute();
        commandHistory.pushUndo(command);
    }

    public void undo() {
        Command command = commandHistory.popUndo();
        if (command != null) {
            command.undo();
            commandHistory.pushRedo(command);
        }
    }

    public void redo() {
        Command command = commandHistory.popRedo();
        if (command != null) {
            command.execute();
            commandHistory.pushUndo(command);
        }
    }

    public void printContent() {
        System.out.println(textDocument.getText());
        System.out.println("=====================================");
    }

}
