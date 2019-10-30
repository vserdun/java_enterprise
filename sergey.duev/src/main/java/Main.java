import java.time.LocalDate;
import java.util.Collection;

public class Main {

    public static void main(String[] args){
        ReportGenerator generator = new ReportGeneratorImpl("Report_", "Description_");
        Collection<Report> reports = generator.generateReports(1000);
        ReportsStatistic reportsStatistic = new ReportsStatisticImpl(reports);
        System.out.println(reportsStatistic.getReportsByStatus(Report.Status.GREEN));
        System.out.println(reportsStatistic.getReportsCountByStatus(Report.Status.GREEN));
        System.out.println(reportsStatistic.getReportsFromDate(LocalDate.ofYearDay(2019, 353)));
        System.out.println(reportsStatistic.getReportsToDate(LocalDate.ofYearDay(2019, 2)));
        System.out.println(reportsStatistic.getReportsInInterval(LocalDate.ofYearDay(2019, 2), LocalDate.ofYearDay(2019, 5)));
        System.out.println(reportsStatistic.getLastReportsLog(10));
    }
}
