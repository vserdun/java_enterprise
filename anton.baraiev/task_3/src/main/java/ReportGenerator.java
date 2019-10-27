import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Collectors;

public class ReportGenerator {

    private static final Random random = new Random();

    public static Report generateReport() {
        return new Report(generateId(), generateDate(), generateName(),
                          generateDescription(), generateStatus());
    }

    private static int generateId() {
        return random.nextInt(100_000);
    }

    private static LocalDate generateDate() {
        return LocalDate.now().minusDays(random.nextInt(365));
    }

    private static String generateName() {
        return "Report No. " + random.nextInt( 10_000);
    }

    private static String generateDescription() {
        String symbols = "abcdefghijklmnopqrstuvwxyz";
        return random.ints(7, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private static Status generateStatus() {
        return Status.getRandomStatus();
    }

}
