import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Date());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 15);
        System.out.println(c.getTime());
        Date currentDate = new Date(); // Gets the current date and time
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = formatter.format(currentDate);
        System.out.println("Formatted date: " + formattedDate);
    }
}