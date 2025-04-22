import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] height = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int[] sum = new int[n+1];
        int s, e, k;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            sum[s-1] += k;
            sum[e] -= k;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sum[i+1] += sum[i];
            sb.append(height[i]+sum[i]).append(" ");
        }

        System.out.println(sb.toString());

        br.close();
    }
}