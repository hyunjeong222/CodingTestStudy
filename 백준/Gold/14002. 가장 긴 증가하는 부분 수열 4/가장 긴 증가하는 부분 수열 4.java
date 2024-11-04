import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr, memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0, idx = 0;
        memo = new int[n+1];
        memo[0] = -1000000000;
        int[] dp = new int[n]; // 증가 부분 수열 크기 저장
        for (int i = 0; i < n; i++) {
            if (arr[i] > memo[len]) {
                dp[i] = ++len;
                memo[len] = arr[i];
            } else {
                idx = binarySearch(0, len, arr[i]);
                memo[idx] = arr[i];
                dp[i] = idx;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(len).append("\n");

        Stack<Integer> stack = new Stack<>();
        for (int i = n-1; i >= 0; i--) {
            if (dp[i] == len) {
                stack.push(arr[i]);
                len--;
            }
        }

        while (!stack.empty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }

    private static int binarySearch(int left, int right, int target) {
        while (left < right) {
            int mid = left + (right-left) / 2;

            if (memo[mid] < target) {
                left = mid+1;
            } else right = mid;
        }
        return right;
    }
}