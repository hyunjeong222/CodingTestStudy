import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][] prefix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine()); // nxn 과수원의 크기
        map = new int[n+1][n+1]; // 총이익을 저장할 배열
        prefix = new int[n+1][n+1]; // 총이익의 누적합을 저장할 배열
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                prefix[i][j] = map[i][j] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int tmp = check(i);
            ans = Math.max(ans, tmp);
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int check(int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i+k-1 > n || j+k-1 > n) continue;
                int tmp = prefix[i+k-1][j+k-1] - prefix[i-1][j+k-1] - prefix[i+k-1][j-1] + prefix[i-1][j-1];
                max = Math.max(max, tmp);
            }
        }
        return max;
    }
}