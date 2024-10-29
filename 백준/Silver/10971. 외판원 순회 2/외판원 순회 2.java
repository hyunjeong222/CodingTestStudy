import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static boolean[] checked;
    static int cnt;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // System.out.println(Arrays.deepToString(map));

        checked = new boolean[n];
        checked[0] = true;
        cnt += 1;
        dfs(0, 0, 0);

        System.out.println(ans);
    }

    private static void dfs(int cost, int start, int cur) {
        if (cnt == n && map[cur][start] != 0) {
            ans = Math.min(ans, cost+map[cur][start]);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (checked[i] || map[cur][i] == 0) continue;
            if (cost+map[cur][i] > ans) continue;

            checked[i] = true;
            cnt += 1;

            dfs(cost+map[cur][i], start, i);

            cnt -= 1;
            checked[i] = false;
        }
    }
}