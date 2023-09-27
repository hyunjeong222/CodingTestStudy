import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int t, n, m;
    static char[][] map;
    static boolean[][] checked;
    static int min, dotNum;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        String input = "";
        t = 0;
        while ((input = br.readLine()) != null) {
            t++;
            st = new StringTokenizer(input);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new char[n][m];
            checked = new boolean[n][m];
            min = Integer.MAX_VALUE;
            dotNum = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(input);
                String s = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == '.') dotNum++;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == '.') {
                        checked[i][j] = true;
                        dfs(i, j, 0, 1);
                        checked[i][j] = false;
                    }
                }
            }

            if (min == Integer.MAX_VALUE) {
                bw.append("Case " + t + ": " + -1 + "\n");
            } else {
                bw.append("Case " + t + ": " + min + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int r, int c, int depth, int moves) {
        if (moves == dotNum) {
            min = Math.min(min, depth);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int count = 0;
            int x = r;
            int y = c;

            while (true) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == '*' || checked[nx][ny]) break;

                checked[nx][ny] = true;
                count++;
                x = nx;
                y = ny;
            }

            if (r == x && c == y) continue;

            dfs(x, y, depth+1, moves+count);

            for (int j = 0; j < count; j++) {
                checked[x][y] = false;
                x -= dx[i];
                y -= dy[i];
            }
        }
    }
}