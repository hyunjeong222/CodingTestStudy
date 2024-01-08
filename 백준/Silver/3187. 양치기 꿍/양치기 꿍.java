import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static char[][] map;
    static boolean[][] checked;
    static int wCnt, sCnt;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        wCnt = 0; sCnt = 0;
        map = new char[r][c];
        checked = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        int wTotal = 0;
        int sTotal = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!checked[i][j] && map[i][j] == '#') {
                    sCnt = 0; wCnt = 0;
                    dfs(i, j);
                    if (sCnt > wCnt) sTotal += sCnt;
                    else wTotal += wCnt;
                }
            }
        }
        sb.append(sTotal).append(" ").append(wTotal);
        System.out.println(sb);
    }

    private static void dfs(int x, int y) {
        if (map[x][y] == 'v') wCnt++;
        else if (map[x][y] == 'k') sCnt++;
        checked[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < r && ny >= 0 && ny < c && !checked[nx][ny]) {
                if (map[nx][ny] != '#') {
                    dfs(nx, ny);
                }
            }
        }
    }
}