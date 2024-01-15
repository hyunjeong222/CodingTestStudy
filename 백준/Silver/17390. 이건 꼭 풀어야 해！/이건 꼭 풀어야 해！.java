import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 수열의 길이
        int q = Integer.parseInt(st.nextToken()); // 질문의 개수
        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int[] prefix = new int[n+1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i-1] + arr[i];
        }
        while (q --> 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int ans = prefix[r] - prefix[l-1];
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}