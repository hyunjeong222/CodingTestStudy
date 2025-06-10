import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[n+1];
        long[] prefix = new long[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefix[i] = arr[i] + prefix[i-1];
        }

        long sum = 0;
        for (int i = 2; i <= n; i++) {
            sum += arr[i]*prefix[i-1];
        }

        System.out.println(sum);

        br.close();
    }
}