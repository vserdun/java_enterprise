package reportsExplorer;

import objects.Report;
import objects.Status;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

public class ReportsExplorerImpl implements ReportsExplorer {

    private Collection<Report> reports;

    public ReportsExplorerImpl(Collection<Report> reports){
        this.reports = reports;
    }

    @Override
    public Collection<Report> getReportsByStatus(Status status) {
        return reports.stream().filter(r -> r.getStatus().equals(status)).collect(Collectors.toList());
    }

    @Override
    public int getReportsCountByStatus(Status status) {
        return getReportsByStatus(status).size();
    }

    @Override
    public Collection<Report> getReportsFromDate(LocalDate startDate) {
        return reports.stream().filter(r -> r.getDate().isAfter(startDate)).collect(Collectors.toList());
    }

    @Override
    public Collection<Report> getReportsToDate(LocalDate endDate) {
        return reports.stream().filter(r -> r.getDate().isBefore(endDate)).collect(Collectors.toList());
    }

    @Override
    public Collection<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate) {
        return reports.stream()
                .filter(r -> r.getDate().isAfter(startDate) && r.getDate().isBefore(endDate))
                .collect(Collectors.toList());
    }

    @Override
    public String getLastReportsLog(int lastReportsCount) {
        String lastReports =  reports.stream()
                .skip(reports.size() - lastReportsCount)
                .map(r -> r.getName().concat(" ")
                        .concat(r.getDescription()).concat(" ")
                        .concat(r.getDate().toString()).concat(" ")
                        .concat(r.getStatus().toString()))
                .reduce("Last Reports ", (r1, r2) -> r1 + r2);

        String otherReports = reports.stream().limit(reports.size() - lastReportsCount)
                .map(Report::toString)
                .reduce("Other reports: ", (r1, r2) -> r1 + r2);

        return lastReports + "\n" + otherReports;
    }
}
