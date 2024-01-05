import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static int sCnt = 0;
    static int wCnt = 0;
    static char[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        checked = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        int sTotal = 0; int wTotal = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!checked[i][j] && map[i][j] != '#') {
                    sCnt = 0; wCnt = 0;
                    dfs(i, j);
                    if (sCnt > wCnt) sTotal += sCnt;
                    else  wTotal += wCnt;
                }
            }
        }
        bw.append(sTotal + " " + wTotal);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y) {
        if (map[x][y] == 'k') sCnt++;
        else if (map[x][y] == 'v') wCnt++;

        checked[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                if (!checked[nx][ny] && map[nx][ny] != '#') {
                    dfs(nx, ny);
                }
            }
        }
    }
}