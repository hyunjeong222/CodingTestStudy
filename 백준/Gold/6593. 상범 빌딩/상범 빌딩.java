import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int l, r, c;
    static char[][][] map;
    static boolean[][][] checked;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static Queue<Pos> que;
    static public class Pos {
        int z; int x; int y; int time;
        public Pos(int z, int x, int y, int time) {
            this.z = z; this.x = x; this.y = y; this.time = time;
        }
    }
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (l == 0 && r == 0 && c == 0) {
                System.out.println(sb);
                return;
            }

            map = new char[l][r][c];
            checked = new boolean[l][r][c];
            que = new LinkedList<>();
            for (int h = 0; h < l; h++) {
                for (int i = 0; i < r; i++) {
                    String str = br.readLine();
                    for (int j = 0; j < c; j++) {
                        map[h][i][j] = str.charAt(j);
                        if (map[h][i][j] == 'S') {
                            que.offer(new Pos(h, i, j, 0));
                            checked[h][i][j] = true;
                            map[h][i][j] = '.';
                        }
                    }
                }
                br.readLine();
            }

            bfs();
        }
    }

    private static void bfs() {
        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (map[now.z][now.x][now.y] == 'E') {
                sb.append("Escaped in ").append(now.time).append(" minute(s).").append("\n");
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nz = dz[i] + now.z;
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nz >= 0 && nx >= 0 && ny >= 0 && nz < l && nx < r && ny < c && !checked[nz][nx][ny]) {
                    if (map[nz][nx][ny] == '.' || map[nz][nx][ny] == 'E') {
                        que.offer(new Pos(nz, nx, ny, now.time+1));
                        checked[nz][nx][ny] = true;
                    }
                }
            }
        }
        sb.append("Trapped!").append("\n");
    }
}