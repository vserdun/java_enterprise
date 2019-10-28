import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReportGenerator {
    public List<Report> generate() {
        List<Report> reports = new ArrayList<>();
        Status[] statuses = Status.values();
        String name;
        for (int id = 1; id <= 1000; id++) {
            name = "Report_" + id;
            reports.add(new Report(id, generateRandomDate(), name, generateRandomString((10+id)%20), statuses[(id-1)%3]));
        }
        return reports;
    }

    private LocalDate generateRandomDate() {
        LocalDate start = LocalDate.of(1970, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        LocalDate randomDate = start.plusDays(new Random().nextInt((int) days + 1));
        return randomDate;
    }

    private String generateRandomString(int n) {


        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {

            char ch = randomString.charAt(k);

            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {

                r.append(ch);
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }

}
