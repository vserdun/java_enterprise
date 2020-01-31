package com.hillel;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        RandomReportGenerator randomReportGenerator = new RandomReportGenerator();
        List<Report> reports = randomReportGenerator.generateRandomReports(1000);
        ReportStatisticService service = new ReportStatisticService(reports);

        log.info(service.getReportsByStatus(Status.STATUS_YELLOW).toString());

        log.info(String.valueOf(service.getReportsCountByStatus(Status.STATUS_GREEN)));

        log.info(service.getReportsFromDate(
                LocalDate.of(2019, 10, 22)).toString());

        log.info(service.getReportsToDate(
                LocalDate.of(2019, 10, 22)).toString());

        log.info(service.getReportsInInterval(
                LocalDate.of(2019, 4, 1),
                LocalDate.of(2019, 6, 3)).toString());

        log.info(service.getLastReportsLog(3));
    }
}
