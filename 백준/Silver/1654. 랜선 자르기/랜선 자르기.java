import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[k];
        long max = 0;
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
        long start = 1;
        long end = max;
        while (start <= end) {
            long mid = start + (end-start) / 2;

            long sum = 0;
            for (int lan : arr) {
                if (lan >= mid) sum += (lan / mid);
            }

            if (sum >= n) start = mid+1;
            else end = mid-1;
        }
        System.out.println(end);
    }
}