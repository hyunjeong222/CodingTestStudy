import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int y = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int minus = Math.abs(y-m);

        sb.append(m+minus).append("\n");

        System.out.println(sb.toString());
    }
}