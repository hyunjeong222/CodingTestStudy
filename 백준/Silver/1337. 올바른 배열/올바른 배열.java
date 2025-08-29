import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int SIZE = 5;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int left = 0;
        int right = 0;
        int ans = 4; // 최악의 경우
        while (right < n) {
            if (arr[right]-arr[left] < SIZE) {
                right++;
                ans = Math.min(ans, SIZE-(right-left));
            } else {
                left++;
            }
        }

        System.out.println(ans);

        br.close();
    }
}