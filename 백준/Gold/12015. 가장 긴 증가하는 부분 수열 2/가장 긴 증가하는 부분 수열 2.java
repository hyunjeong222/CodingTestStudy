import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        memo = new int[n+1];
        int len = 0; int idx = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > memo[len]) {
                memo[++len] = arr[i];
            } else {
                idx = binarySearch(0, len, arr[i]);
                memo[idx] = arr[i];
            }
        }

        System.out.println(len);
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