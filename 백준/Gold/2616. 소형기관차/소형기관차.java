import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine()); // 기관차가 끌고 가던 객차의 수
        StringTokenizer st;
        int[] arr = new int[n+1]; // 각 객차에 타고 있는 손님의 수를 저장할 배열
        int[] prefix = new int[n+1]; // 누적합을 저장할 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefix[i] = arr[i] + prefix[i-1];
        }
        int k = Integer.parseInt(br.readLine()); // 소형 기관차가 최대로 끌 수 있는 객차의 수
        int[][] dp = new int[4][n+1]; // 소형 기관차 i대로 k대의 객차를 선택할 때 최대 승객의 수를 저장
        for (int i = 1; i < 4; i++) {
            for (int j = i * k; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-k] + prefix[j] - prefix[j-k]);
            }
        }
        bw.append(dp[3][n] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}