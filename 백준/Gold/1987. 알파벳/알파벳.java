import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static int[][] map;
    static boolean[] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j) - 'A';
            }
        }

        checked = new boolean[26];
        checked[map[0][0]] = true;
        dfs(0, 0, 1);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int depth) {
        ans = Math.max(ans, depth);

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                if (!checked[map[nx][ny]]) {
                    checked[map[nx][ny]] = true;
                    dfs(nx, ny, depth+1);
                    checked[map[nx][ny]] = false;
                }
            }
        }
    }
}