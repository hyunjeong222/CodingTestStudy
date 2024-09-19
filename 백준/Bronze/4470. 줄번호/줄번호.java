import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String str;
        int idx = 1;
        while (n --> 0) {
            str = br.readLine();
            sb.append(idx++).append(". ").append(str).append("\n");
        }
        System.out.println(sb.toString());
    }
}