import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // n일 동안
        int m = Integer.parseInt(st.nextToken()); // m번 인출

        int left = 0;
        int right = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            left = Math.max(left, arr[i]);
            right += arr[i];
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int cnt = 1;
            int sum = 0;
            for (int a : arr) {
                sum += a;

                if (sum > mid) {
                    sum = a;
                    cnt++;
                }
            }

            if (cnt > m) left = mid+1;
            else right = mid-1;
        }

        System.out.println(left);
    }
}