import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] port, memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        port = new int[n];
        for (int i = 0; i < n; i++) {
            port[i] = Integer.parseInt(st.nextToken());
        }
        
        memo = new int[n+1];
        int idx = 0;
        int len = 0;
        for (int i = 0; i < n; i++) {
            if (port[i] > memo[len]) {
                // LIS 갱신
                memo[++len] = port[i];
            } else {
                idx = binarySearch(0, len, port[i]);
                memo[idx] = port[i];
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