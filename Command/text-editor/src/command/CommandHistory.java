package command;

import java.util.Stack;

public class CommandHistory {
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    public CommandHistory() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void pushUndo(Command c) {
        undoStack.push(c);
        redoStack.clear();
    }
    public Command popUndo() {
        if (undoStack.isEmpty()) return null;
        return undoStack.pop();
    }
    public boolean hasUndo() {
        return !undoStack.isEmpty();
    }

    public void pushRedo(Command c) {
        redoStack.push(c);
    }
    public Command popRedo() {
        if (redoStack.isEmpty()) return null;
        return redoStack.pop();
    }
    public boolean hasRedo () {
        return !redoStack.isEmpty();
    }

}
