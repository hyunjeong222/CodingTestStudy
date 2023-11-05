import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][m+1];
        int[][] prefix = new int[n+1][m+1];
        boolean flag = false; // 블랙 : false, 화이트 : true
        // 첫 칸 블랙 기준
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                char color = str.charAt(j-1);
                if (!flag && color == 'W') map[i][j] = 1;
                else if (flag && color == 'B') map[i][j] = 1;
                flag = !flag;
            }
            if (m % 2 == 0) flag = !flag;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefix[i][j] = map[i][j] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i+k-1 > n || j+k-1 > m) continue;
                int tmp = prefix[i+k-1][j+k-1] - prefix[i-1][j+k-1] - prefix[i+k-1][j-1] + prefix[i-1][j-1];
                min = Math.min(tmp, min);
                max = Math.max(tmp, max);

            }
        }
        int ans = Math.min(min, k*k-max);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}