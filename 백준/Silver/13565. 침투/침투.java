import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int m, n;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // mxn
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        
        checked = new boolean[m][n];
        // 0번째 줄부터 시작해서 마지막 줄에 가면 성공 리턴
        for (int i = 0; i < n; i++) {
            // 전류가 통하면 0, 아니면 1
            if (map[0][i] == 0) {
                dfs(0, i);
            }
        }

        System.out.println(flag ? "YES" : "NO");
    }

    private static void dfs(int x, int y) {
        checked[x][y] = true;

        // 마지막줄에 도착했다면 침투 성공
        if (x == m-1) {
            flag = true;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (!checked[nx][ny] && map[nx][ny] == 0) {
                    checked[nx][ny] = true;
                    dfs(nx, ny);
                }
            }
        }
    }
}