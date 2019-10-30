import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReportStatsAnalyzer {

    public static List<Report> getReportsByStatus(Status status, List<Report> reports) {
        return reports.stream().filter((report) -> report.getStatus().equals(status)).collect(Collectors.toList());
    }

    public static long getReportsCountByStatus(Status status, List<Report> reports) {
        return reports.stream().filter((report) -> report.getStatus().equals(status)).count();
    }

    public static List<Report> getReportsFromDate(LocalDate startDate, List<Report> reports) {
        return reports.stream().filter((report) -> report.getDate().isAfter(startDate)).collect(Collectors.toList());
    }

    public static List<Report> getReportsToDate(LocalDate endDate, List<Report> reports) {
        return reports.stream().filter((report) -> report.getDate().isBefore(endDate)).collect(Collectors.toList());
    }

    public static List<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate, List<Report> reports) {
        return reports.stream().filter((report) -> report.getDate().isBefore(endDate)
                                                   && report.getDate().isAfter(startDate))
                                                   .collect(Collectors.toList());
    }

    public static String getLastReportsLog(int lastReportsCount, List<Report> reports) {
        return System.lineSeparator() +
                reports.stream().skip(reports.size() - lastReportsCount)
                        .map(report -> report.getName() + System.lineSeparator() + report.getDescription())
                        .reduce((r1, r2) -> r1 + System.lineSeparator() + r2).orElse("");
    }
}
