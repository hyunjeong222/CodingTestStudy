import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 신호등 개수
        int k = Integer.parseInt(st.nextToken()); // 연속해야하는 신호등 개수
        int b = Integer.parseInt(st.nextToken()); // 고장난 신호등의 개수
        int[] arr = new int[n+1];
        while (b --> 0) {
            int num = Integer.parseInt(br.readLine());
            arr[num] = 1;
        }
        int sum = 0;
        for (int i = 1; i <= k; i++) {
            sum += arr[i];
        }
        int ans = sum;
        for (int i = k+1; i < arr.length; i++) {
            sum = sum - arr[i-k] + arr[i];
            ans = Math.min(ans, sum);
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}