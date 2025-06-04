import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            long n = Long.parseLong(br.readLine());

            // 등차수열의 합
            long left = 0; long right = 1_000_000_000L;
            while (left <= right) {
                long mid = (left+right)/2;
                long sum = (mid*(mid+1))/2;

                if (sum <= n) left = mid+1;
                else right = mid-1;
            }

            sb.append(right).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}