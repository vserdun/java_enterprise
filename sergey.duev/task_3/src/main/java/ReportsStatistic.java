import java.time.LocalDate;
import java.util.Collection;

public interface ReportsStatistic {

    // получение репортов по статусу
    Collection<Report> getReportsByStatus(Report.Status status);

    //получение количества репортов по статусу
    int getReportsCountByStatus(Report.Status status);

    //получение репортов начиная со стартовой даты
    Collection<Report> getReportsFromDate(LocalDate startDate);

    // получение репортов до конечной даты
    Collection<Report> getReportsToDate(LocalDate endDate);

    //получение репортов в интервале дат
    Collection<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate);

    //метод должен сконкатенировать date, status, name и description последних репортов с другими репортами в одну строку.
    // Репорты и поля разделять переносом строки.
    String getLastReportsLog(int lastReportsCount);
}
