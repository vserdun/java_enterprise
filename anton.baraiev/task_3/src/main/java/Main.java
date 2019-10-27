import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) {

        //Generate collection w/ 1000 reports
        List<Report> reports = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            reports.add(ReportGenerator.generateReport());
        }

        //get reports by given status
        ReportStatsAnalyzer.getReportsByStatus(Status.YELLOW, reports).forEach((report) -> log.info(report.toString()));

        //get quantity of reports w/ given status
        log.info("REPORT QTY: " + ReportStatsAnalyzer.getReportsCountByStatus(Status.RED, reports));

        //get reports after the given date
        LocalDate startDate = LocalDate.of(2019,8,10);
        ReportStatsAnalyzer.getReportsFromDate(startDate, reports).forEach((report -> log.info(report.toString())));

        //get reports before the given date
        LocalDate endDate = LocalDate.of(2019,10,20);
        ReportStatsAnalyzer.getReportsToDate(endDate, reports).forEach((report -> log.info(report.toString())));

        //get reports between the given dates
        ReportStatsAnalyzer.getReportsInInterval(startDate, endDate, reports).forEach((report -> log.info(report.toString())));

        //concat name & description of N last reports into one string w/ line separators
        log.info(ReportStatsAnalyzer.getLastReportsLog(5, reports));
    }
}
