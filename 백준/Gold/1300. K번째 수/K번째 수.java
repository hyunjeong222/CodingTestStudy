import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 배열 크기
        int k = Integer.parseInt(br.readLine());

        long left = 1;
        long right = k;
        long ans = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;

            // mid보다 작거나 같은 수는 몇 개인지
            for (int i = 1; i <= n; i++) {
                cnt += Math.min(mid/i, n);
            }

            if (cnt < k) left = mid+1;
            else {
                ans = mid;
                right = mid - 1;
            }
        }

        System.out.println(ans);

        br.close();
    }
}