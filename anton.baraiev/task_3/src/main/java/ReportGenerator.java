import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Random;

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
        byte[] array = new byte[5];
        random.nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

    private static Status generateStatus() {
        return Status.getRandomStatus();
    }

}
