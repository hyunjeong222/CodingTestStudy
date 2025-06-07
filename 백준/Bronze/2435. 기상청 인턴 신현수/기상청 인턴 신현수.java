import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 온도를 측정한 전체 날짜의 수
        int k = Integer.parseInt(st.nextToken()); // 합을 위한 연속적인 날짜의 수

        int[] degrees = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            degrees[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefix = new int[n+1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i-1]+degrees[i];
        }

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n-k+1; i++) {
            sum = prefix[i+k-1]-prefix[i-1];

            if (sum > max) max = sum;
        }

        System.out.println(max);

        br.close();
    }
}