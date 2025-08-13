package books;

import library.Rack;

import java.util.Date;

public class BookFactory {

    public static Book createBook(String ISBN, String title,
                                  String author, Date publicationDate,
                                  String subject, BookType bookType) {
        return new Book(ISBN, title, author, publicationDate, subject, bookType);
    }

    public static BookItem createBookItemForBook(Book book, Rack rack) {
        return new BookItem(book, rack);
    }
}
