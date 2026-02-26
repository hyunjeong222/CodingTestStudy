import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 캐릭터 개수
        int k = Integer.parseInt(st.nextToken()); // 레벨 총합

        int[] arr = new int[n];
        long left = Integer.MAX_VALUE; // 현재 최소 레벨
        long right = Integer.MAX_VALUE; // 현재 최대 레벨
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            left = Math.min(left, arr[i]);
        }

        long ans = 0;
        while (left <= right) {
            long mid = (left+right)/2; // 목표 팀 레벨

            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (mid >= arr[i]) {
                    sum += (mid-arr[i]);
                }
            }

            if (k >= sum) {
                left = mid+1;
                ans = Math.max(ans, mid);
            } else right = mid-1;
        }

        System.out.println(ans);

        br.close();
    }
}