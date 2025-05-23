import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] A, memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 수열 A의 크기
        A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        memo = new int[n+1];
        int len = 0, idx = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] > memo[len]) {
                memo[++len] = A[i];
            } else {
                idx = binarySearch(0, len, A[i]);
                memo[idx] = A[i];
            }
        }

        System.out.println(len);

        br.close();
    }

    private static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (memo[mid] < target) left = mid+1;
            else right = mid;
        }

        return right;
    }
}