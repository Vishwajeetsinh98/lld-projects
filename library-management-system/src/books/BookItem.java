package books;

import catalog.Catalog;
import library.Rack;
import system.util.LibraryDate;

import java.util.Date;

public class BookItem {
    private final String id;
    private final Book book;
    private BookStatus bookStatus;
    private LibraryDate borrowedDate;
    private LibraryDate dueDate;
    private Rack rack;

    public BookItem(Book book, Rack rack) {
        this.book = book;
        this.id = book.getISBN() + "_" + (Catalog.getInstance().getISBNCount(book.getISBN()) + 1);
        this.bookStatus = BookStatus.AVAILABLE;
        this.borrowedDate = null;
        this.dueDate = null;
        this.rack = rack;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = new LibraryDate(borrowedDate);
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = new LibraryDate(dueDate);
    }

    public String getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public LibraryDate getBorrowedDate() {
        return borrowedDate;
    }

    public LibraryDate getDueDate() {
        return dueDate;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }
}
