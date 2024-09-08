import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[] pick = new long[3];
    static Long max = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < n-2; i++) {
            binarySearch(i, arr);
        }

        Arrays.sort(pick);
        StringBuilder sb = new StringBuilder();
        sb.append(pick[0]).append(" ").append(pick[1]).append(" ").append(pick[2]);
        System.out.println(sb.toString());
    }

    private static void binarySearch(int i, long[] arr) {
        int left = i+1;
        int right = arr.length-1;

        while (left < right) {
            long sum = arr[i] + arr[left] + arr[right];

            if (max > Math.abs(sum)) {
                pick[0] = arr[i];
                pick[1] = arr[left];
                pick[2] = arr[right];
                max = Math.abs(sum);
            }

            if (sum > 0) right--;
            else left++;
        }
    }
}