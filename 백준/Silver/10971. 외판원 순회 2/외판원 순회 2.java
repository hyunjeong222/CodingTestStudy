import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static boolean[] checked;
    static int cnt = 0;
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
        checked = new boolean[n];
        // 모든 경로를 방문해야하니 0 먼저 방문한다고 가정
        checked[0] = true;
        cnt += 1;

        dfs(0, 0, 0);

        System.out.println(ans);
    }

    private static void dfs(int start, int cur, int cost) {
        // 마지막 도시에서 처음 도시로 가는 루트가 존재할 때
        if (cnt == n && map[cur][start] != 0) {
            ans = Math.min(ans, cost+map[cur][start]);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (checked[i] || map[cur][i] == 0) continue;
            if (cost+map[cur][i] > ans) continue;

            checked[i] = true;
            cnt += 1;

            dfs(start, i, cost+map[cur][i]);

            cnt -= 1;
            checked[i] = false;
        }
    }
}