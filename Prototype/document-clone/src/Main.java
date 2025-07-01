import documenttypes.Document;
import documenttypes.SpreadsheetDocument;
import documenttypes.TextDocument;

public class Main {
    public static void main(String[] args) {
        TextDocument textDoc = new TextDocument("/var/tmp/file.txt", "Some test text");
        System.out.println(textDoc);
        TextDocument newDoc = textDoc.clone();
        System.out.println(newDoc);

        SpreadsheetDocument spreadsheetDoc = new SpreadsheetDocument("/var/tmp/sheet.txt", new String[]{"sheet1", "sheet2"});
        SpreadsheetDocument newSpreadsheetDoc = spreadsheetDoc.clone();
        System.out.println(spreadsheetDoc);
        System.out.println(newSpreadsheetDoc);
    }
}