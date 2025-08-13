package users;

import books.*;
import catalog.Catalog;
import library.Rack;
import system.LibraryManagementSystem;

import java.util.Date;

public class Librarian extends User {
    private final Catalog catalog;
    private final LibraryManagementSystem libraryManagementSystem;
    public Librarian(String name) {
        super(name);
        catalog = Catalog.getInstance();
        libraryManagementSystem = LibraryManagementSystem.getInstance();
    }

    public void addBook(String ISBN, String title,
                        String author, Date publicationDate,
                        String subject, BookType bookType) {
        Book newBook = BookFactory.createBook(ISBN, title, author,
                                              publicationDate, subject,
                                              bookType);
        catalog.addBook(newBook);
    }

    public void createRack(String identifier) {
        libraryManagementSystem.createRack(identifier);
    }

    public void addBookItem(Book book, Rack rack) {
        BookItem newBookItem = BookFactory.createBookItemForBook(book, rack);
        catalog.addBookItem(newBookItem);
    }

    public void updateRackForItem(String bookItemId, String rackId) {
        BookItem bookItem = catalog.getBookItemById(bookItemId);
        Rack rack = libraryManagementSystem.getRack(rackId);
        if (bookItem != null && rack != null) {
            bookItem.setRack(rack);
            System.out.println("[Librarian] Successfully updated rack for BookItem");
        } else {
            System.out.println("[Librarian] BookItem or Rack null");
        }
    }
}
