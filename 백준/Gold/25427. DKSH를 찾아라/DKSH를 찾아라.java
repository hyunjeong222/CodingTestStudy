import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int D = 0;
        int K = 0;
        int S = 0;
        int H = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'D') D++;
            else if (s.charAt(i) == 'K') K += D;
            else if (s.charAt(i) == 'S') S += K;
            else if (s.charAt(i) == 'H') H += S;
        }

        System.out.println(H);

        br.close();
    }
}