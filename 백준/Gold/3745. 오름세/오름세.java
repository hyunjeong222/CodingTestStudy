import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] week, memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String line = null;
        while ((line = br.readLine()) != null) {
            n = Integer.parseInt(line.trim()); // 주가를 관찰한 날의 수

            st = new StringTokenizer(br.readLine());
            week = new int[n]; // n일 동안의 주가
            for (int i = 0; i < n; i++) {
                week[i] = Integer.parseInt(st.nextToken());
            }

            memo = new int[n+1];
            int len = 0, idx = 0;
            for (int i = 0; i < n; i++) {
                if (week[i] > memo[len]) {
                    memo[++len] = week[i];
                } else {
                    idx = binarySearch(0, len, week[i]);
                    memo[idx] = week[i];
                }
            }

            sb.append(len).append("\n");

        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = (left+right)/2;

            if (memo[mid] < target) {
                left = mid+1;
            } else right = mid;
        }

        return right;
    }
}