import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Data
public class ReportsStatisticImpl implements ReportsStatistic {

    @NonNull
    private Collection<Report> reports;

    @Override
    public Collection<Report> getReportsByStatus(Report.Status status) {
        return reports.stream()
                .filter(report -> report.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public int getReportsCountByStatus(Report.Status status) {
        return (int) reports.stream()
                .filter(report -> report.getStatus().equals(status))
                .count();
    }

    @Override
    public Collection<Report> getReportsFromDate(LocalDate startDate) {
        return reports.stream()
                .sorted((o1, o2) -> o1.getDate().getDayOfYear() - o2.getDate().getDayOfYear())
                .filter(report ->
                        report.getDate().isAfter(startDate) || report.getDate().isEqual(startDate))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Report> getReportsToDate(LocalDate endDate) {
        return reports.stream()
                .sorted((o1, o2) -> o1.getDate().getDayOfYear() - o2.getDate().getDayOfYear())
                .filter(report -> report.getDate().isBefore(endDate) || report.getDate().isEqual(endDate))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate) {
        return reports.stream()
                .sorted(((o1, o2) -> o1.getDate().getDayOfYear() - o2.getDate().getDayOfYear()))
                .filter(report -> startDate.isBefore(report.getDate()) && endDate.isAfter(report.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public String getLastReportsLog(int lastReportsCount) {
        return reports.stream()
                .skip(reports.size() - lastReportsCount)
                .map(report -> reportLog(report))
                .reduce((s1, s2)-> s1.concat("\n").concat(s2))
                .get();
    }

    private String reportLog(Report report) {
        StringJoiner joiner = new StringJoiner(", ");
        joiner.add(report.getName());
        joiner.add(report.getDescription());
        joiner.add(report.getDate().toString());
        joiner.add(report.getStatus().toString());
        return joiner.toString();
    }
}
