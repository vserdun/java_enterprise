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
        List<Report> reports = new ArrayList<>();

        int i = 0;
        while (i < count){
            reports.add(new Report(generateId(),
                    generateRandomDate(),
                    generateName(),
                    generateDescription(),
                    generateStatus()));
            i++;
        }

        return reports;
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
        String name = "Report_";
        int reportId = Math.abs(random.nextInt());

        return name + reportId;
    }

    private String generateDescription(){
        StringBuilder description = new StringBuilder();
        int max = RandomGenerator.ALPHABET.length();

        for(int i = 0; i < max; i++){
            description.append(ALPHABET.charAt(random.nextInt(max)));
        }

        return description.toString();
    }

    private Status generateStatus(){
        String[] statuses = new String[]{"Green", "Red", "Yellow"};
        return Status.fromString(statuses[random.nextInt(statuses.length)]);
    }
}
