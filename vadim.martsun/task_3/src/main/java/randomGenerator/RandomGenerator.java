package randomGenerator;

import objects.Report;
import java.util.List;

public interface RandomGenerator {
    String ALPHABET = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo PpQqRrSsTtUuVvWwXxYyZz";
    int MONTHS_IN_YEAR = 12;
    int DAYS = 28;

    List<Report> generateRandomReports(int count);
}
