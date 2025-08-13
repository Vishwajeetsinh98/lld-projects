package users;

import books.BookItem;
import catalog.Catalog;
import library.LibraryCard;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class User {
    protected final LibraryCard libraryCard;
    protected final String name;

    public User(String name) {
        this.libraryCard = new LibraryCard();
        this.name = name;
    }

    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public String getName() {
        return name;
    }

    public void searchByKey(String keyName, String value) {
        List<BookItem> result;
        switch (keyName.toLowerCase()) {
            case "isbn":
                result = Catalog.getInstance().findByISBN(value);
                break;
            case "title":
                result = Catalog.getInstance().findByTitle(value);
                break;
            case "author":
                result = Catalog.getInstance().findByAuthor(value);
                break;
            case "subject":
                result = Catalog.getInstance().findBySubject(value);
                break;
            case "publication date":
                DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                Date date = null;
                try {
                    date = formatter.parse(value);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                result = Catalog.getInstance().findByPublicationDate(date);
        }
    }

    public void getNotification(String message) {
        System.out.println("[User] " + name + " libraryCard: " + libraryCard + " got message:\n" + message);
    }
}
