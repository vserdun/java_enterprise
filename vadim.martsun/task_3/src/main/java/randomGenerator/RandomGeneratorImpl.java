package randomGenerator;

import objects.Report;
import objects.Status;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGeneratorImpl implements RandomGenerator {

    private Random random = new Random();

    @Override
    public List<Report> generateRandomReports(int count) {
        if(count <= 0) throw new IllegalArgumentException("Count should be greater than 0");
        List<Report> reports = new ArrayList<>();
        do{
            reports.add(generateRandomReport());
            count--;
        }while (count > 0);
        return reports;
    }

    private Report generateRandomReport(){
        return new Report(generateId(),
                generateRandomDate(),
                generateName(),
                generateDescription(),
                generateStatus());
    }

    private int generateId(){
        return Math.abs(random.nextInt());
    }

    private LocalDate generateRandomDate(){
        int minYear = 2010, maxYear = 2019;
        int randomYear = random.nextInt((maxYear - minYear) + 1) + minYear;
        int randomMonth = random.nextInt(12) + 1;
        int randomDay = random.nextInt(27) + 1;

        return LocalDate.of(randomYear, randomMonth, randomDay);
    }

    private String generateName(){
        return "Report_" + Math.abs(random.nextInt());
    }

    private String generateDescription(){
        StringBuilder description = new StringBuilder();
        int max = RandomGenerator.ALPHABET.length();
        int minDescSize = 20, maxDescSize = 100, descRange = (random.nextInt((maxDescSize - minDescSize) + 1)) + minDescSize;
        for(int i = 0; i < descRange; i++){
            description.append(ALPHABET.charAt(random.nextInt(max)));
        }
        return description.toString();
    }

    private Status generateStatus(){
        Status[] statuses = Status.values();
        return statuses[random.nextInt(statuses.length)];
    }
}
