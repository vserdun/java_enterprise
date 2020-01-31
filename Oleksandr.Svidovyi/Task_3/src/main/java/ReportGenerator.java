import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ReportGenerator {
    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = " -0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private int idCash = 0;
    long minDay = LocalDate.of(1900, 1, 1).toEpochDay();
    long maxDay = LocalDate.of(2019, 10, 30).toEpochDay();


    public Report getRandomReport() {
        Report returnValue = new Report();
        returnValue.setId(generateId());
        returnValue.setDate(generateDate());
        returnValue.setName(generateName());
        returnValue.setStatus(generateStatus());
        returnValue.setDescription(generateDescription(20));
        idCash++;

        return returnValue;
    }


    private int generateId() {
        int returnValue = this.idCash;
        return returnValue;
    }


    private LocalDate generateDate() {
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }


    private String generateName() {
        return "Report_" + idCash;
    }


    private String generateDescription(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }


    private Status generateStatus() {
        return Status.values()[RANDOM.nextInt(Status.values().length)];
    }

}
