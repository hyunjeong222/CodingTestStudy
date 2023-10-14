import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static boolean[][] checked;
    static int r, c;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int sheep, wolf;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 행
        c = Integer.parseInt(st.nextToken()); // 열
        map = new char[r][c]; // 마당
        checked = new boolean[r][c];
        for (int i = 0; i < r; i ++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int sCnt = 0;
        int wCnt = 0;
        for (int i = 0; i < r; i ++) {
            for (int j = 0; j < c; j++) {
                sheep = 0;
                wolf = 0;
                if (!checked[i][j] && (map[i][j] == 'o' || map[i][j] == 'v')) {
                    dfs(i, j);
                    if (sheep > wolf) sCnt += sheep;
                    else wCnt += wolf;
                }
            }
        }
        sb.append(sCnt).append(" ").append(wCnt);
        System.out.println(sb);
    }

    private static void dfs(int x, int y) {
        checked[x][y] = true;

        if (map[x][y] == 'o') sheep++;
        else if (map[x][y] == 'v') wolf++;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (nx >= 0 && nx < r && ny >= 0 && ny < c && !checked[nx][ny] && map[nx][ny] != '#') {
                checked[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }
}