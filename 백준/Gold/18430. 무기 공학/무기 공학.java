import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m]; // nxm
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checked = new boolean[n][m];
        dfs(0, 0);

        System.out.println(ans);
    }

    private static void dfs(int idx, int sum) {
        if (idx == n*m) {
            ans = Math.max(ans, sum);
            return;
        }

        int x = idx / m;
        int y = idx % m;
        if (!checked[x][y]) {
            // ㄱ
            if (x+1 < n && y-1 >= 0 && !checked[x+1][y] && !checked[x][y-1]) {
                checked[x][y-1] = true;
                checked[x][y] = true;
                checked[x+1][y] = true;
                dfs(idx+1, sum+map[x+1][y]+map[x][y-1]+(map[x][y]*2));
                checked[x][y-1] = false;
                checked[x][y] = false;
                checked[x+1][y] = false;
            }
            // ┙
            if (x-1 >= 0 && y-1 >= 0 && !checked[x-1][y] && !checked[x][y-1]) {
                checked[x-1][y] = true;
                checked[x][y] = true;
                checked[x][y-1] = true;
                dfs(idx+1, sum+map[x-1][y]+map[x][y-1]+(map[x][y]*2));
                checked[x-1][y] = false;
                checked[x][y] = false;
                checked[x][y-1] = false;
            }
            // ㄴ
            if (x-1 >= 0 && y+1 < m && !checked[x-1][y] && !checked[x][y+1]) {
                checked[x-1][y] = true;
                checked[x][y] = true;
                checked[x][y+1] = true;
                dfs(idx+1, sum+map[x-1][y]+map[x][y+1]+(map[x][y]*2));
                checked[x-1][y] = false;
                checked[x][y] = false;
                checked[x][y+1] = false;
            }
            // ┍
            if (x+1 < n && y+1 < m && !checked[x+1][y] && !checked[x][y+1]) {
                checked[x+1][y] = true;
                checked[x][y] = true;
                checked[x][y+1] = true;
                dfs(idx+1, sum+map[x+1][y]+map[x][y+1]+(map[x][y]*2));
                checked[x+1][y] = false;
                checked[x][y] = false;
                checked[x][y+1] = false;
            }
        }

        // 부메랑 안 만들고 넘어가는 경우
        dfs(idx+1, sum);
    }
}