import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken()); // 입국 심사대의 개수
        long m = Long.parseLong(st.nextToken()); // 총 인원수

        long[] time = new long[(int)n]; // 심사에 걸리는 시간
        long min = Long.MAX_VALUE;
        long max = 0;
        for (int i = 0; i < n; i++) {
            time[i] = Long.parseLong(br.readLine());
            min = Math.min(min, time[i]);
            max = Math.max(max, time[i]);
        }

        long left = min;
        long right = max * m;
        while (left <= right) {
            long mid = left + (right-left) / 2;

            long cnt = 0; // 주어진 시간 내에 처리할 수 있는 인원
            for (int i = 0; i < n; i++) {
                cnt += mid/time[i];
                if (cnt >= m) break;
            }

            if (cnt < m) left = mid+1;
            else right = mid-1;
        }

        System.out.println(left);
    }
}