import java.time.LocalDate;

public class ReportGenerator {

    public static Report generateReport() {
        return new Report(generateId(), generateDate(), generateName(),
                          generateDescription(), generateStatus());
    }

    private static int generateId() {
        return 0;
    }

    private static LocalDate generateDate() {
        return null;
    }

    private static String generateName() {
        return "";
    }

    private static String generateDescription() {
        return "";
    }

    private static Status generateStatus() {
        return Status.getRandomStatus();
    }

}
