import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // n개의 수
        m = Integer.parseInt(st.nextToken()); // m개 이하의 구간

        arr = new int[n];
        int start = 0;
        int end = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, arr[i]);
        }

        int ans = end; // 구간의 점수의 최댓값의 최솟값
        while (start <= end) {
            int mid = start + (end-start) / 2; // 구간 내 차이의 값

            if (isValid(mid)) { // m개 이하 구간 만족
                ans = Math.min(ans, mid);
                end = mid-1;
            } else start = mid+1;
        }

        System.out.println(ans);
    }

    private static boolean isValid(int mid) {
        int cnt = 1;
        int min = arr[0];
        int max = arr[0];

        for (int i = 0; i < n; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if (max - min > mid) {
                cnt++;
                min = arr[i];
                max = arr[i];
            }
        }

        return cnt <= m;
    }
}