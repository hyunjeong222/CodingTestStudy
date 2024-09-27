import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        LocalDate now = LocalDate.now();
        sb.append(now.getYear()).append("\n");
        sb.append(now.getMonthValue()).append("\n");
        sb.append(now.getDayOfMonth());

        System.out.println(sb.toString());
    }
}