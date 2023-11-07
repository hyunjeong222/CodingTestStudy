import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = Integer.MIN_VALUE; // 테트로미노가 놓은 간에 쓰인 수들의 최댓값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 종이의 세로, 가로
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m]; // 종이
        checked = new boolean[n][m];
        // 종이에 쓰여진 수 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                checked[i][j] = true;
                dfs(i, j, map[i][j], 1);
                checked[i][j] = false;
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int sum, int cnt) {
        if (cnt == 4) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!checked[nx][ny]) {
                    if (cnt == 2) {
                        checked[nx][ny] = true;
                        dfs(x, y, map[nx][ny] + sum, cnt+1);
                        checked[nx][ny] = false;
                    }

                    checked[nx][ny] = true;
                    dfs(nx, ny, map[nx][ny] + sum, cnt+1);
                    checked[nx][ny] = false;
                }
            }
        }
    }
}