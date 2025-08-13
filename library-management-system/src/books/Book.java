package books;

import system.util.LibraryDate;

import java.util.Date;

public class Book {
    private final String ISBN;
    private final String title;
    private final String author;
    private final LibraryDate publicationDate;
    private final String subject;
    private final BookType bookType;

    public Book(String ISBN, String title,
                String author, Date publicationDate,
                String subject, BookType bookType) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publicationDate = new LibraryDate(publicationDate);
        this.subject = subject;
        this.bookType = bookType;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LibraryDate getPublicationDate() {
        return publicationDate;
    }

    public String getSubject() {
        return subject;
    }

    public BookType getBookType() {
        return this.bookType;
    }
}
