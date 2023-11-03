import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 배열의 크기
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+1];
        int[][] prefix = new int[n+1][m+1]; // 누적합을 저장할 배열
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 2차원 배열에 누적합 저장하기
                prefix[i][j] = map[i][j] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }
        
        int k = Integer.parseInt(br.readLine()); // 합을 구할 부분의 개수
        while (k --> 0) {
            st = new StringTokenizer(br.readLine());
            // (i, j) 위치부터 (x, y) 위치
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int ans = prefix[x][y] - prefix[i-1][y] - prefix[x][j-1] + prefix[i-1][j-1];
            bw.append(ans + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}