import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        int ans1 = a + b - c;
        String str = String.valueOf(a) + String.valueOf(b);
        int ans2 = Integer.parseInt(str) - c;
        sb.append(ans1).append("\n").append(ans2);

        System.out.println(sb);
    }
}