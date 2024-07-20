import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 수의 개수
        int m = Integer.parseInt(st.nextToken()); // m 이상인 두 수의 차 중 가장 작은 것
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);
        int start = 0;
        int end = 0;

        int ans = Integer.MAX_VALUE;
        while (start <= end && end < n) {
            int diff = arr[end] - arr[start];

            if (diff >= m) {
                start++;
                ans = Math.min(ans, diff);
            } else {
                end++;
            }
        }

        System.out.println(ans);
    }
}