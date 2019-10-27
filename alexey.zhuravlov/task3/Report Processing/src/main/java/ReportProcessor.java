import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface ReportProcessor {
     Collection<Report> getReportsByStatus(Status status); //получение репортов по статусу

     int getReportsCountByStatus(Status status); //получение количества репортов по статусу

     Collection<Report> getReportsFromDate(LocalDate startDate); //получение репортов начиная со стартовой даты

     Collection<Report> getReportsToDate(LocalDate endDate); //получение репортов до конечной даты

     Collection<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate); //олучение репортов в интервале дат

     String getLastReportsLog(List<Report> reportList); //метод должен сконкатенировать name и description последних репортов с другими репортами в одну строку. Репорты и поля разделять переносом строки. Подсказка - использовать reduce
}
