import java.io.*;
import java.util.Arrays;

public class Main {
    static int n;
    static int[] prefix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        prefix = new int[n+1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = Integer.parseInt(br.readLine()) + prefix[i-1];
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                int rightD = prefix[j-1] - prefix[i-1];
                int leftD = prefix[n] - rightD;
                int min = Math.min(rightD, leftD); 
                ans = Math.max(ans, min);
                if (rightD >= leftD) break;
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}