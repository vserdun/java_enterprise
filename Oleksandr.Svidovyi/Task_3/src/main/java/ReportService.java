import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportService {
    private List<Report> reports;

    //получение репортов по статусу
    public ArrayList<Report> getReportsByStatus(Status status) {
        return reports.stream().filter(report -> report.getStatus().equals(status)).collect(Collectors.toCollection(ArrayList::new));
    }


    //получение количества репортов по статусу
    public int getReportsCountByStatus(Status status) {
        return (int) reports.stream().filter(report -> report.getStatus().equals(status)).count();
    }


    //получение репортов начиная со стартовой даты
    public ArrayList<Report> getReportsFromDate(LocalDate startDate) {
        return reports.stream().filter(report -> report.getDate().isAfter(startDate)).collect(Collectors.toCollection(ArrayList::new));
    }


    //получение репортов до конечной даты
    public ArrayList<Report> getReportsToDate(LocalDate endDate) {
        return reports.stream().filter(report -> report.getDate().isBefore(endDate)).collect(Collectors.toCollection(ArrayList::new));
    }


    //получение репортов в интервале дат
    public ArrayList<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate) {
        return reports.stream().filter(report -> report.getDate().isAfter(startDate))
                .filter(report -> report.getDate().isBefore(endDate)).collect(Collectors.toCollection(ArrayList::new));
    }


    /*- метод должен сконкатенировать date, status, name и description последних репортов с другими репортами в одну строку.
    Репорты и поля разделять переносом строки. Подсказка - использовать reduce*/
    public String getLastReportsLog(int lastReportsCount) {
        return reports.stream().skip(reports.size() - lastReportsCount).map(Report::toString)
                .reduce((s, s2) -> s + "\n" + s2).toString();
    }
}
