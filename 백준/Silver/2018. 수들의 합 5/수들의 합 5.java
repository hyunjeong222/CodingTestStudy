import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 자연수

        int left = 1, right = 1;
        int sum = 1, ans = 0;
        while (left <= right) {
            if (sum == n) ans++;

            if (sum < n) {
                right++;
                sum += right;
            } else { // sum >= n
                sum -= left;
                left++;
            }
        }

        System.out.println(ans);

        br.close();
    }
}