import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] memo, reverseAttack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] attack = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            attack[i] = Integer.parseInt(st.nextToken());
        }

        reverseAttack = new int[n];
        for (int i = 0; i < n; i++) {
            reverseAttack[i] = attack[n-i-1];
        }
        // System.out.println(Arrays.toString(reverseAttack));

        memo = new int[n+1];
        int len = 0; int idx = 0;
        for (int i = 0; i < n; i++) {
            if (reverseAttack[i] > memo[len]) {
                memo[++len] = reverseAttack[i];
            } else {
                idx = binarySearch(0, len, reverseAttack[i]);
                memo[idx] = reverseAttack[i];
            }
        }

        System.out.println(n-len);
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