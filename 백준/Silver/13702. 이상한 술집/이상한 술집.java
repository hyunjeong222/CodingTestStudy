import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 막걸리 주전자 개수
        int k = Integer.parseInt(st.nextToken()); // 친구들 수 (은상 포함)

        int[] capacity = new int[n];
        for (int i = 0; i < n; i++) {
            capacity[i] = Integer.parseInt(br.readLine());
        }

        long left = 1;
        long right = capacity[n-1];
        while (left <= right) {
            long mid = (left+right)/2;

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                cnt += capacity[i] / mid;
            }

            if (cnt >= k) left = mid+1;
            else right = mid-1;
        }

        System.out.println(right);

        br.close();
    }
}