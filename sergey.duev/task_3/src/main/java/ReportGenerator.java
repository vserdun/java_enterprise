import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public interface ReportGenerator {

    default Collection<Report> generateReports(int count) {
        ArrayList<Report> reports = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            reports.add(generateReport(i));
        }
        return reports;
    };

    default Report generateReport(int number) {
        return new Report(
                generateId(number),
                generateDate(number),
                generateName(number),
                generateDescription(number),
                generateStatus(number)
        );
    };

    long generateId(int number);

    LocalDate generateDate(int number);

    String generateName(int number);

    String generateDescription(int number);

    Report.Status generateStatus(int number);

}
