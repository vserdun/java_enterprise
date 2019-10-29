import lombok.extern.slf4j.Slf4j;
import objects.Report;
import objects.Status;
import randomGenerator.RandomGenerator;
import randomGenerator.RandomGeneratorImpl;
import reportsExplorer.ReportsExplorer;
import reportsExplorer.ReportsExplorerImpl;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String ... args){
        RandomGenerator generator = new RandomGeneratorImpl();

        List<Report> reports = generator.generateRandomReports(20);
        log.info("Generated Reports: ");
        reports.forEach(r -> log.info(r.toString()));
        log.info("\n");

        ReportsExplorer explorer = new ReportsExplorerImpl(reports);

        List<Report> redReports = (List<Report>) explorer.getReportsByStatus(Status.RED);
        log.info("Reports by status: ");
        redReports.forEach(r -> log.info(r.toString()));
        log.info("\n");
        log.info("Reports count by the selected status: " + explorer.getReportsCountByStatus(Status.RED));
        log.info("\n");

        List<Report> reportsInInterval = (List<Report>) explorer.getReportsInInterval(LocalDate.of(2014, 4,6), LocalDate.of(2019, 4, 6));
        log.info("Reports in interval: ");
        reportsInInterval.forEach(r -> log.info(r.toString()));
        log.info("\n");

        String lastReportsLog = explorer.getLastReportsLog(3);
        log.info("Last reports log: " + lastReportsLog);
    }
}
