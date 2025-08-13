package system.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LibraryDate {

    private final Date date;
    private final String formattedDate;

    public LibraryDate(Date date) {
        this.date = date;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        this.formattedDate = formatter.format(this.date);
    }

    public Date getDate() {
        return date;
    }

    public String toString() {
        return formattedDate;
    }

    public int differenceBetween(LibraryDate date2) {
        long diffInMillis = date.getTime() - date2.getDate().getTime();
        return (int) (diffInMillis / (1000 * 60 * 60 * 24));
    }
}