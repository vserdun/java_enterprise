import lombok.extern.java.Log;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Log
public class Main {
    public static void main(String[] args) {

        ReportProcessorImpl reportProcessor = new ReportProcessorImpl();

        Collection<Report> reportsByStatus = reportProcessor.getReportsByStatus(Status.YELLOW);
        reportsByStatus.forEach(report -> log.info(report.toString()));

        int reportsCountByStatus = reportProcessor.getReportsCountByStatus(Status.GREEN);
        log.info(String.valueOf(reportsCountByStatus));

        List<Report> reportsFromDate = reportProcessor.getReportsFromDate(LocalDate.parse("2019-05-12"));
        reportsFromDate.forEach(report -> log.info(report.toString()));

        List<Report> reportsInInterval = reportProcessor.getReportsInInterval(LocalDate.parse("2019-05-01"), LocalDate.parse("2019-07-01"));
        reportsInInterval.forEach(report -> log.info(report.toString()));

        String lastReportsLog = reportProcessor.getLastReportsLog(3);
        log.info(lastReportsLog);


    }
}
