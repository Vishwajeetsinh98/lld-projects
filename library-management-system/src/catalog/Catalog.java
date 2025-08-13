package catalog;

import books.Book;
import books.BookItem;
import system.util.LibraryDate;

import java.util.*;

public class Catalog {

    private final Map<String, Book> books;
    private final Map<String, BookItem> bookItems;

    private Catalog() {
        books = new HashMap<>();
        bookItems = new HashMap<>();
    }

    public void addBook(Book book) {
        if (books.containsKey(book.getISBN())) {
            System.out.println("[Catalog] Book already exists with same ISBN");
            return;
        }
        books.put(book.getISBN(), book);
    }

    public Book getBookByISBN(String key) {
        return books.getOrDefault(key, null);
    }

    public void addBookItem(BookItem bookItem) {
        if (bookItems.containsKey(bookItem.getId())) {
            System.out.println("[Catalog] BookItem already exists with this id");
            return;
        }
        bookItems.put(bookItem.getId(), bookItem);
    }

    public BookItem getBookItemById(String key) {
        return bookItems.getOrDefault(key, null);
    }


    public boolean isbnExists(String ISBN) {
        return books.containsKey(ISBN);
    }

    public List<BookItem> findByBook(Book book) {
        List<BookItem> ret = new ArrayList<>();
        for (BookItem bookItem : bookItems.values()) {
            if (bookItem.getBook().equals(book))
                ret.add(bookItem);
        }
        return ret;
    }

    public List<BookItem> findByISBN(String ISBN) {
        List<BookItem> ret = new ArrayList<>();
        for (BookItem bookItem : bookItems.values()) {
            if (bookItem.getBook().getISBN().equals(ISBN))
                ret.add(bookItem);
        }
        return ret;
    }

    public int getISBNCount(String ISBN) {
        return findByISBN(ISBN).size();
    }

    public List<BookItem> findByAuthor(String author) {
        List<BookItem> ret = new ArrayList<>();
        for (BookItem bookItem : bookItems.values()) {
            if (bookItem.getBook().getAuthor().equals(author))
                ret.add(bookItem);
        }
        return ret;
    }

    public List<BookItem> findByTitle(String title) {
        List<BookItem> ret = new ArrayList<>();
        for (BookItem bookItem : bookItems.values()) {
            if (bookItem.getBook().getTitle().equals(title))
                ret.add(bookItem);
        }
        return ret;
    }

    public List<BookItem> findBySubject(String subject) {
        List<BookItem> ret = new ArrayList<>();
        for (BookItem bookItem : bookItems.values()) {
            if (bookItem.getBook().getSubject().equals(subject))
                ret.add(bookItem);
        }
        return ret;
    }

    public List<BookItem> findByPublicationDate(Date publicationDate) {
        List<BookItem> ret = new ArrayList<>();
        if (publicationDate == null) return ret;
        LibraryDate search = new LibraryDate(publicationDate);
        for (BookItem bookItem : bookItems.values()) {
            if (bookItem.getBook().getPublicationDate().equals(search))
                ret.add(bookItem);
        }
        return ret;
    }

    // Singleton
    private static class Holder {
        private static final Catalog INSTANCE = new Catalog();
    }
    public static Catalog getInstance() {
        return Holder.INSTANCE;
    }
}
