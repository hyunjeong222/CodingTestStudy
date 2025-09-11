import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 카드의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] num = new long[n]; // N개의 수
        long m = 0; // 모든 카드에 적힌 수의 합
        for (int i = 0; i < n; i++) {
            num[i] = Long.parseLong(st.nextToken());
            m += num[i];
        }

        int total = 1 << n; // 모든 경우의 수
        HashSet<Long> set = new HashSet<>();
        for (int mask = 1; mask < total; mask++) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += num[i];
                }
            }
            set.add(sum);
        }

        long cnt = 0;
        for (long sum : set) {
            if (sum >= 1 && sum <= m) {
                cnt++;
            }
        }

        System.out.println(m-cnt);

        br.close();
    }
}