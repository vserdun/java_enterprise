import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Random;

@RequiredArgsConstructor
public class ReportGeneratorImpl implements ReportGenerator{

    @NonNull
    private String namePrefix;
    @NonNull
    private String descriptionPrefix;

    @Override
    public long generateId(int number) {
        return number;
    }

    @Override
    public LocalDate generateDate(int number) {
        return LocalDate.ofYearDay(2019, new Random().nextInt(364) + 1);
    }

    @Override
    public String generateName(int number) {
        return namePrefix.concat(String.valueOf(number));
    }

    @Override
    public String generateDescription(int number) {
        return descriptionPrefix.concat(String.valueOf(number));
    }

    @Override
    public Report.Status generateStatus(int number) {
        return Report.Status.values()[new Random().nextInt(Report.Status.values().length)];
    }
}
