package reportsExplorer;

import objects.Report;
import objects.Status;
import java.time.LocalDate;
import java.util.Collection;

public interface ReportsExplorer {

    Collection<Report> getReportsByStatus(Status status);
    int getReportsCountByStatus(Status status);
    Collection<Report> getReportsFromDate(LocalDate startDate);
    Collection<Report> getReportsToDate(LocalDate endDate);
    Collection<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate);
    String getLastReportsLog(int lastReportsCount);
}
