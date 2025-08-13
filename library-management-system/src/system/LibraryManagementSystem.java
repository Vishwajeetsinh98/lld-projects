package system;

import books.Book;
import books.BookItem;
import books.BookStatus;
import catalog.Catalog;
import library.Rack;
import system.notification.NotificationService;
import users.Member;
import users.User;

import java.util.*;

public class LibraryManagementSystem {

    private final Map<String, User> users;
    private final Map<String, List<String>> borrowings;
    private final Map<String, String> reservations;
    private final Map<String, Rack> racks;
    private final Catalog catalog;
    private LibraryManagementSystem() {
        users = new HashMap<>();
        borrowings = new HashMap<>();
        reservations = new HashMap<>();
        racks = new HashMap<>();
        catalog = Catalog.getInstance();
    }

    public void issueBook(String userLibraryCard, String key) {
        // key could be ISBN of book or BookItem id
        BookItem assigned = null;
        Book book;
        User user = users.getOrDefault(userLibraryCard, null);
        if (catalog.isbnExists(key)) {
            book = catalog.getBookByISBN(key);
            List<BookItem> bookItems = catalog.findByBook(book);
            for (BookItem bookItem : bookItems) {
                if (bookItem.getBookStatus() == BookStatus.AVAILABLE) {
                    assigned = bookItem;
                    break;
                } else if (bookItem.getBookStatus() == BookStatus.RESERVED &&
                            reservations.get(bookItem.getId()).equals(userLibraryCard)) {
                    assigned = bookItem;
                    break;
                }
            }
        } else {
            assigned = catalog.getBookItemById(key);
            book = assigned != null ? assigned.getBook() : null;
            if (assigned != null) {
                if ((assigned.getBookStatus() == BookStatus.RESERVED &&
                        !reservations.get(assigned.getId()).equals(userLibraryCard))
                            || assigned.getBookStatus() != BookStatus.AVAILABLE) {
                    assigned = null;
                }
            }
        }

        if (book == null || user == null) {
            System.out.println("User/Book not found!");
            return;
        }

        if (assigned == null) {
            System.out.println("[LMS] All copies for book " + book.getTitle() + " are reserved/lost/issued. Please reserve it.");
        } else {
            System.out.println("[LMS] Assigning book item: " + assigned.getId() + " to " + user.getName());
            if (((Member)user).getNumberOfBooksBorrowed() == 10) {
                System.out.println("[LMS] User already has max books borrowed! Return a book to issue more.");
                return;
            }
            assigned.setBorrowedDate(new Date());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 15);
            assigned.setDueDate(c.getTime());
            assigned.setBookStatus(BookStatus.BORROWED);
            ((Member) user).incrementNumberOfBooksBorrowed();
            updateBorrowing(user, assigned);
        }
    }

    public void returnBook(String userLibraryCard, String bookItemId) {
        User user = users.get(userLibraryCard);
        BookItem bookItem = catalog.getBookItemById(bookItemId);
        if (user == null || bookItem == null)
            throw new RuntimeException("User or BookItem null!");

        if (!borrowings.getOrDefault(userLibraryCard, new ArrayList<>()).contains(bookItemId)) {
            System.out.println("[LMS] User " + user.getName() + " card  num: " + user.getLibraryCard() + " didn't borrow " + bookItem.getBook().getTitle());
            return;
        }

        double fine = calculateFine(bookItem);
        if (fine != 0.0) {
            System.out.println("[LMS] Fine of " + fine + " to be paid");
            // Emulate payment
        }

        borrowings.get(userLibraryCard).remove(bookItemId);
        ((Member) user).decrementNumberOfBooksBorrowed();
        System.out.println("[LMS] Book successfully returned by user");

        if (reservations.containsKey(bookItemId)) {
            User userToNotify = users.get(reservations.get(bookItemId));
            NotificationService.getInstance().sendMessage(userToNotify, "Your requested book: " + bookItem.getBook().getTitle() + " is now available.");
            bookItem.setBookStatus(BookStatus.RESERVED);
        } else {
            bookItem.setBookStatus(BookStatus.AVAILABLE);
        }
    }

    public void reserveBook(String userLibraryCard, String bookItemId) {
        if (reservations.containsKey(bookItemId)) {
            System.out.println("[LMS] Book already reserved!");
        } else {
            reservations.put(bookItemId, userLibraryCard);
            System.out.println("[LMS] Book successfully reserved for user.");
            if (catalog.getBookItemById(bookItemId) != null &&
                    catalog.getBookItemById(bookItemId).getBookStatus() != BookStatus.BORROWED) {
                catalog.getBookItemById(bookItemId).setBookStatus(BookStatus.RESERVED);
            }
        }
    }

    private double calculateFine(BookItem bookItem) {
        int daysLate = bookItem.getDueDate().differenceBetween(bookItem.getBorrowedDate());
        if (daysLate <= 0) return 0.0;
        return daysLate * 5;
    }

    public void addUser(User user) {
        if (!users.containsKey(user.getLibraryCard().toString()))
            users.put(user.getLibraryCard().toString(), user);
    }

    public void updateBorrowing(User user, BookItem bookItem) {
        if (!borrowings.containsKey(user.getLibraryCard().toString()))
            borrowings.put(user.getLibraryCard().toString(), new ArrayList<>());
        borrowings.get(user.getLibraryCard().toString()).add(bookItem.getId());
    }

    public void createRack(String identifier) {
        if (racks.containsKey(identifier)) {
            System.out.println("[LMS] Rack already exists!");
            return;
        }
        racks.put(identifier, new Rack(identifier));
    }

    public Rack getRack(String identifier) {
        return racks.getOrDefault(identifier, null);
    }

    // Singleton:
    private static class Holder {
        private static final LibraryManagementSystem INSTANCE = new LibraryManagementSystem();
    }
    public static LibraryManagementSystem getInstance() {
        return Holder.INSTANCE;
    }
}
