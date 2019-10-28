import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportProcessorImpl implements ReportProcessor {
    private List<Report> reportList;

    public ReportProcessorImpl() {
        ReportGenerator reportGenerator = new ReportGenerator();
        this.reportList = reportGenerator.generate();
    }

    @Override
    public List<Report> getReportsByStatus(Status status) {
        return reportList.stream()
                .filter(report -> report.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public int getReportsCountByStatus(Status status) {
        return (int) reportList.stream()
                .filter(report -> report.getStatus().equals(status))
                .count();
    }

    @Override
    public List<Report> getReportsFromDate(LocalDate startDate) {
        return reportList.stream()
                .filter(report -> report.getDate().isAfter(startDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> getReportsToDate(LocalDate endDate) {
        return reportList.stream()
                .filter(report -> report.getDate().isBefore(endDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate) {
        return reportList.stream()
                .filter(report -> report.getDate().isAfter(startDate) && report.getDate().isBefore(endDate))
                .collect(Collectors.toList());
    }

    @Override
    public String getLastReportsLog(int lastReportsCount) {

        return reportList.stream()
                .skip(reportList.size()-lastReportsCount)
                .map(report -> report.getName().concat(" ").concat(report.getDescription()))
                .reduce("", (s1, s2) -> s1.concat("\n").concat(s2));
    }
}
