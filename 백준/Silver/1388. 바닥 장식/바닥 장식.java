import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] checked;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (checked[i][j]) continue;
                if (map[i][j] == '-') dfs(i, j, true);
                else dfs(i, j, false);
                ans++;
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, boolean flag) {
        checked[x][y] = true;

        if (flag) { // -
            y++;
            if (y < m && map[x][y] == '-') dfs(x, y, true);
        } else { // |
            x++;
            if (x < n && map[x][y] == '|') dfs(x, y, false);
        }
    }
}