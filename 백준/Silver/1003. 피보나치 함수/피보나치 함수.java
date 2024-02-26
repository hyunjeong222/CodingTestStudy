import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int[] dp0 = new int[41];
        int[] dp1 = new int[41];
        dp0[0] = 1; dp0[1] = 0;
        dp1[0] = 0; dp1[1] = 1;
        for (int i = 2; i < 41; i++) {
            dp0[i] = dp0[i-1]+dp0[i-2];
            dp1[i] = dp1[i-1]+dp1[i-2];
        }
        int n;
        while (t --> 0) {
            n = Integer.parseInt(br.readLine());
            sb.append(dp0[n]).append(" ").append(dp1[n]).append("\n");
        }
        System.out.println(sb);
    }
}