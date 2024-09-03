import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = n-1;
        int sum = 0;
        int max = 987654321;
        while (start < end) {
            sum = arr[start] + arr[end];

            if (Math.abs(max) > Math.abs(sum)) {
                max = sum;
            }

            if (sum == 0) break;
            else if (sum > 0) end--;
            else start++;
        }
        
        System.out.println(max);
    }
}