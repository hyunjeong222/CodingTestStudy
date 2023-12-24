import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][][][] checked;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static Queue<Pos> que;
    static public class Pos {
        int redX; int redY; int blueX; int blueY; int cnt;
        public Pos(int redX, int redY, int blueX, int blueY, int cnt) {
            this.redX = redX; this.redY = redY;
            this.blueX = blueX; this.blueY = blueY;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int rx = 0; int ry = 0;
        int bx = 0; int by = 0;
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }
        que = new LinkedList<>();
        que.offer(new Pos(rx, ry, bx, by, 0));
        checked = new boolean[n][m][n][m];
        checked[rx][ry][bx][by] = true;
        int ans = bfs();
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        while (!que.isEmpty()) {
            Pos now = que.poll();

            if (now.cnt > 10) return -1;

            if (map[now.blueX][now.blueY] == 'O') continue;

            if (map[now.redX][now.redY] == 'O' && map[now.blueX][now.blueY] != 'O') {
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int rnx = now.redX;
                int rny = now.redY;

                while (true) {
                    if (rnx + dx[i] >= 0 && rny + dy[i] >= 0) {
                        rnx += dx[i];
                        rny += dy[i];
                        if (map[rnx][rny] == '#' || map[rnx][rny] == 'O') break;
                    }
                }

                if (map[rnx][rny] == '#') {
                    rnx -= dx[i];
                    rny -= dy[i];
                }

                int bnx = now.blueX;
                int bny = now.blueY;

                while (true) {
                    if (bnx + dx[i] >= 0 && bny + dy[i] >= 0) {
                        bnx += dx[i];
                        bny += dy[i];
                        if (map[bnx][bny] == '#' || map[bnx][bny] == 'O') break;
                    }
                }

                if (map[bnx][bny] == '#') {
                    bnx -= dx[i];
                    bny -= dy[i];
                }

                if (rnx == bnx && rny == bny && map[rnx][rny] != 'O') {
                    int rDir = Math.abs(rnx - now.redX) + Math.abs(rny - now.redY);
                    int bDir = Math.abs(bnx - now.blueX) + Math.abs(bny - now.blueY);

                    if (rDir > bDir) {
                        rnx -= dx[i];
                        rny -= dy[i];
                    } else {
                        bnx -= dx[i];
                        bny -= dy[i];
                    }
                }

                if (!checked[rnx][rny][bnx][bny]) {
                    checked[rnx][rny][bnx][bny] = true;
                    que.offer(new Pos(rnx, rny, bnx, bny, now.cnt+1));
                }
            }
        }
        return -1;
    }
}