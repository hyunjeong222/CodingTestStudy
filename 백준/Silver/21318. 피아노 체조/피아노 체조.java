import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num;
        int[] prefix = new int[n+1];
        for (int i = 1; i <= n; i++) {
            num = Integer.parseInt(st.nextToken());
            arr[i] = num;

            if (num < arr[i-1]) {
                prefix[i-1]++;
            }
            prefix[i] = prefix[i-1];
        }

        int q = Integer.parseInt(br.readLine());
        int x, y;
        StringBuilder sb = new StringBuilder();
        while (q --> 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            // 마지막 악보는 실수 X
            sb.append(prefix[y-1] - prefix[x-1]).append("\n");
        }
        System.out.println(sb.toString());
    }
}