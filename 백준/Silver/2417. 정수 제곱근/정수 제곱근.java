import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();

        // StringTokenizer st = new StringTokenizer(br.readLine());
        Long n = Long.parseLong(br.readLine()); // 정수

        long left = 0;
        long right = n;
        long ans = 0;
        while (left <= right) {
            long mid = (left+right)/2;

            if (n <= Math.pow(mid, 2)) {
                ans = mid;
                right = mid-1;
            } else left = mid+1;
        }

        System.out.println(ans);

        br.close();
    }
}