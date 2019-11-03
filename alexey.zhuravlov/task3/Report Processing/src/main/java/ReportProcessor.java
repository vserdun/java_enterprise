import java.time.LocalDate;
import java.util.Collection;

public interface ReportProcessor {
    Collection<Report> getReportsByStatus(Status status);

    int getReportsCountByStatus(Status status);

    Collection<Report> getReportsFromDate(LocalDate startDate);

    Collection<Report> getReportsToDate(LocalDate endDate);

    Collection<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate);

    String getLastReportsLog(int lastReportsCount);
}
