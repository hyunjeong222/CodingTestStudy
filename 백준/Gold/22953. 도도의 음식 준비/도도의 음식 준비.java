import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k, c;
    static long ans = 1_000_000_000_000L;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        trial(c, 0);
        System.out.println(ans);
    }

    static void binarySearch() {
        long left = 1, right = 1_000_000_000_000L;
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;

            for (int i = 0; i < n; i++) {
                cnt += (mid / arr[i]);
            }

            if (cnt >= k) {
                right = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                left = mid + 1;
            }
        }
    }

    static void trial(int cnt, int idx) {
        if (cnt == 0) {
            binarySearch();
            return;
        }

        for (int i = idx; i < n; i++) {
            if (arr[i] > 1) {
                arr[i]--;
                trial(cnt - 1, i);
                arr[i]++;
            } else {
                trial(cnt - 1, i);
            }
        }
    }
}