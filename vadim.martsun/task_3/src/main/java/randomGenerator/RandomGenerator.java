package randomGenerator;

import objects.Report;
import java.util.List;

public interface RandomGenerator {
    String ALPHABET = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo PpQqRrSsTtUuVvWwXxYyZz";

    List<Report> generateRandomReports(int count);
}
