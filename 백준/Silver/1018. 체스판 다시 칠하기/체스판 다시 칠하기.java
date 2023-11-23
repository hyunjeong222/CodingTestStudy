import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // nxm 체스판
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+1];
        boolean flag = false; // f 블랙 t 흰색
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                char c = str.charAt(j-1);
                // 다시 칠해야 하는 곳 1 아닌 곳 0
                if (c == 'B' && flag) map[i][j] = 1;
                else if (c == 'W' && !flag) map[i][j] = 1;
                flag = !flag;
            }
            if (m % 2 == 0) flag = !flag;
        }
        
        // 누적합
        int[][] prefix = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefix[i][j] = map[i][j] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i+7 > n || j+7 > m) continue;
                int tmp = prefix[i+7][j+7] - prefix[i+7][j-1] - prefix[i-1][j+7] + prefix[i-1][j-1];
                min = Math.min(tmp, min);
                max = Math.max(tmp, max);
            }
        }
        
        int ans = Math.min(min, 64-max);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}