package com.company;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

public class ReportStatisticService {
    private Collection<Report> reports;

    public ReportStatisticService(Collection<Report> reports) {
        this.reports = reports;
    }

    public Collection<Report> getReportsByStatus(Status status) {
        return reports.stream()
                .filter(r -> r.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public int getReportsCountByStatus(Status status) {
        return (int) getReportsByStatus(status).stream().count();
    }

    public Collection<Report> getReportsFromDate(LocalDate startDate) {
        return reports.stream()
                .filter(r -> r.getDate().isAfter(startDate))
                .collect(Collectors.toList());
    }

    public Collection<Report> getReportsToDate(LocalDate endDate) {
        return reports.stream()
                .filter(r -> r.getDate().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public Collection<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate) {
        return reports.stream()
                .filter(r -> r.getDate().isBefore(endDate) && r.getDate().isAfter(startDate))
                .collect(Collectors.toList());
    }

    public String getLastReportsLog(int lastReportsCount) {
        return reports.stream()
                .skip(reports.size() - lastReportsCount)
                .map(Report::toString)
                .reduce("Last reports: ", (r1, r2) ->
                        r1 + "\n" + r2);
    }
}
