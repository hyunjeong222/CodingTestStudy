import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        int min = 0; int max = 0;
        int left = 0; int right = 0;
        int now = arr[left];
        while (left <= right && right < n) {
            min = Math.min(now, sum-now);
            max = Math.max(max, min);

            if (now == min) {
                right++;
                now += arr[right];
            } else {
                now -= arr[left];
                left++;
            }
        }
        bw.append(max + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}