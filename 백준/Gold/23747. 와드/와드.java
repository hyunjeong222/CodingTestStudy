import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] checked;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        checked = new boolean[n][m];
        String str;
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken())-1;
        int startY = Integer.parseInt(st.nextToken())-1;

        String command = br.readLine();
        char c;
        for (int i = 0; i < command.length(); i++) {
            c = command.charAt(i);
            if (c == 'W') bfs(startX, startY);
            else if (c == 'U') startX -= 1;
            else if (c == 'D') startX += 1;
            else if (c == 'L') startY -= 1;
            else startY += 1;
        }

        checked[startX][startY] = true;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + startX;
            int ny = dy[i] + startY;

            if (!rangeCheck(nx, ny) && !checked[nx][ny]) {
                checked[nx][ny] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if  (checked[i][j]) sb.append(".");
                else sb.append("#");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void bfs(int x, int y) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(new Pos(x, y));
        checked[x][y] = true;

        while (!que.isEmpty()) {
            Pos now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                
                if (!rangeCheck(nx, ny) && !checked[nx][ny]) {
                    if (map[now.x][now.y] == map[nx][ny]) {
                        checked[nx][ny] = true;
                        que.offer(new Pos(nx, ny));
                    }
                }
            }
        }
    }

    private static boolean rangeCheck(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}