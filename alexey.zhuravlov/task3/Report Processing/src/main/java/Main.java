import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ReportProcessorImpl reportProcessor = new ReportProcessorImpl();

        Collection<Report> reportsByStatus = reportProcessor.getReportsByStatus(Status.YELLOW);
        //reportsByStatus.forEach(System.out::println);

        int reportsCountByStatus = reportProcessor.getReportsCountByStatus(Status.GREEN);
        //System.out.println(reportsCountByStatus);

        List<Report> reportsFromDate = reportProcessor.getReportsFromDate(LocalDate.parse("2019-05-12"));
        //reportsFromDate.forEach(System.out::println);

        List<Report> reportsInInterval = reportProcessor.getReportsInInterval(LocalDate.parse("2019-05-01"), LocalDate.parse("2019-07-01"));
        reportsInInterval.forEach(System.out::println);


    }
}
