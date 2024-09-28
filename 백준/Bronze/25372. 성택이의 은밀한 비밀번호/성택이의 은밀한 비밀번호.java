import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String str;
        while (n-->0) {
            str = br.readLine();
            if (str.length() >= 6 && str.length() <= 9) sb.append("yes").append("\n");
            else sb.append("no").append("\n");
        }

        System.out.println(sb.toString());
    }
}