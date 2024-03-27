import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 나무의 수
        long m = Long.parseLong(st.nextToken()); // 가져가려는 나무의 길이
        long[] arr = new long[n];
        long max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            max = Math.max(arr[i], max);
        }
        long start = 0;
        long end = max;
        while (start <= end) {
            long mid = start+(end-start) / 2;

            long sum = 0;
            for (long num : arr) {
                if (num > mid) sum += (num-mid);
            }

            if (sum >= m) start = mid+1;
            else end = mid-1;
        }
        System.out.println(end);
    }
}