import java.util.Collection;

public class Main {

    public static void main(String[] args){

        ReportGenerator generator = new ReportGeneratorImpl("Report_", "Description_");
        Collection<Report> reports = generator.generateReports(1000);
        System.out.println(reports);

    }
}
