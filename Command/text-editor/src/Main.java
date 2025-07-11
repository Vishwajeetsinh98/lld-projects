import editor.TextEditor;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.insert("Hello", 0);
        editor.insert(" world!", 5);

        editor.printContent();

        editor.delete(5, 6);
        editor.printContent();
        editor.undo();
        editor.printContent();
        editor.redo();
        editor.printContent();
    }
}