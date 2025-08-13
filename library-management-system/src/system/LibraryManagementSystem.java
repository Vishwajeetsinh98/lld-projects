package system;

import books.Book;
import books.BookItem;
import books.BookStatus;
import catalog.Catalog;
import users.Member;
import users.User;

import java.util.*;

public class LibraryManagementSystem {

    private final Map<String, User> users;
    private final Map<String, List<String>> borrowings;
    private final Map<String, User> reservations;
    private LibraryManagementSystem() {
        users = new HashMap<>();
        borrowings = new HashMap<>();
        reservations = new HashMap<>();
    }

    public void issueBook(String userLibraryCard, String key) {
        // key could be ISBN of book or BookItem id
        List<BookItem> bookItems = Catalog.getInstance().findByBook(book);
        BookItem assigned = null;
        for (BookItem bookItem : bookItems) {
            if (bookItem.getBookStatus() == BookStatus.AVAILABLE) {
                assigned = bookItem;
                break;
            }
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
        BookItem bookItem = Catalog.getInstance().getBookItemById(bookItemId);
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
            // Notify user.
        } else {
            bookItem.setBookStatus(BookStatus.AVAILABLE);
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


    // Singleton:
    private static class Holder {
        private static final LibraryManagementSystem INSTANCE = new LibraryManagementSystem();
    }
    public static LibraryManagementSystem getInstance() {
        return Holder.INSTANCE;
    }
}
