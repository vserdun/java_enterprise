import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static ReportGenerator reportGenerator = new ReportGenerator();
    public static ReportService reportService;


    public static void main(String[] args) {
        List<Report> reportList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            reportList.add(reportGenerator.getRandomReport());
        }

        reportService = new ReportService(reportList);

        for (int i = 0; i < 5; i++) {
            log.info(reportService.getReportsByStatus(Status.GREEN).get(i).toString());
        }

        log.info(String.valueOf(reportService.getReportsCountByStatus(Status.YELLOW)));

        for (int i = 0; i < 5; i++) {
            log.info(reportService.getReportsFromDate(LocalDate.of(1943, 5, 3)).get(i).toString());
        }


        for (int i = 0; i < 5; i++) {
            log.info(reportService.getReportsToDate(LocalDate.of(1943, 5, 3)).get(i).toString());
        }


        for (int i = 0; i < 5; i++) {
            log.info(reportService.getReportsInInterval(LocalDate.of(1943, 5, 3),
                    LocalDate.of(1985, 10, 14)).get(i).toString());
        }


        log.info(reportService.getLastReportsLog(5));
    }
}
