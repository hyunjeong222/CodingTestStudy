import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 집의 개수
            int m = Integer.parseInt(st.nextToken()); // 연속된 집의 개수
            int k = Integer.parseInt(st.nextToken()); // 최소 돈의 양

            int[] arr = new int[n]; // 집에 보관중인 돈의 양
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int cnt = 0; // 돈을 훔치는 방법의 가짓수
            // m : 슬라이딩 윈도우의 구간
            int sum = 0;
            for (int i = 0; i < m; i++) {
                sum += arr[i];
            }
            
            if (sum < k) cnt++; // K원 이상의 돈을 훔친다면 자동 방범장치가 작동 -> k원 미만

            if (n == m) {
                sb.append(cnt).append("\n");
                continue;
            }

            for (int i = m; i < arr.length+(m-1); i++) {
                sum = sum + (arr[i%n]-arr[i-m]);

                if (sum < k) cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}