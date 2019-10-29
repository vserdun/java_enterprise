import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;

@Data
public class ReportsStatisticImpl implements ReportsStatistic {

    private Collection<Report> reports;


    @Override
    public Collection<Report> getReportsByStatus(Report.Status status) {
        return null;
    }

    @Override
    public int getReportsCountByStatus(Report.Status status) {
        return 0;
    }

    @Override
    public Collection<Report> getReportsFromDate(LocalDate startDate) {
        return null;
    }

    @Override
    public Collection<Report> getReportsToDate(LocalDate endDate) {
        return null;
    }

    @Override
    public Collection<Report> getReportsInInterval(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public String getLastReportsLog(int lastReportsCount) {
        return null;
    }
}
