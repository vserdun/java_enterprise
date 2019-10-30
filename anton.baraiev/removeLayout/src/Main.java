import java.io.File;

public class Main {

    public static void main(String[] args) {

        String location = "C:\\Users\\HP-PC\\Desktop\\PRINT";
        File dir = new File(location);

        for (File f : dir.listFiles()) {
            if (f.getName().endsWith(".pdf")) {
                f.renameTo(f.getName().replace(" Layout1",""))
            }
        }

    }
}
