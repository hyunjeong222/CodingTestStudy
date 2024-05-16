import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 지방의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] request = new int[n];
        for (int i = 0; i < n; i++) {
            request[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(request);
        long m = Integer.parseInt(br.readLine()); // 총 예산

        long start = 0;
        long end = request[n-1];
        while (start <= end) {
            long mid = start+(end-start) / 2; // 상한액

            long sum = 0;
            for (long money : request) {
                if (money >= mid) sum += mid;
                else sum += money;
            }

            if (sum > m) end = mid-1;
            else start = mid+1;
        }
        System.out.println(end);
    }
}